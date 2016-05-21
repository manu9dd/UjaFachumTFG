package com.example.manuel.ujafachumtfg;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.manuel.ujafachumtfg.R.layout.activity_main;


public class MainActivity extends AppCompatActivity {



    /*
 Variables globales
  */

     String usuarioconectado;

    ListView lista;
    ArrayAdapter adaptador;
    HttpURLConnection con;

    private DrawerLayout drawerLayout;

    // Mis fragmentos

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);



        usuarioconectado =  getIntent().getStringExtra("usuario");

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.logo_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navview);

        if(navView!=null)
        {CasesDrawerContent(navView);}


        ///  A PARTIR DE AQUI EL TEMARIO DE LA LISTA DEL JSON ETC


        lista= (ListView) findViewById(R.id.listaTfgs);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                Tfg tfg = ((AdaptadorDeTfgs)lista.getAdapter()).getItem(position);
                intent.putExtra("title", tfg);
                intent.putExtra("usuario",usuarioconectado);
                startActivity(intent);
                Log.i("TOCO", "entro");

            }
        });


        Intent intent = getIntent();
        String codiguito  = (String) intent.getSerializableExtra("titulacion");

        // Segun el codigo de titulacion recibe un parámetro u otro

    /*
        Comprobar la disponibilidad de la Red
         */
        try {
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                new JsonTask().execute(new URL("http://manuamate.hol.es/tfgs.php?dato="+codiguito+"&usuario="+usuarioconectado));
            } else {
                Toast.makeText(this, "Error de conexion", Toast.LENGTH_LONG).show();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            //...
        }

        return super.onOptionsItemSelected(item);
    }

    // Son los casos del Menu Drawer
    private void CasesDrawerContent(NavigationView navigationView){

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {


                        switch (menuItem.getItemId()) {
                            case R.id.menu_seccion_0:



                                break;
                            case R.id.menu_seccion_1:


                                break;


                            case R.id.menu_seccion_2:
                                // Salir
                                finish();
                                break;

                        }

                        menuItem.setChecked(true);
                        getSupportActionBar().setTitle(menuItem.getTitle());


                        drawerLayout.closeDrawers();

                        return true;
                    }
                });


    }



    public class JsonTask extends AsyncTask<URL, Void, List<Tfg>> {

        @Override
        protected List<Tfg> doInBackground(URL... urls) {
            List<Tfg> Tfgs = null;

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
                    Tfgs.add(new Tfg("error","error","error","error"));

                } else {

                    // Parsear el flujo con formato JSON
                    InputStream in = new BufferedInputStream(con.getInputStream());

                    GsonTfgParser parser = new GsonTfgParser();

                    Tfgs = parser.leerFlujoJson(in);

                }

            } catch (Exception e) {
                e.printStackTrace();

            }finally {
                con.disconnect();
            }
            return Tfgs;
        }

        @Override
        protected void onPostExecute(List<Tfg> Tfgs) {
            /*
            Asignar los objetos de Json parseados al adaptador
             */
            if(Tfgs !=null) {
                adaptador = new AdaptadorDeTfgs(getBaseContext(), Tfgs);
                lista.setAdapter(adaptador);
            }else{
                Toast.makeText(
                        getBaseContext(),
                        "Ocurrio un error de Parsing Json",
                        Toast.LENGTH_SHORT)
                        .show();
            }

        }
    }


    @Override
    public void onRestart() {
        super.onRestart();  // Always call the superclass method first

        // Activity being restarted from stopped state
    }



    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first

    }



    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first



        Intent intent = getIntent();
        String codiguito  = (String) intent.getSerializableExtra("titulacion");

        // Segun el codigo de titulacion recibe un parámetro u otro

    /*
        Comprobar la disponibilidad de la Red
         */
        try {
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                new JsonTask().execute(new URL("http://manuamate.hol.es/tfgs.php?dato="+codiguito+"&usuario="+usuarioconectado));
            } else {
                Toast.makeText(this, "Error de conexion", Toast.LENGTH_LONG).show();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



    }



}