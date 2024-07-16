package com.aluracursos.desafio.literatura.repository;

import com.aluracursos.desafio.literatura.model.Idioma;
import com.aluracursos.desafio.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
    List<Libro> findByIdioma(Idioma lenguaje);
}
