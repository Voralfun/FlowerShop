package com.example.flowershop.model.repository;

import com.example.flowershop.model.entity.Cart;
import com.example.flowershop.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByClientOrderByCreatedDateDesc(Client client);

}
