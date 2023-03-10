package com.example.flowershop.service.impl;

import com.example.flowershop.exceptions.AuthenticationFailException;
import com.example.flowershop.model.entity.AuthenticationToken;
import com.example.flowershop.model.entity.Client;
import com.example.flowershop.model.repository.TokenRepository;
import com.example.flowershop.service.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    TokenRepository tokenRepository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(Client client) {
        return tokenRepository.findByClient(client);
    }


    public Client getClient(String token) {
        final AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
        if(Objects.isNull(authenticationToken)) {
            return null;
        }
        return authenticationToken.getClient();
    }

    public void authenticate(String token) throws AuthenticationFailException {
        if(Objects.isNull(token)) {
            throw new AuthenticationFailException("Не найден токен");
        }
        if(Objects.isNull(getClient(token))) {
            throw new AuthenticationFailException("Токен недействителен");
        }
    }
}
