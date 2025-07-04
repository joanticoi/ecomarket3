package com.example.ecomarket.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.example.ecomarket.controller.UsuarioControllerV2;
import com.example.ecomarket.model.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(usuario,
            linkTo(methodOn(UsuarioControllerV2.class).listarUnSolo(usuario.getId())).withSelfRel(),
            linkTo(methodOn(UsuarioControllerV2.class).listar()).withRel("usuarios"),
            linkTo(methodOn(UsuarioControllerV2.class).crearUsuario(usuario)).withRel("crear"),
            linkTo(methodOn(UsuarioControllerV2.class).eliminarUsuario(usuario.getId().longValue())).withRel("eliminar"),
            linkTo(methodOn(UsuarioControllerV2.class).actualizarUsuario(usuario.getId(), usuario)).withRel("actualizar")
        );
    }
}
