/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.ejercitodeprueba.Interfaces;

import com.mycompany.ejercitodeprueba.Entidades.Compania;
import com.mycompany.ejercitodeprueba.Entidades.Cuartel;
import com.mycompany.ejercitodeprueba.Entidades.Cuerpo;
import com.mycompany.ejercitodeprueba.Entidades.Servicios;
import com.mycompany.ejercitodeprueba.Entidades.Soldado;
import com.mycompany.ejercitodeprueba.Entidades.Usuario;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 *
 * @author enzol
 */
public interface CapacidadOficial {
   public boolean ReasignarUsuarioCuartel(int codigoUser, int codigoCuartel);
    public boolean ReasignarUsuarioCompania(int codigoUser, int codigoCompania);
    public boolean ReasignarUsuarioCuerpo(int codigoUser, int codigoCuerpo);
    public boolean CrearCuartel(Cuartel cua);
    public boolean CrearCuerpo(Cuerpo cuerpo1);
    public boolean CrearCompania(Compania comp1);
    public boolean asignarServicioASoldado(int codigoUser,int codigoServ);
    public Class<?>[] obtenerServicios();
    //Class<?> clase, int codigo
    public boolean crearServicio(Servicios servicio);
    public void mostrarMenu();
    
    
}
