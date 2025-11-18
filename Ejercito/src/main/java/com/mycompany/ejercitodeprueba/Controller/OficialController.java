package com.mycompany.ejercitodeprueba.Controller;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.mycompany.ejercitodeprueba.Entidades.Compania;
import com.mycompany.ejercitodeprueba.Dao.OficialDAo;
import com.mycompany.ejercitodeprueba.Entidades.Cuerpo;
import com.mycompany.ejercitodeprueba.Entidades.Cuartel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mycompany.ejercitodeprueba.Dao.SoldadoDao;
import com.mycompany.ejercitodeprueba.Entidades.Servicios;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/oficial")
public class OficialController {

    private OficialDAo oficialDao = new OficialDAo();
    private SoldadoDao soldadoDao = new SoldadoDao();

    @PostMapping("/crear-cuerpo")
    public ResponseEntity<?> crearCuerpo(@RequestBody Cuerpo request) {

        // 3. El HTML envía un JSON como {"denominacion_cuerpo": "Infantería"}
        // Spring lo convierte automáticamente en un objeto 'Cuerpo' (request).
        // (El 'codigo' será 0 o nulo, lo cual está bien para crear uno nuevo).

        // 4. ¡USAMOS TU LÓGICA EXISTENTE!
        boolean exito = oficialDao.CrearCuerpo(request);

        if (exito) {
            // Tu DAO imprimirá "Cuerpo" en la CONSOLA DEL SERVIDOR.
            // Y aquí devolvemos un 200 (OK) al HTML.
            return ResponseEntity.ok(request); // Devolvemos el cuerpo creado
        } else {
            // 5. Devolvemos un error 500 (Error Interno del Servidor)
            return ResponseEntity.internalServerError().body("Error al crear el cuerpo");
        }
    }

    @PostMapping("/crear-cuartel")
    public ResponseEntity<?> crearCuartel(@RequestBody Cuartel request) {

        // 4. El HTML envía {"nombre_cuartel": "...", "ubicacion": "..."}

        // 5. ¡USAMOS TU LÓGICA EXISTENTE!
        boolean exito = oficialDao.CrearCuartel(request);

        if (exito) {
            return ResponseEntity.ok(request);
        } else {
            return ResponseEntity.internalServerError().body("Error al crear el cuartel");
        }
    }

    @PostMapping("/asignar-servicio")
    public ResponseEntity<?> asignarServicio(@RequestBody AsignacionRequest request) {

        // Llama a tu método DAO existente
        boolean exito = oficialDao.asignarServicioASoldado(
                request.getIdUsuario(),
                request.getIdServicio()
        );

        if (exito) {
            // Devuelve un 200 (OK) con un mensaje
            return ResponseEntity.ok("Servicio asignado correctamente.");
        } else {
            // Devuelve un 500 (Error del Servidor)
            return ResponseEntity.internalServerError().body("Error al asignar el servicio.");
        }
    }

    @PostMapping("/crear-usuario")
    public ResponseEntity<?> crearUsuario(@RequestBody CrearUsuarioRequest request) {

        boolean exito = oficialDao.crearNuevoUsuario(
                request.getNombre(),
                request.getApellido(),
                request.getEmail(),
                request.getPassword(),
                request.getRol(),
                request.getIdCuerpo(),
                request.getIdCompania(),
                request.getIdCuartel()
        );

        if (exito) {
            return ResponseEntity.ok("Usuario creado exitosamente.");
        } else {
            return ResponseEntity.internalServerError().body("Error al crear el usuario. Verifique que los IDs de Cuerpo/Compañía existan.");
        }
    }

    @PostMapping("/crear-compania")
    public ResponseEntity<?> crearCompania(@RequestBody Compania request) {

        // Llama a tu método DAO existente
        boolean exito = oficialDao.CrearCompania(request);

        if (exito) {
            return ResponseEntity.ok(request); // Devuelve la compañía creada
        } else {
            return ResponseEntity.internalServerError().body("Error al crear la compañía.");
        }
    }

    @GetMapping("/servicios-usuario/{id}")
    public ResponseEntity<List<Servicios>> getServiciosDeCualquierUsuario(@PathVariable int id) {
        List<Servicios> servicios = soldadoDao.getServiciosAsignados(id);
        return ResponseEntity.ok(servicios);
    }

}




