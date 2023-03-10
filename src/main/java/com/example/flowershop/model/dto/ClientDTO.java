package com.example.flowershop.model.dto;

import com.example.flowershop.model.entity.Cart;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Data
public class ClientDTO {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private LocalDate birthdate;
    private Integer phoneNUM;
    private Cart cart;

    public ClientDTO(String email) {
        this.email = email;
    }

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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getPhoneNUM() {
        return phoneNUM;
    }

    public void setPhoneNUM(Integer phoneNUM) {
        this.phoneNUM = phoneNUM;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
