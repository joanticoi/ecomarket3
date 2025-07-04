package com.example.ecomarket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity  // Marca esta clase como una entidad JPA.
@Table(name= "Productos")  // Especifica el nombre de la tabla en la base de datos.
@Data  // Genera automáticamente getters, setters, equals, hashCode y toString.
@NoArgsConstructor  // Genera un constructor sin argumentos.
@AllArgsConstructor  // Genera un constructor con un argumento por cada campo en la clase.
public class Productos {

    @Id  // Especifica el identificador primario.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // El valor del ID se generará automáticamente.
    private Integer idproducto;


    @Column(nullable=false)  // Esta columna no puede ser nula.
    private String nombreProducto;

    @Column(nullable=false)  // Esta columna no puede ser nula.
    private String marcaProducto;

    @Column(nullable=true)  // Esta columna puede ser nula.
    private Date fechaVencimiento;

    @Column(nullable=false)  // Esta columna no puede ser nula.
    private Integer precioProducto;

}

