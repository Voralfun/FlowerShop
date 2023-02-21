package com.example.flowershop.model.repository;

import com.example.flowershop.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface ClientRepository extends JpaRepository<Client, Long>{

    }
