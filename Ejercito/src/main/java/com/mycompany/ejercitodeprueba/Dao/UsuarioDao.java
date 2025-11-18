/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercitodeprueba.Dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.mycompany.ejercitodeprueba.Conexion.Conexion.getConexion;
import com.mycompany.ejercitodeprueba.Entidades.Compania;
import com.mycompany.ejercitodeprueba.Entidades.Cuartel;
import com.mycompany.ejercitodeprueba.Entidades.Cuerpo;
import com.mycompany.ejercitodeprueba.Entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UsuarioDao {
    public Usuario validarID(int userCod) {

        Usuario user=new Usuario();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT "
                + "u.nombre, u.apellido, u.rol, "
                + "u.id_compania, u.id_cuerpo, u.id_cuartel, "
                + "cpa.denominacion_compania, "
                + "cpo.denominacion_cuerpo, "
                + "cu.nombre_cuartel "
                + "FROM usuario u "
                + "JOIN compania cpa ON u.id_compania = cpa.id_compania "
                + "JOIN cuerpo cpo ON u.id_cuerpo = cpo.id_cuerpo "
                + "JOIN cuartel cu ON u.id_cuartel = cu.id_cuartel "
                + "WHERE u.id_user = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, userCod);
            rs = ps.executeQuery();

            if (rs.next()) {
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setTipoUser(rs.getString("rol"));

                Cuerpo cuerpo = new Cuerpo(rs.getInt("id_cuerpo"), rs.getString("denominacion_cuerpo"));
                user.setTipoCuerpo(cuerpo);

                Compania comp = new Compania(rs.getString("denominacion_compania"), rs.getInt("id_compania"));
                user.setComp(comp);
                System.out.println(user.getComp());
                System.out.println("HOLA");
                //Cuartel cuartel = new Cuartel(rs.getInt("id_cuartel"),rs.getString("nombre_cuartel"),rs.getString("ubicacion"));
                Cuartel cuartel = new Cuartel(rs.getString("nombre_cuartel"),rs.getInt("id_cuartel"));
                user.setCuart(cuartel);
                return user;
            }
        } catch (Exception e) {
            System.out.println("Error al buscar User por ID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion " + e.getMessage());
            }
        }
        return null;

    }

    public boolean validarConEm(Usuario user) {

        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();

        String passwordInput = user.getContrasenia();

        var sql = "SELECT "
                + "u.id_user, u.nombre, u.apellido, u.rol, "
                + "u.contrasenia, "
                + "u.id_compania, u.id_cuerpo, u.id_cuartel, "
                + "cpa.denominacion_compania, "
                + "cpo.denominacion_cuerpo, "
                + "cu.nombre_cuartel "
                + "FROM usuario u "
                + "JOIN compania cpa ON u.id_compania = cpa.id_compania "
                + "JOIN cuerpo cpo ON u.id_cuerpo = cpo.id_cuerpo "
                + "JOIN cuartel cu ON u.id_cuartel = cu.id_cuartel "
                + "WHERE u.email = ?";;

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            rs = ps.executeQuery();

            if (rs.next()) {

                String hashGuardado = rs.getString("contrasenia");
                PasswordEncoder encoder = new BCryptPasswordEncoder();

                if (encoder.matches(passwordInput, hashGuardado)) {

                user.setCodigo(rs.getInt("id_user"));
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setTipoUser(rs.getString("rol"));

                Cuerpo cuerpo = new Cuerpo(rs.getInt("id_cuerpo"), rs.getString("denominacion_cuerpo"));
                user.setTipoCuerpo(cuerpo);

                Compania comp = new Compania(rs.getString("denominacion_compania"), rs.getInt("id_compania"));
                user.setComp(comp);
                System.out.println("compania: "+user.getComp().getDenominacion_compania());
                //Cuartel cuartel = new Cuartel(rs.getInt("id_cuartel"),rs.getString("nombre_cuartel"),rs.getString("ubicacion"));
                Cuartel cuartel = new Cuartel(rs.getString("nombre_cuartel"),rs.getInt("id_cuartel"));
                user.setCuart(cuartel);
                return true;
            }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar user por password o mail : " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion " + e.getMessage());
            }
        }
        return false;

    }

    public boolean cambiarContrasenia(int idUsuario, String nuevoPasswordPlano) {

        // 1. Hasheamos la nueva contraseña
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashNuevo = encoder.encode(nuevoPasswordPlano);

        // 2. Preparamos el SQL
        String sql = "UPDATE Usuario SET contrasenia = ? WHERE id_user = ?";

        Connection con = getConexion();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, hashNuevo);  // El nuevo HASH
            ps.setInt(2, idUsuario);   // El ID del usuario

            int filasAfectadas = ps.executeUpdate();

            // Devuelve true si la fila fue actualizada
            return filasAfectadas > 0;

        } catch (Exception e) {
            System.out.println("Error al cambiar contraseña: " + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
    }

    public String getRolById(int idUsuario) {
        String sql = "SELECT rol FROM usuario WHERE id_user = ?";
        Connection con = getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("rol");
            } else {
                return "NOT_FOUND"; // Devuelve esto si el ID no existe
            }
        } catch (Exception e) {
            System.out.println("Error al obtener rol: " + e.getMessage());
            return "ERROR";
        } finally {
            try { if(con!=null) con.close(); } catch(Exception e){}
        }
    }


}
