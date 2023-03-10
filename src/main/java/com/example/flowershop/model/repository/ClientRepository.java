package com.example.flowershop.model.repository;

import com.example.flowershop.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
    public interface ClientRepository extends JpaRepository<Client, Long>{
    Client findByEmail(String email);
    }
