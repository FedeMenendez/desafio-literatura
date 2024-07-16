package com.aluracursos.desafio.literatura.service;

import com.aluracursos.desafio.literatura.model.Autor;
import com.aluracursos.desafio.literatura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class AutorService {
    @Autowired
    private AutorRepository autorRepository;


    public List<String> listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();


        return autores.stream()
                .sorted((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()))
                .map(Autor::toString)
                .collect(Collectors.toList());
    }

    public List<Autor> getAutoresVivosEnAnio(int anio) {
        return autorRepository.findAll().stream()
                .filter(autor -> autor.getFechaDeNacimiento() != null && autor.getFechaDeNacimiento() <= anio)
                .filter(autor -> autor.getFechaDeMuerte() == null || autor.getFechaDeMuerte() >= anio)
                .collect(Collectors.toList());
    }


    public void listarAutoresVivosEnAnio(int anio) {
        List<Autor> autoresVivos = getAutoresVivosEnAnio(anio);

        if (autoresVivos.isEmpty()) {
            System.out.println("No hay autores vivos en el año " + anio + ".");
        } else {
            System.out.println("Autores vivos en el año " + anio + ":");
            autoresVivos.forEach(System.out::println);
        }
    }
}