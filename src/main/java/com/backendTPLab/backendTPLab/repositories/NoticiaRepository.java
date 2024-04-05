package com.backendTPLab.backendTPLab.repositories;

import com.backendTPLab.backendTPLab.entities.Empresa;
import com.backendTPLab.backendTPLab.entities.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {
    @Query("SELECT e FROM Noticia e WHERE e.empresa.id = :idEmpresa")
    public List<Noticia> findByEmpresaId(Integer idEmpresa);
}
