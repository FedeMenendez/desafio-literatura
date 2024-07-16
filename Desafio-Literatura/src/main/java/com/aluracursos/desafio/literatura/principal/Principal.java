package com.aluracursos.desafio.literatura.principal;
import com.aluracursos.desafio.literatura.model.Autor;
import com.aluracursos.desafio.literatura.model.Idioma;
import com.aluracursos.desafio.literatura.model.Libro;
import com.aluracursos.desafio.literatura.repository.AutorRepository;
import com.aluracursos.desafio.literatura.repository.LibroRepository;
import com.aluracursos.desafio.literatura.service.AutorService;
import com.aluracursos.desafio.literatura.service.ConsumoApi;
import com.aluracursos.desafio.literatura.service.ConvierteDatos;
import com.aluracursos.desafio.literatura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private static final String url ="https://gutendex.com/books/";
    private ConsumoApi consumoAPI =new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado =new Scanner(System.in);

    private List<Libro> libro;

    @Autowired
    private LibroService libroService;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorService autorService;
    @Autowired
    private AutorRepository autorRepository;

    public void muestraMenu() {


        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libros por titulo 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivosEnDeterminadoAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("¿En qué idioma desea buscar su libro?");
        String idiomaStr = teclado.nextLine().toLowerCase();

        if ("español".equalsIgnoreCase(idiomaStr)) {
            idiomaStr = "CASTELLANO";
        }

        try {
            Idioma idioma = Idioma.valueOf(idiomaStr.toUpperCase());
            libroService.listarLibrosPorIdioma(idioma);
        } catch (IllegalArgumentException e) {
            System.out.println("El idioma ingresado no es válido.");
        }
    }

    private void listarAutoresVivosEnDeterminadoAnio() {
        System.out.println("Introduce el año para listar los autores vivos:");
        while (!teclado.hasNextInt()) {
            System.out.println("Formato inválido, ingrese un número válido para el año");
            teclado.nextLine();
        }
        int anio = teclado.nextInt();
        teclado.nextLine();

        autorService.listarAutoresVivosEnAnio(anio);
    }

    private void listarAutores() {
        List<String> autoresRegistrados = autorService.listarAutoresRegistrados();

        System.out.println("Autores Registrados:");
        autoresRegistrados.forEach(System.out::println);
    }

    private void listarLibros() {
        List<Libro> libros = libroRepository.findAll();
        libros.forEach(System.out::println);
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        String libroBuscar=teclado.nextLine();
        String json = consumoAPI.obtenerDatos(url + "?search=" + libroBuscar.replace(" ", "+").toLowerCase());
        libroService.buscarLibroPorTitulo(libroBuscar, json);
    }
}

