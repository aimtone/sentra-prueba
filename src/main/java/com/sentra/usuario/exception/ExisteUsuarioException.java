package com.sentra.usuario.exception;

/**
 * Excepción que se lanza cuando un usuario ya existe en el sistema.
 * La comprobacion se hace consultando su correo electronico en base
 * de datos.
 */
public class ExisteUsuarioException extends RuntimeException {

    public ExisteUsuarioException(String mensaje) {
        super(mensaje);
    }
}
