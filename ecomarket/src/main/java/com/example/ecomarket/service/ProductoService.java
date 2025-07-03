package com.example.ecomarket.service;

import com.example.ecomarket.model.Productos;
import com.example.ecomarket.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository ProductoRepository;

    public List<Productos> BuscarTodosLosPacientes() {
        
        return ProductoRepository.findAll();
    }

    public Productos BuscarPorId(long idProducto) {
        return ProductoRepository.findById(idProducto).get();
    }

    public Productos save(Productos paciente) {
        return ProductoRepository.save(paciente);
    }

    public void delete(Long idProducto) {
        ProductoRepository.deleteById(idProducto);
    }
}
