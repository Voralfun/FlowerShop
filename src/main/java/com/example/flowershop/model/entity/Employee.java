package com.example.flowershop.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "employee_name")
    String name;
    @Column(name = "employee_surname")
    String surname;
    @Column(name = "employee_patronymic")
    String patronymic;
    @Column(name = "email")
    String email;
    @Column(name = "phoneNUM")
    Integer phoneNUM;
    String region;
    String adress;
}

