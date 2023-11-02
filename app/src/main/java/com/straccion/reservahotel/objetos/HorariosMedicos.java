package com.straccion.reservahotel.objetos;

public class HorariosMedicos{
    private String hora;
    private String clinica;
    private String Medico;
    private String fecha;

    public HorariosMedicos(String hora, String clinica, String medico, String fecha) {
        this.hora = hora;
        this.clinica = clinica;
        this.fecha = fecha;
        Medico = medico;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getClinica() {
        return clinica;
    }

    public void setClinica(String clinica) {
        this.clinica = clinica;
    }

    public String getMedico() {
        return Medico;
    }

    public void setMedico(String medico) {
        Medico = medico;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
