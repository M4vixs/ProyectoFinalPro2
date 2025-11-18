/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.ejercitodeprueba.Interfaces;

import com.mycompany.ejercitodeprueba.Entidades.Servicios;
import java.util.List;

/**
 *
 * @author enzol
 */
public interface CapacidadSubOficial {
     public Servicios AgregarServicios(Servicios s);
     public void MostrarDatos();
     public void asignarServicioASoldado();
     public Class<?>[] obtenerServicios();
     public Servicios crearServicio(Class<?> clase, int codigo);
     public void mostrarSoldados();
     public void mostrarMenu();
}
