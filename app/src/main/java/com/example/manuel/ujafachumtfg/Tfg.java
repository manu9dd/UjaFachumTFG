package com.example.manuel.ujafachumtfg;

import java.io.Serializable;

/**
 * Created by Manuel on 14/02/2016.
 */



public class Tfg implements Serializable{

    private String ApellidosTutor;

    private String CodigoTutor;


    public Tfg(String NombreProyecto, String tutor, String CodigoTutor) {
        this.ApellidosTutor = tutor;

        this.CodigoTutor = CodigoTutor;

    }

    public String getTutor() {
        return ApellidosTutor;
    }

    public void setTutor(String tutor) {
        this.ApellidosTutor = tutor;
    }





    public String getCodigoTutor(){return CodigoTutor;}

    public void setCodigoTutor(String CodigoTutor){this.CodigoTutor = CodigoTutor;}

}
