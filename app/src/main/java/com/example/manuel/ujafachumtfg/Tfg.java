package com.example.manuel.ujafachumtfg;

import java.io.Serializable;

/**
 * Created by Manuel on 14/02/2016.
 */



public class Tfg implements Serializable{
    private String tutor;
    private String NombreProyecto;


    public Tfg(String NombreProyecto, String tutor) {
        this.tutor = tutor;
        this.NombreProyecto = NombreProyecto;

    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getNombreProyecto() {
        return NombreProyecto;
    }

    public void setNombreProyecto(String NombreProyecto) {
        this.NombreProyecto = NombreProyecto;
    }

}
