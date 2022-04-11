package com.letscode.SWRSocialNetwork.service;


import com.letscode.SWRSocialNetwork.model.Rebel;

public interface SecurityService {

    String authenticate(final String username, final String password);

    void authenticate(final String token);

    Rebel getCurrentRebel();

}
