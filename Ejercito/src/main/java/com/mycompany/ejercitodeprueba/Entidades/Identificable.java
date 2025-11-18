/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercitodeprueba.Entidades;



/**
 *
 * @author enzol
 */
public abstract class Identificable {
    protected int codigo;

    public Identificable(int codigo) {
        this.codigo = codigo;
    }

    public Identificable() {
    }
    

    public int getCodigo() {
        return codigo;
    }
    
    
    
}
