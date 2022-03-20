package com.letscode.SWRSocialNetwork.model;

public enum Resource {
    COMIDA("Comida"),
    AGUA("Água"),
    MUNICAO("Munição"),
    ARMA("Arma");

    private String resource;

    Resource(String resource) { this.resource = resource; }
}
