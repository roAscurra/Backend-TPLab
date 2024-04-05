package com.backendTPLab.backendTPLab.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Noticia")
@Entity
public class Noticia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "TituloNoticia", length = 128)
    private String tituloNoticia;
    @Column(name = "ResumenNoticia", length = 1024)
    private String resumenNoticia;
    @Column(name = "ImagenNoticia", length = 128)
    private String imagenNoticia;
    @Column(name = "ContenidoHTML", length = 20480)
    private String contenidoHTML;
    @Column(name = "Publicada", length = 1)
    private Character publicada;
    @Column(name = "FechaPublicacion")
    private LocalDate fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "idEmpresa")
    private Empresa empresa;
}
