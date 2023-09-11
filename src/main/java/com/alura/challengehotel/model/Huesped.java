package com.alura.challengehotel.model;

import java.sql.Date;

public class Huesped {

    private Integer id;
    private String nombre;
    private String apellido;
    private Date fecha_nacimiento;
    private String nacionalidad;
    private String telefono;
    private Integer id_reserva;

    public Huesped(Integer id, String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono, Integer id_reserva) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.id_reserva = id_reserva;
    }

    public Huesped(String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono, Integer id_reserva) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.id_reserva = id_reserva;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public Integer getId_reserva() {
        return id_reserva;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
