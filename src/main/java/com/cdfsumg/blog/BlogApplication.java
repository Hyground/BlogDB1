package com.cdfsumg.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cdfsumg.blog.entities.*;
import com.cdfsumg.blog.repositories.*;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Usuario usuario = mostrarMenuUsuario();
        mostrarMenuPublicaciones(usuario);
    }

    private Usuario mostrarMenuUsuario() {
        while (true) {
            System.out.println("\nMENÚ PRINCIPAL");
            System.out.println("1. Crear usuario");
            System.out.println("2. Seleccionar usuario existente");
            System.out.print("Elige una opción: ");
            String opcion = scanner.nextLine();

            if (opcion.equals("1")) {
                return crearUsuario();
            } else if (opcion.equals("2")) {
                return seleccionarUsuario();
            } else {
                System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }
    }

    private Usuario crearUsuario() {
        System.out.print("Nombre de usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombre);
        usuario.setCorreo(correo);

        return usuarioRepository.save(usuario);
    }

    private Usuario seleccionarUsuario() {
        System.out.print("Ingresa nombre de usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingresa correo: ");
        String correo = scanner.nextLine();

        return usuarioRepository.findByNombreUsuarioAndCorreo(nombre, correo)
                .orElseGet(() -> {
                    System.out.println("Usuario no encontrado. Creando uno nuevo...");
                    Usuario nuevo = new Usuario();
                    nuevo.setNombreUsuario(nombre);
                    nuevo.setCorreo(correo);
                    return usuarioRepository.save(nuevo);
                });
    }

    private void mostrarMenuPublicaciones(Usuario usuario) {
        while (true) {
            System.out.println("\nMENÚ PUBLICACIONES");
            System.out.println("1. Crear publicación");
            System.out.println("2. Buscar publicaciones");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    crearPublicacion(usuario);
                    break;
                case "2":
                    buscarPublicaciones(usuario);
                    break;
                case "3":
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private void crearPublicacion(Usuario usuario) {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Contenido: ");
        String contenido = scanner.nextLine();

        Post post = new Post();
        post.setTitulo(titulo);
        post.setContenido(contenido);
        post.setFechaPublicacion(LocalDate.now());
        post.setIdUsuario(usuario.getId());

        postRepository.save(post);
        System.out.println("Publicación guardada con éxito.");
    }

    private void buscarPublicaciones(Usuario usuarioActivo) {
        List<Post> publicaciones = postRepository.findAll();

        if (publicaciones.isEmpty()) {
            System.out.println("No hay publicaciones disponibles.");
            return;
        }

        int i = 1;
        Map<Integer, Post> mapa = new HashMap<>();
        System.out.println("\nPublicaciones:");
        for (Post post : publicaciones) {
            Optional<Usuario> autor = usuarioRepository.findById(post.getIdUsuario());
            System.out.println(i + ". " + autor.map(Usuario::getNombreUsuario).orElse("(desconocido)") + " - " + post.getTitulo());
            mapa.put(i++, post);
        }

        System.out.println("\n1. Agregar comentario");
        System.out.println("2. Salir");
        System.out.print("Elige una opción: ");
        String opcion = scanner.nextLine();

        if (opcion.equals("1")) {
            System.out.print("Ingresa el número de publicación: ");
            int numero = Integer.parseInt(scanner.nextLine());
            Post postSeleccionado = mapa.get(numero);

            if (postSeleccionado != null) {
                System.out.print("Comentario: ");
                String texto = scanner.nextLine();

                Comentario comentario = new Comentario();
                comentario.setTexto(texto);
                comentario.setIdUsuario(usuarioActivo.getId());
                comentario.setIdPublicacion(postSeleccionado.getId());
                comentarioRepository.save(comentario);

                System.out.println("Comentario agregado con éxito.");
            } else {
                System.out.println("Número inválido.");
            }
        }
    }
}
