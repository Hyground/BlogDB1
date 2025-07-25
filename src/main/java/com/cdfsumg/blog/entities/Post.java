package com.cdfsumg.blog.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "publicaciones")
public class Post {

    @Id
    private Long id;
    private String titulo;
    private String contenido;
    private String fechaPublicacion;
    private Long idUsuario;

    // Getters and Setters
}
