package com.sentra.usuario.controller;

import com.sentra.usuario.dto.Mensaje;
import com.sentra.usuario.exception.ExisteUsuarioException;
import com.sentra.usuario.model.Usuario;
import com.sentra.usuario.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearUsuario() throws Exception {
        Usuario usuario = new Usuario();
        when(usuarioService.crear(usuario)).thenReturn(usuario);
        ResponseEntity<?> response = usuarioController.crearUsuario(usuario);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void crearUsuarioExisteUsuarioException() throws Exception {
        Usuario usuario = new Usuario();
        when(usuarioService.crear(usuario)).thenThrow(new ExisteUsuarioException("El correo electronico ya esta registrado"));
        ResponseEntity<?> response = usuarioController.crearUsuario(usuario);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        Mensaje mensajeResponse = (Mensaje) response.getBody();
        assertEquals("El correo electronico ya esta registrado", mensajeResponse.getMensaje());
    }

    @Test
    void crearUsuarioRuntimeException() throws Exception {
        Usuario usuario = new Usuario();
        when(usuarioService.crear(usuario)).thenThrow(new RuntimeException("Error al crear el usuario"));
        ResponseEntity<?> response = usuarioController.crearUsuario(usuario);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Mensaje mensajeResponse = (Mensaje) response.getBody();
        assertEquals("Error al crear el usuario", mensajeResponse.getMensaje());
    }
}