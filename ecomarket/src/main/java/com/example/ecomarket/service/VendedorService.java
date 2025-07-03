package com.example.ecomarket.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import com.example.ecomarket.model.Vendedor;
import com.example.ecomarket.repository.VendedorRepository;


import java.util.List;

@Service
@Transactional
public class VendedorService {
     @Autowired
    private VendedorRepository vendedorRepository;

    public List<Vendedor> BuscarTodosvendedor() {
        
        return vendedorRepository.findAll();
    }

    public Vendedor BuscarPorId(long idvendedor) {
        return vendedorRepository.findById(idvendedor).get();
    }

    public Vendedor save(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public void delete(Long idvendedor) {
        vendedorRepository.deleteById(idvendedor);
    }

}
