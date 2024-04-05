package com.backendTPLab.backendTPLab.dto;

import com.backendTPLab.backendTPLab.entities.Noticia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticiaRequest {
    private Integer idEmpresa;
    private Noticia noticia;
}
