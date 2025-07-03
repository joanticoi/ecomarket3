package com.example.ecomarket.service;

import com.example.ecomarket.util.BlowfishUtil;
import org.springframework.stereotype.Service;

@Service
public class CifradoService {

    public String cifrar(String texto, String clave) {
        try {
            return BlowfishUtil.encrypt(texto, clave);
        } catch (Exception e) {
            throw new RuntimeException("Error al cifrar el texto", e);
        }
    }

    public String descifrar(String textoCifrado, String clave) {
        try {
            return BlowfishUtil.decrypt(textoCifrado, clave);
        } catch (Exception e) {
            throw new RuntimeException("Error al descifrar el texto", e);
        }
    }
}
