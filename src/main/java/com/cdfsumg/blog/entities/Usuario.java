package com.cdfsumg.blog.entities;

//Imports
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    @Id
    private Long id;

    private String nombreUsuario;
    private String correo;
}
