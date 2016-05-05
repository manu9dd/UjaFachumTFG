package com.example.manuel.ujafachumtfg;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Manuel on 14/02/2016.
 */

public class AdaptadorDeTfgs extends ArrayAdapter<Tfg> {

    private List<Tfg> list;

    public AdaptadorDeTfgs(Context context, List<Tfg> objects) {
        super(context, 0, objects);
        this.list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Salvando la referencia del View de la fila
        View v = convertView;

        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo
            v = inflater.inflate(
                    R.layout.item_lista,
                    parent,
                    false);
        }

        //Obteniendo instancias de los elementos
        TextView tutorTfg = (TextView)v.findViewById(R.id.tutorTfg);
        TextView NombreProyectoTfg = (TextView)v.findViewById(R.id.NombreProyectoTfg);
        TextView CodigoTutorTfg = (TextView)v.findViewById(R.id.CodigoTutorTfg);



        //Obteniendo instancia de la Tarea en la posición actual
        Tfg item = getItem(position);

        tutorTfg.setText(item.getTutor());
        NombreProyectoTfg.setText(item.getNombreProyecto());
        CodigoTutorTfg.setText(item.getCodigoTutor());



        //Devolver al ListView la fila creada
        return v;

    }


    public Tfg getItem(int pos){
        return this.list.get(pos);
    }
}
