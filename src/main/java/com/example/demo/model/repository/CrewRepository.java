package com.example.demo.model.repository;

import com.example.demo.model.entity.Crew;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;

@Repository
public interface CrewRepository extends JpaRepository<Crew,Long> {
    Optional<Crew> findByTask(String task);
}
