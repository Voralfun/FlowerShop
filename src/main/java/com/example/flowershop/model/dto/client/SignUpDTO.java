package com.example.flowershop.model.dto.client;

public class SignUpDTO {
    String name;
    String surname;
    String patronymic;
    String email;
    String password;
    String PhoneNUM;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNUM() {
        return PhoneNUM;
    }

    public void setPhoneNUM(String phoneNUM) {
        PhoneNUM = phoneNUM;
    }
}
