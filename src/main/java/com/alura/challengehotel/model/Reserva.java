package com.alura.challengehotel.model;

import java.sql.Date;

public class Reserva {

    private int id;
    private Date fechaEntrada;
    private Date fechaSalida;
    private Float valor;
    private String formaPago;

    public Reserva(Date fechaEntrada, Date fechaSalida, Float valor, String formaPago) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaPago = formaPago;
    }

    public int getId() {
        return id;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public Float getValor() {
        return valor;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setId(int id) {
        this.id = id;
    }
}
