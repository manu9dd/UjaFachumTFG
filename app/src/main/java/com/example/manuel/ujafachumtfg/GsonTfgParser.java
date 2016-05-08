package com.example.manuel.ujafachumtfg;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manuel on 14/02/2016.
 */
public class GsonTfgParser {



    // Gson para la lista de tutores del main activity
    public List<Tfg> leerFlujoJson(InputStream in) throws IOException {
        // Nueva instancia de la clase Gson
        Gson gson = new Gson();

        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Tfg> Tfgs = new ArrayList<>();

        // Iniciar el array
        reader.beginArray();

        while (reader.hasNext()) {
            // Lectura de objetos
            Tfg tfgg = gson.fromJson(reader, Tfg.class);
            Tfgs.add(tfgg);
        }


        reader.endArray();
        reader.close();
        return Tfgs;
    }


    // Gson para la lista de los tfgs de cada tutor del activity 2
    public List<Tutor_tfg> leerFlujoJson2(InputStream in) throws IOException{



        // Nueva instancia de la clase Gson
        Gson gson = new Gson();

        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Tutor_tfg> Tfgs = new ArrayList<>();

        // Iniciar el array
        reader.beginArray();

        while (reader.hasNext()) {
            // Lectura de objetos
            Tutor_tfg tfgg = gson.fromJson(reader, Tutor_tfg.class);
            Tfgs.add(tfgg);
        }


        reader.endArray();
        reader.close();
        return Tfgs;

    }



    // GSon para la autenticacion del alumno
    public Alumno leerFlujoJsonAutenticacion(InputStream in) throws IOException {
        // Nueva instancia de la clase Gson
        Gson gson = new Gson();

        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

        Alumno autentico = null;



            // Lectura de objetos
            autentico = gson.fromJson(reader, Alumno.class);




        reader.close();
        return autentico;
    }



}

