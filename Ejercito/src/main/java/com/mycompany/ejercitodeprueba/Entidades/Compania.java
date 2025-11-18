/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercitodeprueba.Entidades;
import java.util.ArrayList;

public class Compania extends Identificable {
    private String denominacion_compania;

    public Compania(String denominacion_compania, int codigo) {
        super(codigo);
        this.denominacion_compania = denominacion_compania;
    }

    public Compania() {
        super();
    }

    public Compania(int codigo) {
        super(codigo);
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDenominacion_compania() {
        return denominacion_compania;
    }

    public void setDenominacion_compania(String denominacion_compania) {
        this.denominacion_compania = denominacion_compania;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
}
