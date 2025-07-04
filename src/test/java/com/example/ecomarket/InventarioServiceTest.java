package com.example.ecomarket;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.ecomarket.model.Inventario;
import com.example.ecomarket.repository.InventarioRepository;
import com.example.ecomarket.service.InventarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class InventarioServiceTest {

    @Autowired
    private InventarioService inventarioService;

    @MockBean
    private InventarioRepository inventarioRepository;

    @Test
    void testObtenerInventarioPorId() {
        Inventario inventario = new Inventario();
        inventario.setId(1L);

        when(inventarioRepository.findById(1L)).thenReturn(Optional.of(inventario));

        Optional<Inventario> resultado = inventarioService.obtenerInventarioPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
    }

    @Test
    void testObtenerInventarioPorIdNoEncontrado() {
        when(inventarioRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Inventario> resultado = inventarioService.obtenerInventarioPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void testListarInventarios() {
        Inventario inv1 = new Inventario();
        inv1.setId(1L);
        Inventario inv2 = new Inventario();
        inv2.setId(2L);

        when(inventarioRepository.findAll()).thenReturn(List.of(inv1, inv2));

        List<Inventario> inventarios = inventarioService.listarInventarios();

        assertEquals(2, inventarios.size());
        assertEquals(1L, inventarios.get(0).getId());
        assertEquals(2L, inventarios.get(1).getId());
    }
}
