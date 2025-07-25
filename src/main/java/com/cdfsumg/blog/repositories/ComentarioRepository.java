package com.cdfsumg.blog.repositories;

import com.cdfsumg.blog.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    
    // Aquí puedes definir métodos personalizados si es necesario
    // Por ejemplo, para encontrar comentarios por publicación o usuario
    // List<Comentario> findByIdPublicacion(Long idPublicacion);
    // List<Comentario> findByIdUsuario(Long idUsuario);
    
}
