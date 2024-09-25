package com.sentra.usuario.service.impl;

import com.sentra.usuario.exception.ExisteUsuarioException;
import com.sentra.usuario.model.Usuario;
import com.sentra.usuario.repository.UsuarioRepository;
import com.sentra.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service("UsuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TokenServiceImpl tokenService;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, TokenServiceImpl tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    @Override
    public Usuario crear(Usuario usuario) {
        verificarUsuarioExistente(usuario.getEmail());
        String token = tokenService.generarToken(usuario.getEmail());
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
