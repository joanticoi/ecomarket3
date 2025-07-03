package com.example.ecomarket.repository;

import com.example.ecomarket.model.Productos;
import com.example.ecomarket.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}