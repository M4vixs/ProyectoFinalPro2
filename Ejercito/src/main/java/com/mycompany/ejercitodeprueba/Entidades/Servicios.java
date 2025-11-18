/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercitodeprueba.Entidades;

import java.util.Date;

/**
 *
 * @author enzol
 */
public class Servicios extends Identificable {
    private String nombre_servicio;
    private String descripcion;

    public Servicios(String nombre_servicio, String descripcion, int codigo) {
        super(codigo);
        this.nombre_servicio = nombre_servicio;
        this.descripcion = descripcion;
    }

    public Servicios(String nombre_servicio, String descripcion) {
        this.nombre_servicio = nombre_servicio;
        this.descripcion = descripcion;
    }
    
    

    public Servicios(int codigo) {
        super(codigo);
    }

    public Servicios() {
    }
    
    

    
    public String getNombre_servicio() {
        return nombre_servicio;
    }
    
    
    public void setNombre_servicio(String nombre_servicio) {
        this.nombre_servicio = nombre_servicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    
    

    
    
}
