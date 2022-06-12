package com.example.biblioteca.entities;

public class Prestamo {
    private long id;
    private String fechaInicio;
    private String fechaFin;
    private boolean finalizado;
    private Libro libro;

    public Prestamo() {
    }

    public Prestamo(String fechaInicio, String fechaFin, Libro libro) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.finalizado = false;
        this.libro = libro;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
