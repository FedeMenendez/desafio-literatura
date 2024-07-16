package com.aluracursos.desafio.literatura.service;

import com.aluracursos.desafio.literatura.model.Datos;
import com.aluracursos.desafio.literatura.model.DatosLibros;
import com.aluracursos.desafio.literatura.model.Idioma;
import com.aluracursos.desafio.literatura.model.Libro;
import com.aluracursos.desafio.literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class LibroService {
    @Autowired
    private ConvierteDatos conversor;
    @Autowired
    private LibroRepository libroRepository;

    public void buscarLibroPorTitulo(String libroBuscar, String json) {
        try {
            Datos datos = conversor.obtenerDatos(json, Datos.class);
            Optional<DatosLibros> libroBuscado = datos.resultados().stream()
                    .filter(l -> l.titulo().toUpperCase().contains(libroBuscar.toUpperCase()))
                    .findFirst();
            if (libroBuscado.isPresent()) {
                DatosLibros datosLibros = libroBuscado.get();
                Libro libro = new Libro(datosLibros);
                System.out.println("El libro encontrado es: " + libro);
                libroRepository.save(libro);
            } else {
                System.out.println("El libro no se ha encontrado");
            }
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    public List<Libro> listarLibrosPorIdioma(Idioma idioma) {
        List<Libro> libros = libroRepository.findByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No hay libros disponibles en el idioma " + idioma);
        } else {
            System.out.println("Los libros encontrados en " + idioma + "son: ");
            libros.forEach(System.out::println);
        }
        return libros;
    }

}
