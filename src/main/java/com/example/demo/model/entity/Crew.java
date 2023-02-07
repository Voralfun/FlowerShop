package com.example.demo.model.entity;

import com.example.demo.model.enums.CrewStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "crew")
public class Crew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "crew_type")
    String crewType;
    @Column(name = "crew_number")
    Integer crewNumber;
    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime updatedAt;
    @ManyToOne(cascade = CascadeType.ALL)
    Ship ship;
    String task;
    @Enumerated(EnumType.STRING)
    CrewStatus status = CrewStatus.CREATED;
}
