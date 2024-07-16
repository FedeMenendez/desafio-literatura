package com.aluracursos.desafio.literatura.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;

import java.util.Map;

public enum Idioma {
    INGLES("[en]"),
    CASTELLANO("[es]"),
    FRANCES("[fr]"),
    PORTUGuES("[pt]");

    private final String codigo;

    Idioma(String codigo) {
        this.codigo = codigo;
    }

    @JsonValue
    public String getCodigo() {
        return codigo;
    }

    @JsonCreator
    public static Idioma fromString(String codigo) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.getCodigo().equalsIgnoreCase(codigo)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Valor desconocido: " + codigo);
    }

    public static String obtenerCodigoPorNombre(String nombreIdioma) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.name().equalsIgnoreCase(nombreIdioma)) {
                return idioma.getCodigo();
            }
        }
        throw new IllegalArgumentException("Nombre de idioma desconocido: " + nombreIdioma);
    }
}

