package com.mycompany.ejercitodeprueba.Controller;

class CrearUsuarioRequest {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String rol;
    private int idCuerpo;
    private int idCompania;
    private int idCuartel;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public int getIdCuerpo() { return idCuerpo; }
    public void setIdCuerpo(int idCuerpo) { this.idCuerpo = idCuerpo; }
    public int getIdCompania() { return idCompania; }
    public void setIdCompania(int idCompania) { this.idCompania = idCompania; }
    public int getIdCuartel() { return idCuartel; }
    public void setIdCuartel(int idCuartel) { this.idCuartel = idCuartel; }
}
