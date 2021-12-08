package com.Alkemy.disneyCharacters.exception;

public class ParamNotFound extends RuntimeException {
    public ParamNotFound(String error) {
        super(error);
    }
}
