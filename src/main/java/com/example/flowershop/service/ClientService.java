package com.example.flowershop.service;

import com.example.flowershop.model.dto.ResponseDTO;
import com.example.flowershop.model.dto.client.SignInDTO;
import com.example.flowershop.model.dto.client.SignInResponseDTO;
import com.example.flowershop.model.dto.client.SignUpDTO;
import com.example.flowershop.model.entity.Client;

public interface ClientService {


    ResponseDTO signUp(SignUpDTO signupDto);
    SignInResponseDTO signIn(SignInDTO signInDTO);
}
