package com.sentra.usuario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre no puede ser nulo o vacío")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "El correo electrónico no puede ser nulo o vacío")
    @Email(message = "El correo electrónico no sigue el formato correcto")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "La contraseña no puede ser nula o vacía")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,}$", message = "La contraseña debe tener al menos 7 caracteres, incluyendo al menos una letra y un número")
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Telefono> phones;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime modified;

    private LocalDateTime last_login;

    private String token;

    private boolean isactive;

}