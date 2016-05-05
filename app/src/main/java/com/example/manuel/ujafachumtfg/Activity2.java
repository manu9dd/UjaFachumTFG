package com.example.manuel.ujafachumtfg;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    private TextView tutor;
    private TextView proyecto;
    private TextView codigotutor;


    // Botones de Eleccion y borrado del tutor
    private Button btn_eligetutor;
    private Button btn_borratutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        this.tutor = (TextView) findViewById(R.id.tutor);
        this.proyecto = (TextView) findViewById(R.id.proyecto);
        this.codigotutor = (TextView) findViewById(R.id.codigotutor);



        Intent intent = getIntent();
        Tfg tfg  = (Tfg)intent.getSerializableExtra("title");
        this.tutor.setText(tfg.getTutor());
        this.proyecto.setText(tfg.getNombreProyecto());
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



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
