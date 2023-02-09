package com.example.demo.model.repository;

import com.example.demo.model.entity.Ship;
import com.example.demo.model.enums.ShipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship,Long> {
    Optional<Ship> findByserialNUM(String serialNUM);

    List<Ship> findAllByStatus(ShipStatus status);
}
