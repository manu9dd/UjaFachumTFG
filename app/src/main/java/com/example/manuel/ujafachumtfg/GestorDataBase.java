package com.example.manuel.ujafachumtfg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Manuel on 07/01/2016.
 */


public class GestorDataBase extends SQLiteOpenHelper {

    Context context;

    //Sentencia SQL para crear la tabla de listado de TFG
    String sqlCreate = "CREATE TABLE Alumno (email TEXT, contrasena TEXT, titulacion TEXT)";

    public GestorDataBase(Context contexto, String nombre,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
        context = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS TfgInfantil");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }
}