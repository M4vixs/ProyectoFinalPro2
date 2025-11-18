/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercitodeprueba.Entidades;

import static com.mycompany.ejercitodeprueba.Conexion.Conexion.getConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Usuario extends Identificable{

    private String nombre;
    private String apellido;
    private String tipoUser;
    private Cuerpo tipoCuerpo;
    private Compania comp;
    private Cuartel cuart;
    private String Contrasenia;
    private String email;

    public Usuario(String nombre, String apellido, String tipoUser, Cuerpo tipoCuerpo, Compania comp, Cuartel cuart, String Contrasenia, String email, int codigo) {
        super(codigo);
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoUser = tipoUser;
        this.tipoCuerpo = tipoCuerpo;
        this.comp = comp;
        this.cuart = cuart;
        this.Contrasenia = Contrasenia;
        this.email = email;
    }


    public Usuario(String Contrasenia, String email) {
        this.Contrasenia = Contrasenia;
        this.email = email;
    }

    
    public Usuario() {
    }

    

    
    public Usuario(int codigo) {
        super(codigo);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    public String getContrasenia() {    
        return Contrasenia;
    }

    // Métodos getter para acceder a los atributos privados
    public void setContrasenia(String Contrasenia) {    
        this.Contrasenia = Contrasenia;
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTipoUser() {
        return tipoUser;
    }
    
    
    public String getNombre() {
        return nombre;
    }
    
    public Cuerpo getTipoCuerpo() {
        return tipoCuerpo;
    }
    
    public Compania getComp() {
        return comp;
    }
    
    public Cuartel getCuart() {
        return cuart;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipoCuerpo(Cuerpo tipoCuerpo) {
        this.tipoCuerpo = tipoCuerpo;
    }

    public void setComp(Compania comp) {
        this.comp = comp;
    }

    public void setCuart(Cuartel cuart) {
        this.cuart = cuart;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }

    
    public void MostrarDatos(){
        System.out.println("Codigo Usuario: " + this.codigo);
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Cuerpo: " + this.tipoCuerpo.getDenominacion_cuerpo()); // Suponiendo que Cuerpo tiene getNombre()
        System.out.println("Compania: " + this.comp.getDenominacion_compania()); // Suponiendo que Compania tiene getDenominacion()
        System.out.println("Cuartel: " + this.cuart.getNombre_cuartel()); 
    }
}
