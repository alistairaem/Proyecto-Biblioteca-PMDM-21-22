package com.example.biblioteca.entities;

public class Prestamo {
    private long id;
    private String fechaInicio;
    private String fechaFin;
    private int finalizado;
    private String libro;

    public Prestamo() {
    }

    public Prestamo(String fechaInicio, String fechaFin, String libro) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.finalizado = 0;
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

    public int getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(int finalizado) {
        this.finalizado = finalizado;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }
}
