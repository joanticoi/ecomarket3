package com.example.ecomarket.repository;

import com.example.ecomarket.model.Productos;
import com.example.ecomarket.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    List<Inventario> findByProducto(Productos producto);
}   