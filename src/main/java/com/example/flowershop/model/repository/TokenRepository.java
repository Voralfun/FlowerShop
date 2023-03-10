package com.example.flowershop.model.repository;

import com.example.flowershop.model.entity.AuthenticationToken;
import com.example.flowershop.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Long> {
    AuthenticationToken findByClient(Client client);
    AuthenticationToken findByToken(String token);
}
