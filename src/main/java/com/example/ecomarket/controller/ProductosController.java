package com.example.ecomarket.controller;

import com.example.ecomarket.model.Productos;
import com.example.ecomarket.service.ProductoService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/producto")
@Tag(name = "Productos", description = "operaciones de productos")
public class ProductosController {

    @Autowired
    private ProductoService ProductoService;

    @GetMapping
    public ResponseEntity<List<Productos>> listar() {
        List<Productos> Productos  = ProductoService.BuscarTodosLosPacientes();
        if (Productos.isEmpty()) {
            return ResponseEntity.noContent().build();
            //alternativa 2 -> return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(Productos);
        //alternativa 2 -> return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity <Productos> listarunosolo(@PathVariable Integer idproducto)
    {
          Productos productos= ProductoService.BuscarPorId(idproducto);
          return ResponseEntity.ok(productos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long idproducto)
    {
        ProductoService.delete(idproducto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Productos> guardar(@RequestBody Productos productos)
    {
        Productos productoNuevo = ProductoService.save(productos);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Productos> modifcar(@PathVariable Integer idproducto, @RequestBody Productos producto )
{
        Productos productoMod = ProductoService.BuscarPorId(idproducto);
        productoMod.setIdproducto(idproducto);
    
        productoMod.setNombreProducto(producto.getNombreProducto());
        productoMod.setMarcaProducto(producto.getMarcaProducto());
        productoMod.setFechaVencimiento(producto.getFechaVencimiento());
        productoMod.setPrecioProducto(producto.getPrecioProducto());;
        ProductoService.save(productoMod);

        return ResponseEntity.ok(productoMod);
}
}

