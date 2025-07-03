package com.example.ecomarket.service;

import com.example.ecomarket.model.Inventario;
import com.example.ecomarket.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    // Listar todos los inventarios
    public List<Inventario> listarInventarios() {
        return inventarioRepository.findAll();
    }

    // Obtener inventario por ID
    public Optional<Inventario> obtenerInventarioPorId(Long id) {
        return inventarioRepository.findById(id);
    }

    // Guardar o actualizar inventario
    public Inventario guardarInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    // Eliminar inventario por ID
    public void eliminarInventario(Long id) {
        inventarioRepository.deleteById(id);
    }
}