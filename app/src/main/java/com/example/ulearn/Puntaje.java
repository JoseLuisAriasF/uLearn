package com.example.ulearn;

public class Puntaje {

    private String idUsuario;
    private String nombreJuego;
    private String puntaje;

    public Puntaje(String idUsuario, String nombreJuego, String puntaje) {
        this.idUsuario = idUsuario;
        this.nombreJuego = nombreJuego;
        this.puntaje = puntaje;
    }


    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombreJuego() {
        return nombreJuego;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }

}
