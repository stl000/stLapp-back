package com.stlapp.stlappback.excepciones;

public class UsuarioNotFoundException extends Exception{

    public UsuarioNotFoundException(){
        super("El usuario con ese username no existe en la base de datos. Vuelva a intentarlo");
    }

    public UsuarioNotFoundException(String message){
        super(message);
    }
}
