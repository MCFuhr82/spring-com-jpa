package com.blinkspace.springjpa.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException (Object id) {
        super("Resource not found. Id " + id); //chamado o construtor da superclasse com o super(), passando a mensagem que irá aparecer.
    }
}
