package com.mycompany.ejercitodeprueba.Dao;

import static com.mycompany.ejercitodeprueba.Conexion.Conexion.getConexion;
import com.mycompany.ejercitodeprueba.Entidades.Servicios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.ejercitodeprueba.Interfaces.CapacidadSoldado;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class SoldadoDao implements CapacidadSoldado {


    public List<Servicios> getServiciosAsignados(int idSoldado) {
        List<Servicios> servicios = new ArrayList<>();
        Connection con = getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT s.id_servicio, s.nombre_servicio, s.descripcion, r.estado, r.fecha_realizacion, r.fecha_completado " +
                "FROM Servicios s " +
                "JOIN realizaservicio r ON s.id_servicio = r.id_servicio " +
                "WHERE r.id_user = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idSoldado);
            rs = ps.executeQuery();

            while (rs.next()) {
                Servicios servicio = new Servicios();
                servicio.setCodigo(rs.getInt("id_servicio"));
                servicio.setNombre_servicio(rs.getString("nombre_servicio"));

                boolean realizado = rs.getBoolean("estado");
                Date fechaAsignacionSQL = rs.getDate("fecha_realizacion");
                Timestamp fechaCompletadoSQL = rs.getTimestamp("fecha_completado"); // Puede ser NULL

                String estadoFinal = "";
                LocalDate hoy = LocalDate.now();

                if (realizado) {
                    if (fechaCompletadoSQL != null) {
                        LocalDate fechaAsignacion = fechaAsignacionSQL.toLocalDate();
                        LocalDate fechaCompletado = fechaCompletadoSQL.toLocalDateTime().toLocalDate(); // <-- Esta era la línea 51

                        if (fechaCompletado.isEqual(fechaAsignacion)) {
                            estadoFinal = "Realizado en fecha (" + fechaCompletado + ")";
                        } else {
                            estadoFinal = "Realizado con demora (" + fechaCompletado + ")";
                        }
                    } else {
                        // Caso borde: Está realizado pero no sabemos cuándo
                        estadoFinal = "REALIZADO (Fecha desc.)";
                    }

                } else {
                    // Si no está realizado, comprobamos si está vencido
                    LocalDate fechaAsignacion = fechaAsignacionSQL.toLocalDate();

                    if (hoy.isAfter(fechaAsignacion)) {
                        estadoFinal = "NO REALIZADO (Vencido)";
                    } else {
                        estadoFinal = "PENDIENTE";
                    }
                }

                servicio.setDescripcion(rs.getString("descripcion") + " [" + estadoFinal + "]");

                servicios.add(servicio);
            }
        } catch (Exception e) {
            System.out.println("CRITICAL Error al obtener servicios con estado: " + e.getMessage());
            e.printStackTrace(); // Imprime el error completo
        } finally {
            try { if(con!=null) con.close(); } catch(Exception e){}
        }
        return servicios;
    }

    public boolean marcarComoRealizado(int idSoldado, int idServicio) {

        // Ahora actualizamos 2 columnas: estado Y fecha_completado
        String sql = "UPDATE realizaservicio SET estado = ?, fecha_completado = ? WHERE id_user = ? AND id_servicio = ?";

        Connection con = getConexion();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);

            ps.setBoolean(1, true); // estado = true
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis())); // fecha_completado = AHORA MISMO
            ps.setInt(3, idSoldado);
            ps.setInt(4, idServicio);

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (Exception e) {
            System.out.println("Error al actualizar estado: " + e.getMessage());
            return false;
        } finally {
            try { if(con!=null) con.close(); } catch(Exception e){}
        }
    }

    @Override
    public void verServiciosAsignados() {throw new UnsupportedOperationException("Not supported yet.");}

    @Override
    public void MostrarDatos() {throw new UnsupportedOperationException("Not supported yet.");}

    @Override
    public void mostrarMenu() {throw new UnsupportedOperationException("Not supported yet.");}

    @Override
    public String toString() {
        return "SoldadoDao{" + '}';
    }
}