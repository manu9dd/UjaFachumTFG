package com.example.manuel.ujafachumtfg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {


    private TextView TituloCabecera;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TituloCabecera = (TextView) findViewById(R.id.TituloPrincipal);

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }



}
