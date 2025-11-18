/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercitodeprueba.Conexion;


import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion {
    public static Connection getConexion(){
        Connection conexion=null;
        var baseDatos="ejercito";
        var url="jdbc:mysql://localhost:3306/"+ baseDatos;
        var usuario="root";
        var password ="";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion=DriverManager.getConnection(url,usuario,password);
        } catch (Exception e) {
            System.out.println("Error al conectarnos a la BD: "+ e.getMessage());
        }
        return conexion;
    }
}

