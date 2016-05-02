package com.example.manuel.ujafachumtfg;

import java.io.Serializable;

/**
 * Created by Manuel on 02/05/2016.
 */
public class Alumno implements Serializable {



    private String success;
    private String message;


    public Alumno(String success, String message) {

        this.success = success;
        this.message = message;

    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message= message;
    }



}
