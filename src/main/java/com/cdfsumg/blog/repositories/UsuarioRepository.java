package com.cdfsumg.blog.repositories;

import com.cdfsumg.blog.entities.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByNombreUsuarioAndCorreo(String nombreUsuario, String correo);
}
