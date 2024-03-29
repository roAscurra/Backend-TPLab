package com.backendTPLab.backendTPLab.repositories;

import com.backendTPLab.backendTPLab.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    @Query("SELECT e FROM Empresa e WHERE LOWER(e.denominacion) LIKE %:param% OR LOWER(e.telefono) LIKE %:param% OR LOWER(e.horarioAtencion) LIKE %:param% OR LOWER(e.quienesSomos) LIKE %:param% OR CAST(e.latitud AS string) LIKE %:param% OR CAST(e.longitud AS string) LIKE %:param% OR LOWER(e.domicilio) LIKE %:param% OR LOWER(e.email) LIKE %:param%")
    public List<Empresa> buscador(String param);
    @Query("SELECT e FROM Empresa e WHERE LOWER(e.denominacion) LIKE %:denominacion%")
    public List<Empresa> findByDenominacion(String denominacion);
    @Query("SELECT e FROM Empresa e WHERE LOWER(e.telefono) LIKE %:telefono%")
    public List<Empresa> findByTelefono(String telefono);
    @Query("SELECT e FROM Empresa e WHERE LOWER(e.horarioAtencion) LIKE %:horaAtencion%")
    public List<Empresa> findByHorarioAtencion(String horaAtencion);
    @Query("SELECT e FROM Empresa e WHERE LOWER(e.quienesSomos) LIKE %:quienesSomos%")
    public List<Empresa> findByQuienesSomos(String quienesSomos);
    @Query("SELECT e FROM Empresa e WHERE CAST(e.latitud AS string) LIKE %:latitud%")
    public List<Empresa> findByLatitud(Double latitud);
    @Query("SELECT e FROM Empresa e WHERE CAST(e.longitud AS string) LIKE %:longitud%")
    public List<Empresa> findByLongitud(Double longitud);
    @Query("SELECT e FROM Empresa e WHERE LOWER(e.domicilio) LIKE %:domicilio%")
    public List<Empresa> findByDomicilio(String domicilio);
    @Query("SELECT e FROM Empresa e WHERE LOWER(e.email) LIKE %:email%")
    public List<Empresa> findByEmail(String email);

}
