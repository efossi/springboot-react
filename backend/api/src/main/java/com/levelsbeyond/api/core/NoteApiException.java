package com.levelsbeyond.api.core;

public class NoteApiException extends RuntimeException {

    private static final long serialVersionUID = -3951650158104514999L;

    public NoteApiException(String message) {
        super(message);
    }

    public NoteApiException(String message, Throwable e) {
        super(message, e);
    }
}