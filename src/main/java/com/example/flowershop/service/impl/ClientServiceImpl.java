package com.example.flowershop.service.impl;

import com.example.flowershop.exceptions.AuthenticationFailException;
import com.example.flowershop.exceptions.FlowerServiceException;
import com.example.flowershop.model.dto.ResponseDTO;
import com.example.flowershop.model.dto.client.SignInDTO;
import com.example.flowershop.model.dto.client.SignInResponseDTO;
import com.example.flowershop.model.dto.client.SignUpDTO;
import com.example.flowershop.model.entity.AuthenticationToken;
import com.example.flowershop.model.entity.Client;
import com.example.flowershop.model.repository.ClientRepository;
import com.example.flowershop.service.AuthenticationService;
import com.example.flowershop.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Transactional
    public ResponseDTO signUp(SignUpDTO signupDto) {
        if (Objects.nonNull(clientRepository.findByEmail(signupDto.getEmail()))) {
            throw new FlowerServiceException("Такой пользователь уже существует");
        }
        String encryptedpassword = signupDto.getPassword();

        try {
            encryptedpassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Client client = new Client(signupDto.getName(), signupDto.getSurname(),signupDto.getPatronymic(),
                signupDto.getEmail(),signupDto.getPhoneNUM(), encryptedpassword);

        clientRepository.save(client);

        final AuthenticationToken authenticationToken = new AuthenticationToken(client);

        authenticationService.saveConfirmationToken(authenticationToken);

        ResponseDTO responseDto = new ResponseDTO("Успешно", "Аккаунт успешно создан");
        client.setCreatedAt(LocalDateTime.now());
        return responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDTO signIn(SignInDTO signInDTO) {
        Client client = clientRepository.findByEmail(signInDTO.getEmail());

        if (Objects.isNull(client)) {
            throw new AuthenticationFailException("Пользователь не найден");
        }

        try {
            if (!client.getPassword().equals(hashPassword(signInDTO.getPassword()))) {
                throw new AuthenticationFailException("Не правильный пароль");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        AuthenticationToken token = authenticationService.getToken(client);


        if (Objects.isNull(token)) {
            throw new FlowerServiceException("Токен не найден");
        }

        return new SignInResponseDTO("Успешно", token.getToken());

    }

}
