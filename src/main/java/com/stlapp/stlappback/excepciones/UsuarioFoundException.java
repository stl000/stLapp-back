package com.stlapp.stlappback.excepciones;

public class UsuarioFoundException extends Exception{

    public UsuarioFoundException(){
        super("El usuario con ese username ya existe en la base de datos. Vuelva a intentarlo");
    }

    public UsuarioFoundException(String message){
        super(message);
    }
}
