package com.example.flowershop.model.repository;

import com.example.flowershop.model.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerRepository extends JpaRepository<Flower,Long> {
}
