package com.example.demo.model.entity;

import com.example.demo.model.enums.ShipStatus;
import com.example.demo.model.enums.Type;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "ships")
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ship_name")
    String name;
    @Column(name = "ship_type")
    Type type;
    @Column(name = "ship_length")
    Float length;
    @Column(unique = true)
    String serialNUM;
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    LocalDateTime createdAt;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @OneToMany(cascade = CascadeType.ALL)
    List<Crew> crew;
    @Enumerated(EnumType.STRING)
    ShipStatus status;
}
