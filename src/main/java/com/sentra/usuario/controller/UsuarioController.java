package com.sentra.usuario.controller;

import com.sentra.usuario.dto.Mensaje;
import com.sentra.usuario.exception.ExisteUsuarioException;
import com.sentra.usuario.model.Usuario;
import com.sentra.usuario.service.impl.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    @Autowired
    public UsuarioController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            Usuario savedUsuario = usuarioService.crear(usuario);
            return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
        } catch (ExisteUsuarioException e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("Error al crear el usuario"), HttpStatus.BAD_REQUEST);
        }
    }
}
