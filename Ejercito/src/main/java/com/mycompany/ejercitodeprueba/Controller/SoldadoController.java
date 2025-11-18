package com.mycompany.ejercitodeprueba.Controller;
import com.mycompany.ejercitodeprueba.Dao.SoldadoDao;
import com.mycompany.ejercitodeprueba.Entidades.Servicios;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/soldado") // Todas las URLs de esta clase empiezan con /api/soldado
public class SoldadoController {

    private SoldadoDao soldadoDao = new SoldadoDao();

    @GetMapping("/{id}/servicios")
    public ResponseEntity<List<Servicios>> getServiciosDelSoldado(@PathVariable int id) {
        List<Servicios> servicios = soldadoDao.getServiciosAsignados(id);
        return ResponseEntity.ok(servicios);
    }

    @PostMapping("/marcar-realizado")
    public ResponseEntity<?> marcarRealizado(@RequestBody MarcarRequest request) {
        boolean exito = soldadoDao.marcarComoRealizado(request.getIdUsuario(), request.getIdServicio());

        if (exito) {
            return ResponseEntity.ok("¡Servicio completado!");
        } else {
            return ResponseEntity.internalServerError().body("Error al marcar el servicio.");
        }
    }
}

