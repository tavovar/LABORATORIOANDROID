package com.example.gustavovargas.lab2;

/**
 * Created by gustavovargas on 25/10/14.
 */
public class Usuario {

    private static Usuario usuario = null;
    public String nombre = "null";
    public String id = "null";

    protected Usuario(){

    }

    public static Usuario getElement(){
        if(usuario==null){
            usuario = new Usuario();
        }
        return usuario;
    }
}
