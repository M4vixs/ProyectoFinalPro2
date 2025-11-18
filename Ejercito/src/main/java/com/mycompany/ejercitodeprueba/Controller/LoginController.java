package com.mycompany.ejercitodeprueba.Controller;

import com.mycompany.ejercitodeprueba.Dao.UsuarioDao;
import com.mycompany.ejercitodeprueba.Entidades.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private UsuarioDao usuarioDao = new UsuarioDao();

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuarioParaValidar = new Usuario(request.getContrasenia(), request.getEmail());
        boolean esValido = usuarioDao.validarConEm(usuarioParaValidar);

        if (esValido) {
            return ResponseEntity.ok(usuarioParaValidar);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email o contraseña incorrectos");
        }
    }
}


