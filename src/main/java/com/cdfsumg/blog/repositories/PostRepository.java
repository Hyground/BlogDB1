package com.cdfsumg.blog.repositories;

import com.cdfsumg.blog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    
    // Aquí puedes definir métodos personalizados si es necesario
    // Por ejemplo, para encontrar publicaciones por título o autor
    // List<Post> findByTitulo(String titulo);
    // List<Post> findByIdUsuario(Long idUsuario);
    
}
