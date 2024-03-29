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
    @Column(name = "Denominacion")
    private String denominacion;
    @Column(name = "Telefono")
    private String telefono;
    @Column(name = "HorarioDeAtencion")
    private String horarioAtencion;
    @Column(name = "QuienesSomos")
    private String quienesSomos;
    @Column(name = "Latitud")
    private Double latitud;
    @Column(name = "Longitud")
    private Double longitud;
    @Column(name = "Domicilio")
    private String domicilio;
    @Column(name = "Email")
    private String email;
}
