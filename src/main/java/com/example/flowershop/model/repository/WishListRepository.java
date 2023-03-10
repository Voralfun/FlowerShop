package com.example.flowershop.model.repository;

import com.example.flowershop.model.entity.Client;
import com.example.flowershop.model.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    List<WishList> findAllByClientOrderByCreatedDateDesc(Client client);

}
