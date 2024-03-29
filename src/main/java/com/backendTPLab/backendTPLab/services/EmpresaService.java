package com.backendTPLab.backendTPLab.services;

import com.backendTPLab.backendTPLab.entities.Empresa;
import com.backendTPLab.backendTPLab.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;
    //traer todas las empresas
    public List<Empresa> list(){
        return empresaRepository.findAll();
    }
    //eliminar
    public boolean deleteEmpresa(Integer id) throws Exception {
        try {
            if (empresaRepository.existsById(id)) {
                empresaRepository.deleteById(id);
                return true;
            }
            else {
                throw new Exception();
            }
        }
        catch (Exception e ){
            throw new Exception(e.getMessage());
        }
    }
    //alta
    public Empresa save(Empresa empresa)throws Exception {
        try {
            empresa = empresaRepository.save(empresa);
            System.out.println("Estoy en servicio con el id generado:" + empresa.getId());

            System.out.println("Estoy en servicio  generado" + empresa.getDenominacion());

            return empresa;
        }
        catch (Exception e ){
            throw new Exception(e.getMessage());
        }
    }
    //actualizar
    public Empresa modificarEmpresa(Integer id, Map<String, Object> cambios) throws Exception {
        // Verificar si la empresa existe
        Optional<Empresa> empresaExistente = empresaRepository.findById(id);
        if (empresaExistente.isPresent()) {
            // Obtener la empresa existente
            Empresa empresa = empresaExistente.get();

            // Aplicar los cambios recibidos
            for (Map.Entry<String, Object> entry : cambios.entrySet()) {
                String campo = entry.getKey();
                Object valor = entry.getValue();

                // Actualizar el campo correspondiente de la empresa
                switch (campo) {
                    case "denominacion":
                        empresa.setDenominacion((String) valor);
                        break;
                    case "domicilio":
                        empresa.setDomicilio((String) valor);
                        break;
                    case "email":
                        empresa.setEmail((String) valor);
                        break;
                    case "horarioDesAtencion":
                        empresa.setHorarioAtencion((String) valor);
                        break;
                    case "latitud":
                        empresa.setLatitud((Double) valor);
                        break;
                    case "longitud":
                        empresa.setLongitud((Double) valor);
                        break;
                    case "quienesSomos":
                        empresa.setQuienesSomos((String) valor);
                        break;
                    case "telefono":
                        empresa.setTelefono((String) valor);
                        break;
                    default:
                        // Ignorar campos desconocidos
                        break;
                }
            }
            // Guardar la empresa actualizada en la base de datos
            return empresaRepository.save(empresa);
        } else {
            throw new Exception("La empresa no se encuentra.");
        }
    }
    //buscador general
    public List<Empresa> buscador(String param) throws Exception{
        try{
            return empresaRepository.buscador(param);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    //buscar por id
    public Optional<Empresa> findById(Integer id) throws Exception{
        try{
            return empresaRepository.findById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    //buscar por denominacion
    public List<Empresa> findByDenominacion(String denominacion)throws Exception{
        try {
            return empresaRepository.findByDenominacion(denominacion);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    // buscar por teléfono
    public List<Empresa> findByTelefono(String telefono) throws Exception {
        try {
            return empresaRepository.findByTelefono(telefono);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // buscar por horario de atención
    public List<Empresa> findByHorarioAtencion(String horarioAtencion) throws Exception {
        try {
            return empresaRepository.findByHorarioAtencion(horarioAtencion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // buscar por quienes somos
    public List<Empresa> findByQuienesSomos(String quienesSomos) throws Exception {
        try {
            return empresaRepository.findByQuienesSomos(quienesSomos);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // buscar por latitud
    public List<Empresa> findByLatitud(Double latitud) throws Exception {
        try {
            return empresaRepository.findByLatitud(latitud);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // buscar por longitud
    public List<Empresa> findByLongitud(Double longitud) throws Exception {
        try {
            return empresaRepository.findByLongitud(longitud);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // buscar por domicilio
    public List<Empresa> finByDomicilio(String domicilio) throws Exception {
        try {
            return empresaRepository.findByDomicilio(domicilio);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // buscar por email
    public List<Empresa> finByEmail(String email) throws Exception {
        try {
            return empresaRepository.findByEmail(email);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    // obtener denominacion por id
    public String obtenerDenominacion(int id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (empresaOptional.isPresent()) {
            return empresaOptional.get().getDenominacion();
        } else {
            // Manejo de caso en el que la empresa no se encuentra
            throw new RuntimeException("No se encontró ninguna empresa con el ID proporcionado.");
        }
    }
    //obtener domicilio por id
    public String obtenerDomicilio(int id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (empresaOptional.isPresent()) {
            return empresaOptional.get().getDomicilio();
        } else {
            // Manejo de caso en el que la empresa no se encuentra
            throw new RuntimeException("No se encontró ninguna empresa con el ID proporcionado.");
        }
    }
    //obtener email por id
    public String obtenerEmail(int id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (empresaOptional.isPresent()) {
            return empresaOptional.get().getEmail();
        } else {
            // Manejo de caso en el que la empresa no se encuentra
            throw new RuntimeException("No se encontró ninguna empresa con el ID proporcionado.");
        }
    }
    //obtener horario de atencion por id
    public String obtenerHorarioAtencion(int id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (empresaOptional.isPresent()) {
            return empresaOptional.get().getHorarioAtencion();
        } else {
            // Manejo de caso en el que la empresa no se encuentra
            throw new RuntimeException("No se encontró ninguna empresa con el ID proporcionado.");
        }
    }
    //obtener latitud por id
    public Double obtenerLatitudPorId(int id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (empresaOptional.isPresent()) {
            return empresaOptional.get().getLatitud();
        } else {
            // Manejo de caso en el que la empresa no se encuentra
            throw new RuntimeException("No se encontró ninguna empresa con el ID proporcionado.");
        }
    }
    //obtener longitud por id
    public Double obtenerLongitud(int id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (empresaOptional.isPresent()) {
            return empresaOptional.get().getLongitud();
        } else {
            // Manejo de caso en el que la empresa no se encuentra
            throw new RuntimeException("No se encontró ninguna empresa con el ID proporcionado.");
        }
    }
    //obtener quienes somos por id
    public String obtenerQuienesSomos(int id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (empresaOptional.isPresent()) {
            return empresaOptional.get().getQuienesSomos();
        } else {
            // Manejo de caso en el que la empresa no se encuentra
            throw new RuntimeException("No se encontró ninguna empresa con el ID proporcionado.");
        }
    }
    //obtener telefono por id
    public String obtenerTelefono(int id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (empresaOptional.isPresent()) {
            return empresaOptional.get().getTelefono();
        } else {
            // Manejo de caso en el que la empresa no se encuentra
            throw new RuntimeException("No se encontró ninguna empresa con el ID proporcionado.");
        }
    }
}
