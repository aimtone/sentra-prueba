package com.sentra.usuario.service;

import com.sentra.usuario.exception.ExisteUsuarioException;
import com.sentra.usuario.model.Usuario;
import com.sentra.usuario.repository.UsuarioRepository;
import com.sentra.usuario.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TokenProvider tokenProvider;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, TokenProvider tokenProvider) {
        this.usuarioRepository = usuarioRepository;
        this.tokenProvider = tokenProvider;
    }

    public Usuario crear(Usuario usuario) throws Exception {
        verificarUsuarioExistente(usuario.getEmail());
        String token = tokenProvider.generarToken(usuario.getEmail());
        usuario.setToken(token);
        usuario.setLast_login(LocalDateTime.now());
        usuario.setIsactive(true);
        return usuarioRepository.save(usuario);
    }

    private void verificarUsuarioExistente(String email) throws ExisteUsuarioException {
        Optional<Usuario> existeUsuario = usuarioRepository.findByEmail(email);
        if (existeUsuario.isPresent()) {
            throw new ExisteUsuarioException("El correo electrónico ya está registrado");
        }
    }

}
