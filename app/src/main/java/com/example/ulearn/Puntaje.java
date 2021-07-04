package com.example.ulearn;

public class Puntaje {

    private String idUsuario;
    private String nombreUsuario;
    private String correoUsuario;
    private String nombreJuego;
    private int puntaje;

    public Puntaje(){

    }

    public Puntaje(String idUsuario, String nombreUsuario, String correoUsuario, String nombreJuego, int puntaje) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correoUsuario = correoUsuario;
        this.nombreJuego = nombreJuego;
        this.puntaje = puntaje;
    }


    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public String getNombreJuego() {
        return nombreJuego;
    }

    public int getPuntaje() {
        return puntaje;
    }


    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}
