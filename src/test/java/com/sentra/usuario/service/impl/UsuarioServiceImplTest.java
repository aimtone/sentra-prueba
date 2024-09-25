package com.sentra.usuario.service.impl;

import com.sentra.usuario.exception.ExisteUsuarioException;
import com.sentra.usuario.model.Usuario;
import com.sentra.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UsuarioServiceImplTest {
    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private TokenServiceImpl tokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearExistente() {
        Usuario usuario = new Usuario();
        usuario.setEmail("juasn@rodriguez.org");
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.of(usuario));
        assertThrows(ExisteUsuarioException.class, () -> usuarioService.crear(usuario));
    }

    @Test
    void crearNuevo() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("juasn@rodriguez.org");
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.empty());
        when(tokenService.generarToken(usuario.getEmail())).thenReturn("token");
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario usuarioCreado = usuarioService.crear(usuario);
        assertNotNull(usuarioCreado);
        assertEquals(usuario.getEmail(), usuarioCreado.getEmail());
        assertEquals("token", usuarioCreado.getToken());
    }
}