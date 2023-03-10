package com.example.flowershop.service;

import com.example.flowershop.exceptions.AuthenticationFailException;
import com.example.flowershop.model.entity.AuthenticationToken;
import com.example.flowershop.model.entity.Client;

public interface AuthenticationService {
    void saveConfirmationToken(AuthenticationToken authenticationToken);
    AuthenticationToken getToken(Client client);
    Client getClient(String token);
    void authenticate(String token) throws AuthenticationFailException;
}
