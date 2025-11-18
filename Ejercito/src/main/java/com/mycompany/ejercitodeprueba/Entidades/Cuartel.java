/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercitodeprueba.Entidades;



import java.util.ArrayList;

public class Cuartel extends Identificable{
    private String nombre_cuartel;
    private String ubicacion;

    public Cuartel( int codigo,String nombre_cuartel, String ubicacion) {
        super(codigo);
        this.nombre_cuartel = nombre_cuartel;
        this.ubicacion = ubicacion;
    }

    public Cuartel(String nombre_cuartel, int codigo) {
        super(codigo);
        this.nombre_cuartel = nombre_cuartel;
    }

    public Cuartel() {
        super();
    }
    
    



    public Cuartel(int codigo) {
        super(codigo);
    }

    public String getNombre_cuartel() {
        return nombre_cuartel;
    }

    public String getUbicacion() {
        return ubicacion;
    }
    

//    public void MostrarUsuariosAsignados(){
//        for (Usuario usuario : UsuarioCuartel) {
//            usuario.MostrarDatos();
//        }
//    }

    public void setNombre_cuartel(String nombre_cuartel) {
        this.nombre_cuartel = nombre_cuartel;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}