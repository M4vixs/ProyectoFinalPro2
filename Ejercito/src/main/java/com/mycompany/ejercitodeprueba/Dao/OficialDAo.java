/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercitodeprueba.Dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.mycompany.ejercitodeprueba.Conexion.Conexion.getConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static com.mycompany.ejercitodeprueba.Conexion.Conexion.getConexion;
import com.mycompany.ejercitodeprueba.Interfaces.CapacidadOficial;
import com.mycompany.ejercitodeprueba.Entidades.Compania;
import com.mycompany.ejercitodeprueba.Entidades.Cuartel;
import com.mycompany.ejercitodeprueba.Entidades.Cuerpo;
import com.mycompany.ejercitodeprueba.Entidades.Servicios;
import com.mycompany.ejercitodeprueba.Entidades.Soldado;
import com.mycompany.ejercitodeprueba.Entidades.Usuario;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author enzol
 */
public class OficialDAo implements CapacidadOficial {

    @Override
    public boolean ReasignarUsuarioCuartel(int codigoUser, int codigoCuartel) {
        System.out.println("Codigo user: "+codigoUser);
        System.out.println("Codigo cuartel: "+codigoCuartel);
        PreparedStatement ps;
        //ResultSet rs;
        Connection con = getConexion();
        //"INSERT INTO cuartel(nombre_cuartel, ubicacion)" + " Values(?,?)";
        var sql = "UPDATE usuario set id_cuerpo=? where id_user=?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoCuartel);
            ps.setInt(2, codigoUser);
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al reasignar un cuartel " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion " + e.getMessage());
            }

        }
        return false;
    }

    @Override
    public boolean ReasignarUsuarioCompania(int codigoUser, int codigoCompania) {
        UsuarioDao userDao = new UsuarioDao();
        Scanner escaner = new Scanner(System.in);
        //UPDATE usuario set id_compania=10 where id_user=10;
//        Usuario userPrueba = new Usuario();
//        userPrueba = userDao.validarID(codigoUser);
//
//        if (userPrueba.getComp() != null) {
//
//        }
        System.out.println("Codigo user: "+codigoUser);
        System.out.println("Codigo compania: "+codigoCompania);
        PreparedStatement ps;
        //ResultSet rs;
        Connection con = getConexion();
        //"INSERT INTO cuartel(nombre_cuartel, ubicacion)" + " Values(?,?)";
        var sql = "UPDATE usuario set id_compania=? where id_user=?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoCompania);
            ps.setInt(2, codigoUser);
            ps.execute();
            System.out.println("Cuartel");
            return true;
        } catch (Exception e) {
            System.out.println("Error al reasignar una compania " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion " + e.getMessage());
            }

        }
        return false;

    }

    @Override
    public boolean ReasignarUsuarioCuerpo(int codigoUser, int codigoCuerpo) {
        System.out.println("Codigo user: "+codigoUser);
        System.out.println("Codigo compania: "+codigoCuerpo);
        PreparedStatement ps;
        //ResultSet rs;
        Connection con = getConexion();
        //"INSERT INTO cuartel(nombre_cuartel, ubicacion)" + " Values(?,?)";
        var sql = "UPDATE usuario set id_cuerpo=? where id_user=?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoCuerpo);
            ps.setInt(2, codigoUser);
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al reasignar un cuerpo " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion " + e.getMessage());
            }

        }
        return false;
    }

    @Override
    public boolean CrearCuartel(Cuartel cua) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "INSERT INTO cuartel(nombre_cuartel, ubicacion)" + " Values(?,?)";;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cua.getNombre_cuartel());
            ps.setString(2, cua.getUbicacion());
            ps.execute();
            System.out.println("Cuartel");
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar un Cuartel " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion " + e.getMessage());
            }

        }
        return false;
    }

    @Override
    public boolean CrearCuerpo(Cuerpo cuerpo1) {
        PreparedStatement ps;
        //ResultSet rs;
        Connection con = getConexion();
        //"INSERT INTO cuerpo(denominacion_cuerpo)"+ " Values(?)";
        var sql = "INSERT INTO cuerpo(denominacion_cuerpo)" + " Values(?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cuerpo1.getDenominacion_cuerpo());
            ps.execute();
            System.out.println("Cuerpo");
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar un cuerpo " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion " + e.getMessage());
            }

        }
        return false;
    }

    @Override
    public boolean CrearCompania(Compania comp1) {
        PreparedStatement ps;
        //ResultSet rs;
        Connection con = getConexion();
        //"INSERT INTO compania(denominacion_compania)"+ "Values(?);";
        var sql = "INSERT INTO compania(denominacion_compania)" + "Values(?);";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, comp1.getDenominacion_compania());
            ps.execute();
            System.out.println("Compania");
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar un Compania " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion " + e.getMessage());
            }

        }
        return false;
    }

    @Override
    public boolean asignarServicioASoldado(int codigoUser, int codigoServ) {
        // --- ¡AQUÍ VA LA NUEVA LÓGICA! ---

        // Asumo que tu tabla intermedia se llama 'usuario_servicios'
        String sql = "INSERT INTO realizaservicio (id_user, id_servicio, fecha_realizacion) VALUES (?, ?, ?)";

        Connection con = getConexion();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoUser);
            ps.setInt(2, codigoServ);

            ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0; // Devuelve true si se insertó con éxito

        } catch (Exception e) {
            System.out.println("Error al asignar servicio: " + e.getMessage());
            return false;
        } finally {
            // Cierra todas las conexiones
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
    }



    @Override
    public Class<?>[] obtenerServicios() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean crearServicio(Servicios ser) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "INSERT INTO servicios(nombre_servicio,descripcion)" + "Values(?,?);";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ser.getNombre_servicio());
            ps.setString(2, ser.getDescripcion());
            ps.execute();
            System.out.println("Servicio");
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar un servicio " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion " + e.getMessage());
            }

        }
        return false;
    }

    public boolean crearNuevoUsuario(String nombre, String apellido, String email, String password, String rol, int idCuerpo, int idCompania, int idCuartel) {

        // 1. Hasheamos la contraseña (Seguridad)
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordHasheado = encoder.encode(password);

        // 2. SQL para insertar
        String sql = "INSERT INTO usuario (nombre, apellido, email, contrasenia, rol, id_cuerpo, id_compania, id_cuartel) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection con = getConexion();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, email);
            ps.setString(4, passwordHasheado); // Guardamos el hash, no el texto plano
            ps.setString(5, rol);       // Ej: "SOLDADO", "SUBOFICIAL"
            ps.setInt(6, idCuerpo);
            ps.setInt(7, idCompania);
            ps.setInt(8, idCuartel);

            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Error al crear usuario: " + e.getMessage());
            return false;
        } finally {
            try { if(con!=null) con.close(); } catch(Exception e){}
        }
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n");
        System.out.println("---Menu Oficial---\n");
    }

}
