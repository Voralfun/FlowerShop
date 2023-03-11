package com.example.flowershop.controller;

import com.example.flowershop.model.dto.ResponseDTO;
import com.example.flowershop.model.dto.client.SignInDTO;
import com.example.flowershop.model.dto.client.SignInResponseDTO;
import com.example.flowershop.model.dto.client.SignUpDTO;
import com.example.flowershop.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")

    public class ClientController {
        @Autowired
        ClientService clientService;

    @PostMapping("/signup")
    public ResponseDTO signup(@RequestBody SignUpDTO signupDTO) {
        return clientService.signUp(signupDTO);
    }

    @PostMapping("/signin")
    public SignInResponseDTO signIn(@RequestBody SignInDTO signInDTO) {
        return clientService.signIn(signInDTO);
    }

    }

