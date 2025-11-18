/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercitodeprueba.Entidades;

/**
 *
 * @author enzol
 */
import com.mycompany.ejercitodeprueba.Interfaces.CapacidadSoldado;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Soldado extends Usuario implements CapacidadSoldado {

    private Scanner scanner;

    public Soldado(int codigo) {
        super(codigo);
    }

    public Soldado() {
    }
    

    public Soldado(String nombre, String apellido, String tipoUser, Cuerpo tipoCuerpo, Compania comp, Cuartel cuart, String con,String email,int codigo) {
        super( nombre,  apellido,  tipoUser,  tipoCuerpo,  comp,  cuart,  con, email, codigo);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void mostrarDatos() {
        System.out.println("---Datos Soldado---");
        System.out.println("Id Soldado: " + getCodigo() + "\n");
        System.out.println("Nombre: " + getNombre() + "\n");
        System.out.println("Apellido: " + getApellido() + "\n");
        System.out.println("Tipo de Usuario: " + getTipoUser() + "\n");
        System.out.println("Tipo de Cuerpo: " + getTipoCuerpo().getDenominacion_cuerpo() + "\n");
        System.out.println("Compania: " + getComp().getDenominacion_compania() + "\n");
        System.out.println("Cuartel Asignado: " + getCuart().getNombre_cuartel() + "\n");
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Soldado{" + "scanner=" + scanner + '}';
    }

    @Override
    public void verServiciosAsignados() {
        
    }

    @Override
    public void mostrarMenu() {
        Scanner escaner = new Scanner(System.in);
        System.out.println("Elige que quiere acceder:\n1)Ver mis datos\n2)Ver Servicios Asignado\n3)Salir");
        int opcion = escaner.nextInt();
        switch (opcion) {
            case 1:
                //Mostrar Datos
                mostrarDatos();
                break;
            case 2:
                //Ver Servicios Asignados
                verServiciosAsignados();
                break;
            case 3:
                //Salir;
                //OficialDAo ofi=new OficialDAo();
                //ofi.mostrarMenu();
                break;
        }

    }

}
