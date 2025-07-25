package com.cdfsumg.blog.entities;

import java.security.Timestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "comentarios")
public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;
    private Timestamp fechaCreacion;
    private Long id_usuario; 
    private Long id_publicaion; 

    // Getters and Setters
    
}
