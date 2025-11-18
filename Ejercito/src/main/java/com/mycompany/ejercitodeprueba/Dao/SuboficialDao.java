/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercitodeprueba.Dao;

import com.mycompany.ejercitodeprueba.Interfaces.CapacidadSubOficial;
import com.mycompany.ejercitodeprueba.Entidades.Servicios;


import static com.mycompany.ejercitodeprueba.Conexion.Conexion.getConexion;
import com.mycompany.ejercitodeprueba.Entidades.Servicios;
import com.mycompany.ejercitodeprueba.Entidades.Usuario;
import com.mycompany.ejercitodeprueba.Interfaces.CapacidadSubOficial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SuboficialDao implements CapacidadSubOficial {

    @Override
    public Servicios crearServicio(Class<?> clase, int codigo) {
        return null;
    }

    // Método real que usaremos
    public boolean crearNuevoServicio(Servicios servicio) {
        String sql = "INSERT INTO servicios (nombre_servicio, descripcion) VALUES (?, ?)";
        Connection con = getConexion();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, servicio.getNombre_servicio());
            ps.setString(2, servicio.getDescripcion());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al crear servicio: " + e.getMessage());
            return false;
        } finally {
            try { if(con!=null) con.close(); } catch(Exception e){}
        }
    }


    // --- Métodos de interfaz no usados en web ---
    @Override
    public Servicios AgregarServicios(Servicios s) { return null; }
    @Override
    public void MostrarDatos() {}
    @Override
    public void asignarServicioASoldado() {}
    @Override
    public Class<?>[] obtenerServicios() { return null; }
    @Override
    public void mostrarMenu() {}
    @Override
    public void mostrarSoldados() {}

    public List<Usuario> obtenerListaSoldados() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id_user, nombre, apellido, email FROM usuario WHERE rol = 'SOLDADO'"; // O 'Soldado' según tu BD
        Connection con = getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setCodigo(rs.getInt("id_user"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setEmail(rs.getString("email")); // Asegúrate de tener este campo
                lista.add(u);
            }
        } catch (Exception e) {
            System.out.println("Error al listar soldados: " + e.getMessage());
        } finally {
            try { if(con!=null) con.close(); } catch(Exception e){}
        }
        return lista;
    }





    public boolean asignarServicioAUsuario(int idUsuarioDestino, int idServicio) throws Exception {

        UsuarioDao usuarioDao = new UsuarioDao();
        String rolDestino = usuarioDao.getRolById(idUsuarioDestino);

        if (rolDestino.trim().toUpperCase().equals("OFICIAL")) {
            throw new Exception("No tiene permiso para asignar servicios a un Oficial.");
        }

        if (rolDestino.equals("NOT_FOUND")) {
            throw new Exception("El ID de usuario destino no existe.");
        }

        String sql = "INSERT INTO realizaservicio (id_user, id_servicio, fecha_realizacion, estado) VALUES (?, ?, ?, ?)";
        Connection con = getConexion();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuarioDestino);
            ps.setInt(2, idServicio);
            ps.setDate(3, new java.sql.Date(System.currentTimeMillis())); // Fecha actual
            ps.setBoolean(4, false); // Estado PENDIENTE (false)

            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al asignar servicio (DAO): " + e.getMessage());
            throw e; // Relanza el error
        } finally {
            try { if(con!=null) con.close(); } catch(Exception e){}
        }
    }



}


