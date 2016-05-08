package com.example.manuel.ujafachumtfg;

import java.io.Serializable;

/**
 * Created by Manuel on 07/05/2016.
 */
public class Tutor_tfg implements Serializable {


    private String CodigoTfg;
    private String NombreTfg;


    public Tutor_tfg(String CodigoTfg, String NombreTfg) {

        this.CodigoTfg = CodigoTfg;
        this.NombreTfg = NombreTfg;
    }


    public String getCodigoTfg() {return CodigoTfg;}

    public void setCodigoTfg(String CodigoTfg) {
        this.CodigoTfg = CodigoTfg;
    }


    public String getNombreTfg() {return NombreTfg;}

    public void setNombreTfg(String NombreTfg) {
        this.NombreTfg = NombreTfg;
    }


}
