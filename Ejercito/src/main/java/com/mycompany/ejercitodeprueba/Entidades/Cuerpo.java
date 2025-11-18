/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercitodeprueba.Entidades;

import java.util.ArrayList;

public class Cuerpo extends Identificable{
    private String denominacion_cuerpo;

    public Cuerpo(int codigo,String denominacion_cuerpo) {
        super(codigo);
        this.denominacion_cuerpo = denominacion_cuerpo;
    }

    public Cuerpo(int codigo) {
        super(codigo);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setDenominacion_cuerpo(String denominacion_cuerpo) {
        this.denominacion_cuerpo = denominacion_cuerpo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    

//    public ArrayList<Usuario> getUsuarioAsignado() {
//        return usuarioAsignado;
//    }

    public String getDenominacion_cuerpo() {
        return denominacion_cuerpo;
    }
}