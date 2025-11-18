package com.mycompany.ejercitodeprueba.Controller;

class AsignacionRequest {
    private int idUsuario;
    private int idServicio;

    // Getters y Setters (necesarios para que Spring lea el JSON)
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public int getIdServicio() {
        return idServicio;
    }
    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }


}
