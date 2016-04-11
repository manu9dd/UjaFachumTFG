package com.example.manuel.ujafachumtfg;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    private TextView tutor;
    private TextView proyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        this.tutor = (TextView) findViewById(R.id.tutor);
        this.proyecto = (TextView) findViewById(R.id.proyecto);

        Intent intent = getIntent();
        Tfg tfg  = (Tfg)intent.getSerializableExtra("title");
        this.tutor.setText(tfg.getTutor());
        this.proyecto.setText(tfg.getNombreProyecto());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
