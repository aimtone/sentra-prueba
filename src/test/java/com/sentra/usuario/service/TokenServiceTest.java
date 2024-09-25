package com.sentra.usuario.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        tokenService = new TokenService();
        ReflectionTestUtils.setField(tokenService, "secretKey", "SECRETKEY");
    }

    @Test
    void generarToken() {
        String email = "juasn@rodriguez.org";
        String token = tokenService.generarToken(email);
        assertNotNull(token);
        assertTrue(token.startsWith("eyJ"));
    }
}