package com.example.manuel.ujafachumtfg;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.DialogTitle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;








public class GestionActivity extends AppCompatActivity {




    HttpURLConnection con;

    private ListView list ;
    private ArrayAdapter<String> listAdapter ;

    String usuarioconectado;


    private TextView tematicas;

    private TextView apellidotutor;
    private TextView codigotutor;
    private TextView nombretutor;


    // Botones de Eleccion y borrado del tutor
    private Button btn_eligetutor;
    private Button btn_borratutor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);




        usuarioconectado =  getIntent().getStringExtra("usuario");

////// la lista eo

        list = (ListView) findViewById( R.id.list );


        //ArrayList<Tutor_tfg> AdapterList = new ArrayList<Tutor_tfg>();


        // Create ArrayAdapter using the planet list.
        //listAdapter = new ArrayAdapter<Tutor_tfg>(this, R.layout.simplerow, AdapterList);


        // Set the ArrayAdapter as the ListView's adapter.
     //   list.setAdapter( listAdapter );



////////////////////


        this.apellidotutor = (TextView) findViewById(R.id.tutor);
        this.nombretutor = (TextView) findViewById(R.id.NombreTutor);

        this.codigotutor = (TextView) findViewById(R.id.codigotutor);



        Intent intent = getIntent();
        final Tfg tfg  = (Tfg)intent.getSerializableExtra("title");
        this.apellidotutor.setText(tfg.getApellidosTutor());
        this.nombretutor.setText(tfg.getNombreTutor());
        this.codigotutor.setText(tfg.getCodigoTutor());



        // Boton de elegir







        btn_eligetutor = (Button)findViewById(R.id.button_eligetutor);

        btn_eligetutor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                Log.i("Elige", "onClick: ");


                new AlertDialog.Builder(btn_eligetutor.getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Eleccion del tutor")
                        .setMessage("¿Esta seguro que desea elegir el tutor: "+tfg.getNombreTutor()+" "+tfg.getApellidosTutor()+"?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                try {
                                    ConnectivityManager connMgr = (ConnectivityManager)
                                            getSystemService(Context.CONNECTIVITY_SERVICE);

                                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                                    if (networkInfo != null && networkInfo.isConnected()) {
                                        new Elige_Tutor().execute(new URL("http://manuamate.hol.es/eligetutor.php?usuario="+usuarioconectado+"&tutor="+tfg.getCodigoTutor()));
                                    } else {
                                        Toast.makeText(getBaseContext(), "Error de conexion", Toast.LENGTH_LONG).show();
                                    }

                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }



                                // Cuando termine de hacer la opcion que finalice


                                finish();

                            }

                        })
                        .setNegativeButton("No", null)
                        .show();






            }
        });

        // Boton de borrado

        btn_borratutor = (Button)findViewById(R.id.button_borratutor);

        btn_borratutor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                Log.i("Borra", "onClick: ");
                

                new AlertDialog.Builder(btn_borratutor.getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Borrar tutor")
                        .setMessage("¿Esta seguro que desea eliminar el tutor: "+tfg.getNombreTutor()+" "+tfg.getApellidosTutor())
                        .setPositiveButton("Si", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    ConnectivityManager connMgr = (ConnectivityManager)
                                            getSystemService(Context.CONNECTIVITY_SERVICE);

                                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                                    if (networkInfo != null && networkInfo.isConnected()) {
                                        new Elimina_Tutor().execute(new URL("http://manuamate.hol.es/eliminatutor.php?usuario="+usuarioconectado+"&tutor="+tfg.getCodigoTutor()));
                                    } else {
                                        Toast.makeText(getBaseContext(), "Error de conexion", Toast.LENGTH_LONG).show();
                                    }

                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }

                                // Cuando termine de hacer la opcion que finalice


                                finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();







            }
        });





        try {
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                new JsonTask2().execute(new URL("http://manuamate.hol.es/tfgtutor.php?dato="+tfg.getCodigoTutor()));
            } else {
                Toast.makeText(this, "Error de conexion", Toast.LENGTH_LONG).show();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }



    @Override
    protected void onRestart() {
        super.onRestart();  // Always call the superclass method first

        // Activity being restarted from stopped state
    }




    public class JsonTask2 extends AsyncTask<URL, Void, List<Tutor_tfg>> {

        @Override
        protected List<Tutor_tfg> doInBackground(URL... urls) {
            List<Tutor_tfg> Tfgs = null;

            try {

                // Establecer la conexion
                con = (HttpURLConnection)urls[0].openConnection();
                con.setConnectTimeout(15000);
                con.setReadTimeout(10000);

                // Obtener el estado del recurso
                int statusCode = con.getResponseCode();

                if(statusCode!=200) {
                    Tfgs = new ArrayList<>();
                    // MIRAR DESDE AQUI EN CASO DE ERROR
                    Tfgs.add(new Tutor_tfg("error","error"));

                } else {

                    // Parsear el flujo con formato JSON
                    InputStream in = new BufferedInputStream(con.getInputStream());

                    GsonTfgParser parser = new GsonTfgParser();

                    Tfgs = parser.leerFlujoJson2(in);

                }

            } catch (Exception e) {
                e.printStackTrace();

            }finally {
                con.disconnect();
            }
            return Tfgs;
        }

        @Override
        protected void onPostExecute(List<Tutor_tfg> Tfgs) {
            /*
            Asignar los objetos de Json parseados al adaptador
             */
            if(Tfgs !=null) {

                ArrayList<String> datos = new ArrayList<>();
                for(int i=0; i<Tfgs.size(); i++)
                {
                    datos.add(Tfgs.get(i).getNombreTfg());
                }


                listAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simplerow, datos);
                list.setAdapter(listAdapter);

            }else{
                Toast.makeText(
                        getBaseContext(),
                        "Ocurrio un error de Parsing Json",
                        Toast.LENGTH_SHORT)
                        .show();
            }

        }
    }


    public class Elige_Tutor extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

        String var = null;


            try {

                // Establecer la conexion
                con = (HttpURLConnection)urls[0].openConnection();
                con.setConnectTimeout(15000);
                con.setReadTimeout(10000);

                // Obtener el estado del recurso
                int statusCode = con.getResponseCode();

                if(statusCode!=200) {

                    // MIRAR DESDE AQUI EN CASO DE ERROR


                } else {

                    // Parsear el flujo con formato JSON





                }

            } catch (Exception e) {
                e.printStackTrace();

            }finally {
                con.disconnect();
            }
            return var;
        }



        @Override
        protected void onPostExecute(String variable) {
            /*
            Asignar los objetos de Json parseados al adaptador
             */

            Toast.makeText(
                    getBaseContext(),
                    "Ha elegido a : "+nombretutor.getText()+" "+apellidotutor.getText(),
                    Toast.LENGTH_LONG)
                    .show();

            if(variable !=null) {

                // Crear un toast si se ha guardado


            }else{

                // Crear un toast si no se ha guardado

            }

        }









    }




    public class Elimina_Tutor extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            String var = null;


            try {

                // Establecer la conexion
                con = (HttpURLConnection)urls[0].openConnection();
                con.setConnectTimeout(15000);
                con.setReadTimeout(10000);

                // Obtener el estado del recurso
                int statusCode = con.getResponseCode();

                if(statusCode!=200) {

                    // MIRAR DESDE AQUI EN CASO DE ERROR


                } else {

                    // Parsear el flujo con formato JSON





                }

            } catch (Exception e) {
                e.printStackTrace();

            }finally {
                con.disconnect();
            }
            return var;
        }



        @Override
        protected void onPostExecute(String variable) {
            /*
            Asignar los objetos de Json parseados al adaptador
             */

            Toast.makeText(
                    getBaseContext(),
                    "Ha eliminado a : "+nombretutor.getText()+" "+apellidotutor.getText(),
                    Toast.LENGTH_LONG)
                    .show();

            if(variable !=null) {

                // Crear un toast si se ha guardado


            }else{

                // Crear un toast si no se ha guardado

            }

        }









    }


}


