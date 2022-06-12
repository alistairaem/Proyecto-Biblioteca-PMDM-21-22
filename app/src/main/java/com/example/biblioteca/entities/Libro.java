package com.example.biblioteca.entities;

public class Libro {
    private long id;
    private String titulo;
    private String autor;
    private String genero;
    private String subgenero;
    private int leido;

    public Libro() {
    }

    public Libro(String titulo, String autor, String genero, String subgenero, int leido) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.subgenero = subgenero;
        this.leido = leido;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSubgenero() {
        return subgenero;
    }

    public void setSubgenero(String subgenero) {
        this.subgenero = subgenero;
    }

    public int getLeido() {
        return leido;
    }

    public void setLeido(int leido) {
        this.leido = leido;
    }
}
