package com.aluracursos.desafio.literatura.repository;

import com.aluracursos.desafio.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {
    @Query("SELECT DISTINCT a FROM Autor a")
    List<Autor> findAllWithoutDuplicates();

}
