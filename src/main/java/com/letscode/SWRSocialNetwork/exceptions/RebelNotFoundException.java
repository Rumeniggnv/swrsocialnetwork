package com.letscode.SWRSocialNetwork.exceptions;

public class RebelNotFoundException extends RuntimeException {
    public RebelNotFoundException() {
        super("Rebel not found");
    }

    public RebelNotFoundException(String message) {
        super(message);
    }
}
