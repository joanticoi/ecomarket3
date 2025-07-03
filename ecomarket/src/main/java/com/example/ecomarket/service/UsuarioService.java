package com.example.ecomarket.service;

import com.example.ecomarket.model.Usuario;
import com.example.ecomarket.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> BuscarTodosusuario() {
        
        return usuarioRepository.findAll();
    }

    public Usuario BuscarPorId(long idusuario) {
        return usuarioRepository.findById(idusuario).get();
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void delete(Long idusuario) {
        usuarioRepository.deleteById(idusuario);
    }
}
