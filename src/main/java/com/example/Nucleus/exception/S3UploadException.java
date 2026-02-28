package com.example.Nucleus.exception;

public class S3UploadException extends RuntimeException{
    public S3UploadException(String message) {
        super(message);
    }
}
