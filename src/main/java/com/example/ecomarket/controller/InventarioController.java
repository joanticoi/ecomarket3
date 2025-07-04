package com.example.ecomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.ecomarket.model.Inventario;
import com.example.ecomarket.service.InventarioService;


import java.util.List; 
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/inventario")
@Tag(name = "Inventario", description = "operaciones de inventario")
public class InventarioController {
@Autowired
private InventarioService inventarioService;

// Obtener todos los inventarios
@GetMapping
public List<Inventario> listarInventarios() {
    return inventarioService.listarInventarios();
}

// Obtener inventario por ID
@GetMapping("/{id}")
public ResponseEntity<Inventario> obtenerInventario(@PathVariable Long id) {
    Optional<Inventario> inventario = inventarioService.obtenerInventarioPorId(id);
    if (inventario.isPresent()) {
        return ResponseEntity.ok(inventario.get());
    } else {
        return ResponseEntity.notFound().build();
    }
}

// Crear o actualizar inventario
@PostMapping
public Inventario guardarInventario(@RequestBody Inventario inventario) {
    return inventarioService.guardarInventario(inventario);
}

// Eliminar inventario por ID
@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminarInventario(@PathVariable Long id) {
    inventarioService.eliminarInventario(id);
    return ResponseEntity.noContent().build();
}
// Actualizar inventario por ID
@PutMapping("/{id}")
public ResponseEntity<Inventario> actualizarInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
    Optional<Inventario> inventarioExistente = inventarioService.obtenerInventarioPorId(id);
    if (inventarioExistente.isPresent()) {
        inventario.setId(id);
        Inventario inventarioActualizado = inventarioService.guardarInventario(inventario);
        return ResponseEntity.ok(inventarioActualizado);
    } else {
        return ResponseEntity.notFound().build();
    }
}

}

