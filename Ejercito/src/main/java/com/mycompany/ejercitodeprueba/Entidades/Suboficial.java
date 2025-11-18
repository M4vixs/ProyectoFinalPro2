/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercitodeprueba.Entidades;

/**
 *
 * @author enzol
 */
import com.mycompany.ejercitodeprueba.Interfaces.CapacidadSubOficial;
import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;
import java.io.File;

public class Suboficial extends Usuario implements CapacidadSubOficial {

    private Scanner scanner;

    public Suboficial(String nombre, String apellido, String tipoUser, Cuerpo tipoCuerpo, Compania comp, Cuartel cuart, String con,String email,int codigo) {
        super(nombre,  apellido,  tipoUser,  tipoCuerpo,  comp,  cuart,  con, email, codigo);
    }

    public Suboficial() {
    }
    

    public Suboficial(int codigo) {
        super(codigo);
    }

    @Override
    public Servicios AgregarServicios(Servicios s) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void asignarServicioASoldado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Class<?>[] obtenerServicios() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Servicios crearServicio(Class<?> clase, int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarSoldados() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    public Servicios AgregarServicios(Servicios s);
//     public void MostrarDatos();
//     public void asignarServicioASoldado();
//     public Class<?>[] obtenerServicios();
//     public Servicios crearServicio(Class<?> clase, int codigo);
//     public void mostrarSoldados();
//     public void mostrarMenu();
    public void mostrarDatos() {
        System.out.println("---Datos Suboficial---");
        System.out.println("Id Soldado: " + getCodigo() + "\n");
        System.out.println("Nombre: " + getNombre() + "\n");
        System.out.println("Apellido: " + getApellido() + "\n");
        System.out.println("Tipo de Usuario: " + getTipoUser() + "\n");
        System.out.println("Tipo de Cuerpo: " + getTipoCuerpo().getDenominacion_cuerpo() + "\n");
        System.out.println("Compania: " + getComp().getDenominacion_compania() + "\n");
        System.out.println("Cuartel Asignado: " + getCuart().getNombre_cuartel() + "\n");
    }

    public void mostrarMenu() {
        Scanner escaner = new Scanner(System.in);
        System.out.println("Elige que quiere acceder:\n1)Ver mis datos\n2)Ver Servicios Asignado\n3)Asignar Sercicio a soldado\n4)Crear Servicio\n5)Mostrar Soldados\n6)Salir");
        int opcion = escaner.nextInt();
        switch (opcion) {
            case 1:
                //Mostrar Datos
                mostrarDatos();
                break;
            case 2:
                //Ver Servicios Asignados
                //verServiciosAsignados();
                break;
            case 3:
                Servicios serv = new Servicios();
                //asignarServicio(Servicio serv);
                break;
            case 4:
                //Salir;
                //OficialDAo ofi=new OficialDAo();
                //ofi.mostrarMenu();
                break;
            case 5:
                //Salir;
                //OficialDAo ofi=new OficialDAo();
                //ofi.mostrarMenu();
                break;
            case 6:
                //Salir;
                //OficialDAo ofi=new OficialDAo();
                //ofi.mostrarMenu();
                break;
        }

    }

}
