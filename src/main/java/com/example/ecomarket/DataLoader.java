package com.example.ecomarket;

import com.example.ecomarket.model.Productos;
import com.example.ecomarket.model.Usuario;
import com.example.ecomarket.model.Vendedor;
import com.example.ecomarket.repository.UsuarioRepository;
import com.example.ecomarket.repository.ProductoRepository;
import com.example.ecomarket.repository.InventarioRepository;
import com.example.ecomarket.repository.VendedorRepository;


import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


import java.util.concurrent.TimeUnit;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private Faker faker;
    @Autowired
    private InventarioRepository inventarioRepository;
    @Autowired
    private VendedorRepository vendedorRepository;

    @Override
    public void run(String... args) throws Exception {
        cargarDatos();
    }

    private void cargarDatos() {
        // Limpiar tablas
        productoRepository.deleteAll();
        usuarioRepository.deleteAll();
        inventarioRepository.deleteAll();
        vendedorRepository.deleteAll();

        // Crear usuarios, vendedores, productos e inventarios
        for (int i = 0; i < 10; i++) {
            Usuario usuario = new Usuario();
            usuario.setNombres(faker.name().firstName());
            usuario.setApellidos(faker.name().lastName());
            usuario.setFechaNacimiento(faker.date().birthday());
            usuario.setCorreo(faker.internet().emailAddress());
            usuarioRepository.save(usuario);

            // Crear vendedor para el usuario
            Vendedor vendedor = new Vendedor();
            vendedor.setId(usuario.getId());
            vendedorRepository.save(vendedor);

            // Crear productos para el usuario
            for (int j = 0; j < 5; j++) {
                Productos producto = new Productos();
                producto.setNombreProducto(faker.commerce().productName());
                producto.setMarcaProducto(faker.company().name());
                producto.setFechaVencimiento(faker.date().future(30, TimeUnit.DAYS));
                producto.setPrecioProducto((int) Double.parseDouble(faker.commerce().price(10.0, 100.0)));
                productoRepository.save(producto);



            }
        }
    }
}
