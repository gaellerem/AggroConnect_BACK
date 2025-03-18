package com.aggroConnect.api.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Long id) {
        super(resource+ " n'a pas été trouvé avec cet id : " + id);
    }
}
