package com.straccion.reservahotel;

public class HorariosMedicos{
    private String hora;
    private String clinica;
    private String Medico;

    public HorariosMedicos(String hora, String clinica, String medico) {
        this.hora = hora;
        this.clinica = clinica;
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

}
