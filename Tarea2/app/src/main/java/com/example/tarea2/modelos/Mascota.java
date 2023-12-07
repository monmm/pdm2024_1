package com.example.tarea2.modelos;

public class Mascota {

    private int id;
    private String nombre;
    private String sexo;
    private String nacimiento;
    private String raza;
    private String dueno;
    private String telefono;
    private String direccion;
    private String cp;

    public Mascota() {}

    public Mascota(String nombre, String sexo, String nacimiento, String raza, String dueno, String telefono, String direccion, String cp) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.nacimiento = nacimiento;
        this.raza = raza;
        this.dueno = dueno;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cp = cp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                "\nCumpleaños: " + nacimiento +
                "\nSexo: " + sexo +
                "\t\tRaza: " + raza;
    }
}
