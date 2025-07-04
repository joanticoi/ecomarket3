package com.example.ecomarket.controller;

import com.example.ecomarket.model.Usuario;
import com.example.ecomarket.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
@Tag(name = "Usuarios", description = "operaciones de usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Obtener todos los usuarios", description = "Obtener el listado de todos los usuarios")
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios  = usuarioService.BuscarTodosusuario();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
            //alternativa 2 -> return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(usuarios);
        //alternativa 2 -> return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }
    @Operation(summary = "Obtener un usuario por ID", description = "Obtener los detalles de un usuario específico")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarunosolo(@PathVariable Integer idusuario)
    {
          Usuario oUsuario= usuarioService.BuscarPorId(idusuario);
          return ResponseEntity.ok(oUsuario);
    }
    @Operation(summary = "Eliminar un usuario por ID", description = "Eliminar un usuario específico")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long idusuario)
    {
        usuarioService.delete(idusuario);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Guardar un nuevo usuario", description = "Crear un nuevo usuario en el sistema")
    @PostMapping
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario)
    {
        Usuario usuarioNuevo = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioNuevo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> modifcar(@PathVariable Integer idusuario, @RequestBody Usuario usuario)
    {
        Usuario usuarioMod = usuarioService.BuscarPorId(idusuario);
        usuarioMod.setId(idusuario);
        usuarioMod.setRun(usuario.getRun());
        usuarioMod.setNombres(usuario.getNombres());
        usuarioMod.setApellidos(usuario.getApellidos());
        usuarioMod.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioMod.setCorreo(usuario.getCorreo());
        usuarioService.save(usuarioMod);

        return ResponseEntity.ok(usuarioMod);
    }

}

