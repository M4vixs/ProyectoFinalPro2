package com.mycompany.ejercitodeprueba.Controller;

import com.mycompany.ejercitodeprueba.Dao.UsuarioDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario") // URLs genéricas de usuario
public class UsuarioController {

    private UsuarioDao usuarioDao = new UsuarioDao();

    @PostMapping("/cambiar-password")
    public ResponseEntity<?> cambiarPassword(@RequestBody CambioPasswordRequest request) {

        boolean exito = usuarioDao.cambiarContrasenia(
                request.getIdUsuario(),
                request.getNuevoPassword()
        );

        if (exito) {
            return ResponseEntity.ok("Contraseña actualizada con éxito.");
        } else {
            return ResponseEntity.internalServerError().body("No se pudo actualizar la contraseña.");
        }
    }
}


