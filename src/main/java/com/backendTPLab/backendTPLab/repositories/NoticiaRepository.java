package com.backendTPLab.backendTPLab.repositories;

import com.backendTPLab.backendTPLab.entities.Empresa;
import com.backendTPLab.backendTPLab.entities.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {
    @Query("SELECT e FROM Noticia e WHERE e.empresa.id = :idEmpresa AND e.publicada = 'Y' ORDER BY e.fechaPublicacion DESC")
    public List<Noticia> findByEmpresaId(Integer idEmpresa);
    @Query("SELECT e FROM Noticia e WHERE (LOWER(e.tituloNoticia) LIKE %:param% OR LOWER(e.resumenNoticia) LIKE %:param%) AND e.empresa.id = :idEmpresa ORDER BY e.fechaPublicacion DESC")
    public List<Noticia> buscador(String param, int idEmpresa);
}
