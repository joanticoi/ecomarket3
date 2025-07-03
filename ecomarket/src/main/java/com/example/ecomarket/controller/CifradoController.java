package com.example.ecomarket.controller;

import com.example.ecomarket.model.CifradoRequest;
import com.example.ecomarket.service.CifradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cifrado")
public class CifradoController {

    @Autowired
    private CifradoService cifradoService;

    @PostMapping("/encriptar")
    public String encriptar(@RequestBody CifradoRequest request) {
        return cifradoService.cifrar(request.getTexto(), request.getClave());
    }

    @PostMapping("/desencriptar")
    public String desencriptar(@RequestBody CifradoRequest request) {
        return cifradoService.descifrar(request.getTexto(), request.getClave());
    }

    

}
