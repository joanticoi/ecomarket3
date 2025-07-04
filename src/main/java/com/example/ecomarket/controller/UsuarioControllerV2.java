package com.example.ecomarket.controller;

import com.example.ecomarket.assemblers.UsuarioModelAssembler;
import com.example.ecomarket.model.Usuario;
import com.example.ecomarket.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioControllerV2 {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler assembler;
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Usuario>> listar() {
        List<EntityModel<Usuario>> usuarios = usuarioService.BuscarTodosusuario().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioControllerV2.class).listar()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> listarUnSolo(@PathVariable Integer idusuario) {
        Usuario usuario = usuarioService.BuscarPorId(idusuario);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(usuario));
    }
    @PostMapping(consumes = MediaTypes.HAL_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.save(usuario);
        EntityModel<Usuario> entityModel = assembler.toModel(nuevoUsuario);
        return ResponseEntity.created(entityModel.getRequiredLink("self").toUri()).body(entityModel);
    }
    @DeleteMapping(value = "/{idusuario}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long idusuario) {
        Usuario usuario = usuarioService.BuscarPorId(idusuario);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.delete(idusuario);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{idusuario}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> actualizarUsuario(@PathVariable Integer idusuario, @RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioService.BuscarPorId(idusuario);
        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }
        usuario.setId(idusuario);
        Usuario usuarioActualizado = usuarioService.save(usuario);
        EntityModel<Usuario> entityModel = assembler.toModel(usuarioActualizado);
        return ResponseEntity.ok(entityModel);
    }
}
