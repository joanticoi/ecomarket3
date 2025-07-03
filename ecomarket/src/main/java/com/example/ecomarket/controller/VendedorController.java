package com.example.ecomarket.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecomarket.model.Vendedor;
import com.example.ecomarket.service.VendedorService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vendedor")

public class VendedorController {
      @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public ResponseEntity<List<Vendedor>> listar() {
        List<Vendedor> vendedor  = vendedorService.BuscarTodosvendedor();
        if (vendedor.isEmpty()) {
            return ResponseEntity.noContent().build();
            //alternativa 2 -> return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(vendedor);
        //alternativa 2 -> return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity <Vendedor> listarunosolo(@PathVariable Integer idvendedor)
    {
          Vendedor ovendedor= vendedorService.BuscarPorId(idvendedor);
          return ResponseEntity.ok(ovendedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long idvendedor)
    {
        vendedorService.delete(idvendedor);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Vendedor> guardar(@RequestBody Vendedor vendedor)
    {
        Vendedor vendedorNuevo = vendedorService.save(vendedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendedorNuevo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Vendedor> modifcar(@PathVariable Integer idvendedor, @RequestBody Vendedor vendedor)
    {
        Vendedor vendedorMod = vendedorService.BuscarPorId(idvendedor);
        vendedorMod.setId(idvendedor);
        vendedorMod.setRun(vendedor.getRun());
        vendedorMod.setNombres(vendedor.getNombres());
        vendedorMod.setApellidos(vendedor.getApellidos());
        vendedorMod.setFechaNacimiento(vendedor.getFechaNacimiento());
        vendedorMod.setCorreo(vendedor.getCorreo());
        vendedorService.save(vendedorMod);

        return ResponseEntity.ok(vendedorMod);
    }

}
