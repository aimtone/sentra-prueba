package com.sentra.usuario.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceImplTest {

    @InjectMocks
    private TokenServiceImpl tokenService;

    @BeforeEach
    void setUp() {
        tokenService = new TokenServiceImpl();
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