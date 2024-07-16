package com.aluracursos.desafio.literatura.model;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    @Enumerated (EnumType.STRING)
    private Idioma idioma;

    private double numeroDescargas;

    public Libro(){
    }

    public Libro(DatosLibros datosLibros){
        this.titulo = datosLibros.titulo();
        this.autor = new Autor(datosLibros.autor().get(0));
        this.idioma = Idioma.valueOf(datosLibros.idiomas().get(0));
        this.numeroDescargas=datosLibros.numeroDescargas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }
}
