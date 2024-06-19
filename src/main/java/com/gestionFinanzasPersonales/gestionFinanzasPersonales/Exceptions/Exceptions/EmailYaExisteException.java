package com.gestionFinanzasPersonales.gestionFinanzasPersonales.Exceptions.Exceptions;

public class EmailYaExisteException extends RuntimeException {
    public EmailYaExisteException(String mail){
        super("El mail '" + mail + "' ya está registrado!");
    }
}
