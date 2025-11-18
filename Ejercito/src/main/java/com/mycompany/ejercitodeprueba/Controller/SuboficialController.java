package com.mycompany.ejercitodeprueba.Controller;
import com.mycompany.ejercitodeprueba.Dao.SoldadoDao;
import com.mycompany.ejercitodeprueba.Dao.UsuarioDao;
import com.mycompany.ejercitodeprueba.Entidades.Servicios;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.mycompany.ejercitodeprueba.Dao.SuboficialDao;
import com.mycompany.ejercitodeprueba.Entidades.Servicios;
import com.mycompany.ejercitodeprueba.Entidades.Usuario;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/suboficial")
public class SuboficialController {

    private SuboficialDao suboficialDao = new SuboficialDao();
    private SoldadoDao soldadoDao = new SoldadoDao();
    private UsuarioDao usuarioDao = new UsuarioDao();

    // Endpoint para Crear Servicio
    @PostMapping("/crear-servicio")
    public ResponseEntity<?> crearServicio(@RequestBody Servicios servicio) {
        boolean exito = suboficialDao.crearNuevoServicio(servicio);
        if (exito) {
            return ResponseEntity.ok("Servicio creado correctamente.");
        } else {
            return ResponseEntity.internalServerError().body("Error al crear el servicio.");
        }
    }

    @GetMapping("/listar-soldados")
    public ResponseEntity<List<Usuario>> listarSoldados() {
        List<Usuario> soldados = suboficialDao.obtenerListaSoldados();
        return ResponseEntity.ok(soldados);
    }

    @PostMapping("/asignar-servicio")
    public ResponseEntity<?> asignarServicio(@RequestBody AsignacionRequest request) {
        try {
            // Reutilizamos el DTO 'AsignacionRequest' que creamos para el Oficial
            boolean exito = suboficialDao.asignarServicioAUsuario(
                    request.getIdUsuario(),
                    request.getIdServicio()
            );

            if (exito) {
                return ResponseEntity.ok("Servicio asignado correctamente.");
            } else {
                // Esto no debería pasar si la lógica de arriba está bien
                return ResponseEntity.internalServerError().body("Error desconocido.");
            }

        } catch (Exception e) {
            // Aquí capturamos el error (Ej: "No tiene permiso...")
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/servicios-usuario/{id}")
    public ResponseEntity<?> getServiciosDeSoldado(@PathVariable int id) {

        // 1. OBTENER EL ROL DEL USUARIO OBJETIVO
        String rolTarget = usuarioDao.getRolById(id);

        // 2. APLICAR LA REGLA DE NEGOCIO (LA RESTRICCIÓN)
        if (rolTarget.trim().toUpperCase().equals("OFICIAL")) {
            // Si es un Oficial, devolvemos un error 403 (Prohibido)
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No tiene permiso para consultar los servicios de un Oficial.");
        }

        if (rolTarget.equals("NOT_FOUND")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El ID de usuario no existe.");
        }

        // 3. SI PASA (Soldado o Suboficial), devuelve los servicios
        List<Servicios> servicios = soldadoDao.getServiciosAsignados(id);
        return ResponseEntity.ok(servicios);
    }
}
