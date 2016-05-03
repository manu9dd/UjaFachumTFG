package com.example.manuel.ujafachumtfg;

import java.io.Serializable;

/**
 * Created by Manuel on 02/05/2016.
 */
public class Alumno implements Serializable {



    private String Usuario;
    private String CodigoTitulacion;


    public Alumno(String usuario, String codigoTitulacion) {

        this.Usuario = usuario;
        this.CodigoTitulacion = codigoTitulacion;

    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        this.Usuario = usuario;
    }

    public String getCodigoTitulacion() {
        return CodigoTitulacion;
    }

    public void setCodigoTitulacion(String codigoTitulacion) {
        this.CodigoTitulacion = codigoTitulacion;
    }



}
