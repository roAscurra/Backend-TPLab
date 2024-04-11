package com.backendTPLab.backendTPLab.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Empresa")
@Entity
public class Empresa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Denominacion", length = 128)
    private String denominacion;
    @Column(name = "Telefono", length = 50)
    private String telefono;
    @Column(name = "HorarioDeAtencion", length = 256)
    private String horarioAtencion;
    @Column(name = "QuienesSomos", length = 1024)
    private String quienesSomos;
    @Column(name = "Latitud")
    private Float latitud;
    @Column(name = "Longitud")
    private Float longitud;
    @Column(name = "Domicilio", length = 256)
    private String domicilio;
    @Column(name = "Email", length = 75)
    private String email;
}
