package com.example.flowershop.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDTO {
    private Long id;
    String name;
    String surname;
    String patronymic;
    String email;
    String phoneNUM;
    String region;
    String adress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPhoneNUM() {
        return phoneNUM;
    }

    public void setPhoneNUM(String phoneNUM) {
        this.phoneNUM = phoneNUM;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public EmployeeDTO() {
    }
}
