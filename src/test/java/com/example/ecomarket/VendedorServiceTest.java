package com.example.ecomarket;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.ecomarket.model.Vendedor;
import com.example.ecomarket.repository.VendedorRepository;
import com.example.ecomarket.service.VendedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class VendedorServiceTest {

    @Autowired
    private VendedorService vendedorService;

    @MockBean
    private VendedorRepository vendedorRepository;

    @Test
    void testObtenerVendedorPorId() {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(1L);
        vendedor.setNombre("Juan Pérez");
        vendedor.setEmail("juan@mail.com");

        when(vendedorRepository.findById(1L)).thenReturn(Optional.of(vendedor));

        Optional<Vendedor> resultado = vendedorService.obtenerVendedorPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Juan Pérez", resultado.get().getNombre());
        assertEquals("juan@mail.com", resultado.get().getEmail());
    }

    @Test
    void testObtenerVendedorPorIdNoEncontrado() {
        when(vendedorRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Vendedor> resultado = vendedorService.obtenerVendedorPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void testListarVendedores() {
        Vendedor vendedor1 = new Vendedor();
        vendedor1.setId(1L);
        vendedor1.setNombre("Juan Pérez");

        Vendedor vendedor2 = new Vendedor();
        vendedor2.setId(2L);
        vendedor2.setNombre("Ana Gómez");

        when(vendedorRepository.findAll()).thenReturn(List.of(vendedor1, vendedor2));

        List<Vendedor> resultado = vendedorService.listarVendedores();

        assertEquals(2, resultado.size());
        assertEquals("Juan Pérez", resultado.get(0).getNombre());
        assertEquals("Ana Gómez", resultado.get(1).getNombre());
    }

    @Test
    void testCrearVendedor() {
        Vendedor nuevoVendedor = new Vendedor();
        nuevoVendedor.setNombre("Carlos López");
        nuevoVendedor.setEmail("carlos@mail.com");

        when(vendedorRepository.save(any(Vendedor.class))).thenReturn(nuevoVendedor);

        Vendedor resultado = vendedorService.crearVendedor(nuevoVendedor);

        assertEquals("Carlos López", resultado.getNombre());
        assertEquals("carlos@mail.com", resultado.getEmail());
    }

    @Test
    void testActualizarVendedor() {
        Vendedor vendedorExistente = new Vendedor();
        vendedorExistente.setId(1L);
        vendedorExistente.setNombre("Juan Pérez");
        vendedorExistente.setEmail("juan@mail.com");

        when(vendedorRepository.findById(1L)).thenReturn(Optional.of(vendedorExistente));
        when(vendedorRepository.save(any(Vendedor.class))).thenReturn(vendedorExistente);

        vendedorExistente.setNombre("Juan Pérez Actualizado");
        Vendedor resultado = vendedorService.actualizarVendedor(1L, vendedorExistente);

        assertEquals("Juan Pérez Actualizado", resultado.getNombre());
        assertEquals("juan@mail.com", resultado.getEmail());
    }
    @Test
    void testEliminarVendedor() {
        Vendedor vendedorExistente = new Vendedor();
        vendedorExistente.setId(1L);
        vendedorExistente.setNombre("Juan Pérez");
        vendedorExistente.setEmail("juan@mail.com");

        when(vendedorRepository.findById(1L)).thenReturn(Optional.of(vendedorExistente));
        doNothing().when(vendedorRepository).deleteById(1L);

        boolean resultado = vendedorService.eliminarVendedor(1L);

        assertTrue(resultado);
        verify(vendedorRepository, times(1)).deleteById(1L);
    }
    @Test
    void testEliminarVendedorNoEncontrado() {
        when(vendedorRepository.findById(99L)).thenReturn(Optional.empty());

        boolean resultado = vendedorService.eliminarVendedor(99L);

        assertFalse(resultado);
        verify(vendedorRepository, never()).deleteById(99L);
    }                       
}
