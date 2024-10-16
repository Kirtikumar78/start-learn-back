package com.elearn.app.exceptions;

import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super("Resource not Found");
    }

    public ResourceNotFoundException(String courseNotFound){
        super(courseNotFound);

    }
}
