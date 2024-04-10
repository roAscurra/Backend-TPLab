package com.backendTPLab.backendTPLab.dto;

import com.backendTPLab.backendTPLab.entities.Noticia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticiaRequest {
    private Integer idEmpresa;
    private Noticia noticia;
//    private MultipartFile file;
}
