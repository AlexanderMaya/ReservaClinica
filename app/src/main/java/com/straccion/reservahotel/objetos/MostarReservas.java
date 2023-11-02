package com.straccion.reservahotel.objetos;

import java.util.ArrayList;
import java.util.List;

public class MostarReservas {
    private Integer idMedico;
    private String hora;
    private String fecha;
    private String lugar;

    public MostarReservas(Integer idMedico, String hora, String fecha, String lugar) {
        this.idMedico = idMedico;
        this.hora = hora;
        this.fecha = fecha;
        this.lugar = lugar;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
