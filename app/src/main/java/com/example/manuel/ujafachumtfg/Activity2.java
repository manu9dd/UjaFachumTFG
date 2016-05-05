package com.example.manuel.ujafachumtfg;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity {


    HttpURLConnection con;

    private TextView tutor;
    private TextView codigotutor;


    // Botones de Eleccion y borrado del tutor
    private Button btn_eligetutor;
    private Button btn_borratutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        this.tutor = (TextView) findViewById(R.id.tutor);

        this.codigotutor = (TextView) findViewById(R.id.codigotutor);



        Intent intent = getIntent();
        Tfg tfg  = (Tfg)intent.getSerializableExtra("title");
        this.tutor.setText(tfg.getTutor());

        this.codigotutor.setText(tfg.getCodigoTutor());



        // Boton de elegir

        btn_eligetutor = (Button)findViewById(R.id.button_eligetutor);

        btn_eligetutor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                Log.i("Elige", "onClick: ");
            }
        });

        // Boton de borrado

        btn_borratutor = (Button)findViewById(R.id.button_borratutor);

        btn_borratutor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                Log.i("Borra", "onClick: ");
            }
        });




    }



    public void ejecuta_Eligetutor(String url_Elige){



        try {
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                new Elige_Tutor().execute(new URL(url_Elige));
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
            if(variable !=null) {

                // Crear un toast si se ha guardado


            }else{

                // Crear un toast si no se ha guardado

            }

        }





    }



}
