package com.example.ecomarket;


import com.example.ecomarket.model.Productos;
import com.example.ecomarket.repository.ProductoRepository;
import com.example.ecomarket.service.ProductoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest 
public class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @MockBean
    private ProductoRepository productoRepository;
       private Long id;
    private String nombre;
    // other fields
    // ...

    public String getNombre() {
        return nombre;
    }

    @Test
    void testObtenerProductoPorId() {
        Productos producto = new Productos();
        producto.setId(1L);
        producto.setNombre("Producto Test");

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Optional<Productos> resultado = productoService.obtenerProductoPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Producto Test", resultado.get().getNombre());
    }

    @Test
    void testObtenerProductoPorIdNoEncontrado() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Productos> resultado = productoService.obtenerProductoPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void testListarProductos() {
        Productos producto1 = new Productos();
        producto1.setId(1L);
        producto1.setNombre("Producto 1");

        Productos producto2 = new Productos();
        producto2.setId(2L);
        producto2.setNombre("Producto 2");

        when(productoRepository.findAll()).thenReturn(List.of(producto1, producto2));

        List<Productos> productos = productoService.listarProductos();

        assertEquals(2, productos.size());
        assertEquals("Producto 1", productos.get(0).getNombre());
        assertEquals("Producto 2", productos.get(1).getNombre());
    }
}




