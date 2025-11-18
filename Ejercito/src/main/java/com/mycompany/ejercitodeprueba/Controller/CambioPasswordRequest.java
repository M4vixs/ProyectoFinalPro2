package com.mycompany.ejercitodeprueba.Controller;

class CambioPasswordRequest {
    private int idUsuario;
    private String nuevoPassword;

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getNuevoPassword() { return nuevoPassword; }
    public void setNuevoPassword(String nuevoPassword) { this.nuevoPassword = nuevoPassword; }
}