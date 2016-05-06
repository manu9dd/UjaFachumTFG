package com.example.manuel.ujafachumtfg;

import java.io.Serializable;

/**
 * Created by Manuel on 14/02/2016.
 */



public class Tfg implements Serializable{

    private String ApellidosTutor;

    private String NombreTutor;

    private String CodigoTutor;


    public Tfg(String NombreTutor, String tutor, String CodigoTutor) {
        this.ApellidosTutor = tutor;
        this.NombreTutor = NombreTutor;
        this.CodigoTutor = CodigoTutor;

    }

    public String getApellidosTutor() {return ApellidosTutor;}

    public void setApellidosTutor(String tutor) {
        this.ApellidosTutor = tutor;
    }


    public String getNombreTutor(){return NombreTutor;}
    public void setNombreTutor(String NombreTutor){this.NombreTutor = NombreTutor;}



    public String getCodigoTutor(){return CodigoTutor;}

    public void setCodigoTutor(String CodigoTutor){this.CodigoTutor = CodigoTutor;}

}
