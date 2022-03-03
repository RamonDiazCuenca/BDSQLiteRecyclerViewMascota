package com.rdc.bdsqliterecyclerview.entidades;

public class Mascota {

    private Integer id;
    private String dueño;
    private String nombre;
    private String raza;

    public Mascota(){

    }

    public Mascota(Integer id,String dueño, String nombre, String raza) {
        this.id = id;
        this.dueño = dueño;
        this.nombre = nombre;
        this.raza = raza;
    }

    public String getDueño() {
        return dueño;
    }

    public void setDueño(String dueño) {
        this.dueño = dueño;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
}
