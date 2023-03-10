package com.example.flowershop.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tokens")
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String token;

    @OneToOne(targetEntity = Client.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "client_id")
    private Client client;
    public Client getClient() {
        return client;
    }
    public AuthenticationToken(Client client) {
        this.client = client;
        this.token = UUID.randomUUID().toString();
    }

    public AuthenticationToken() {
    }
}
