package com.mycompany.ejercitodeprueba.Controller;

class LoginRequest {
    private String email;
    private String contrasenia;


    // Getters y Setters son necesarios para que Spring pueda leer el JSON
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContrasenia() {
        return contrasenia;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
