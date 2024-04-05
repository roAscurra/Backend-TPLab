package com.backendTPLab.backendTPLab.services;

import com.backendTPLab.backendTPLab.dto.NoticiaRequest;
import com.backendTPLab.backendTPLab.entities.Empresa;
import com.backendTPLab.backendTPLab.entities.Noticia;
import com.backendTPLab.backendTPLab.repositories.EmpresaRepository;
import com.backendTPLab.backendTPLab.repositories.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NoticiaService {
    @Autowired
    NoticiaRepository noticiaRepository;
    @Autowired
    EmpresaRepository empresaRepository;
    //traer todas las noticia
    public List<Noticia> list(){
        return noticiaRepository.findAll();
    }
    // Método para obtener las noticias de una empresa por su id
    public List<Noticia> obtenerNoticiasPorEmpresa(Integer idEmpresa) {
        return noticiaRepository.findByEmpresaId(idEmpresa);
    }
    //eliminar
    public boolean deleteNoticia(Integer id) throws Exception {
        try {
            if (noticiaRepository.existsById(id)) {
                noticiaRepository.deleteById(id);
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
    public Noticia save(NoticiaRequest noticiaRequest) throws Exception {
        try {
            Integer idEmpresa = noticiaRequest.getIdEmpresa();
            Noticia noticia = noticiaRequest.getNoticia();

            // Obtener la instancia de Empresa correspondiente al idEmpresa
            Empresa empresa = empresaRepository.findById(idEmpresa)
                    .orElseThrow(() -> new Exception("No se encontró la empresa con el id: " + idEmpresa));

            // Establecer la empresa en la noticia
            noticia.setEmpresa(empresa);

            // Guardar la noticia en la base de datos
            noticia = noticiaRepository.save(noticia);

            // Imprimir información de depuración
            System.out.println("Estoy en servicio con el id generado: " + noticia.getId());
            System.out.println("Estoy en servicio generado: " + noticia.getTituloNoticia());

            return noticia;
        } catch (Exception e) {
            throw new Exception("Error al guardar la noticia: " + e.getMessage());
        }
    }


    //actualizar
    public Noticia modificarNoticia(Integer id, Map<String, Object> cambios) throws Exception {
        // Verificar si la noticia existe
        Optional<Noticia> noticiaExistente = noticiaRepository.findById(id);
        if (noticiaExistente.isPresent()) {
            // Obtener la empresa existente
            Noticia noticia = noticiaExistente.get();

            // Aplicar los cambios recibidos
            for (Map.Entry<String, Object> entry : cambios.entrySet()) {
                String campo = entry.getKey();
                Object valor = entry.getValue();

                // Actualizar el campo correspondiente de la empresa
                switch (campo) {
                    case "tituloNoticia":
                        noticia.setTituloNoticia((String) valor);
                        break;
                    case "resumenNoticia":
                        noticia.setResumenNoticia((String) valor);
                        break;
                    case "imagenNoticia":
                        noticia.setImagenNoticia((String) valor);
                        break;
                    case "contenidoHTML":
                        noticia.setContenidoHTML((String) valor);
                        break;
                    case "publicada":
                        noticia.setPublicada((Character) valor);
                        break;
                    case "fechaPublicacion":
                        noticia.setFechaPublicacion((LocalDate) valor);
                        break;
                    case "empresa": // Cambiado de idEmpresa a empresa
                        // Obtener la empresa actualizada
                        Empresa empresa = (Empresa) valor;
                        // Asignar la empresa a la noticia
                        noticia.setEmpresa(empresa);
                        break;
                    default:
                        // Ignorar campos desconocidos
                        break;
                }
            }
            // Guardar la empresa actualizada en la base de datos
            return noticiaRepository.save(noticia);
        } else {
            throw new Exception("La empresa no se encuentra.");
        }
    }
    //buscar por id
    public Optional<Noticia> findById(Integer id) throws Exception{
        try{
            return noticiaRepository.findById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    // obtener titulo noticia por id
    public String obtenerTitulo(int id) {
        Optional<Noticia> noticiaOptional = noticiaRepository.findById(id);
        if (noticiaOptional.isPresent()) {
            return noticiaOptional.get().getTituloNoticia();
        } else {
            // Manejo de caso en el que la empresa no se encuentra
            throw new RuntimeException("No se encontró ninguna noticia con el ID proporcionado.");
        }
    }
    // obtener resumen noticia por id
    public String obtenerResumen(int id) {
        Optional<Noticia> noticiaOptional = noticiaRepository.findById(id);
        if (noticiaOptional.isPresent()) {
            return noticiaOptional.get().getResumenNoticia();
        } else {
            // Manejo de caso en el que la empresa no se encuentra
            throw new RuntimeException("No se encontró ninguna noticia con el ID proporcionado.");
        }
    }
    // obtener imagen noticia por id
    public String obtenerImagen(int id) {
        Optional<Noticia> noticiaOptional = noticiaRepository.findById(id);
        if (noticiaOptional.isPresent()) {
            return noticiaOptional.get().getImagenNoticia();
        } else {
            // Manejo de caso en el que la empresa no se encuentra
            throw new RuntimeException("No se encontró ninguna noticia con el ID proporcionado.");
        }
    }
    // obtener contenido noticia por id
    public String obtenerContenido(int id) {
        Optional<Noticia> noticiaOptional = noticiaRepository.findById(id);
        if (noticiaOptional.isPresent()) {
            return noticiaOptional.get().getContenidoHTML();
        } else {
            // Manejo de caso en el que la empresa no se encuentra
            throw new RuntimeException("No se encontró ninguna noticia con el ID proporcionado.");
        }
    }
    // obtener publicadaa por id
    public Character obtenerPublicada(int id) {
        Optional<Noticia> noticiaOptional = noticiaRepository.findById(id);
        if (noticiaOptional.isPresent()) {
            return noticiaOptional.get().getPublicada();
        } else {
            // Manejo de caso en el que la empresa no se encuentra
            throw new RuntimeException("No se encontró ninguna noticia con el ID proporcionado.");
        }
    }
    // obtener fecha publicacion por id
    public LocalDate obtenerFechaPublicacion(int id) {
        Optional<Noticia> noticiaOptional = noticiaRepository.findById(id);
        if (noticiaOptional.isPresent()) {
            return noticiaOptional.get().getFechaPublicacion();
        } else {
            // Manejo de caso en el que la empresa no se encuentra
            throw new RuntimeException("No se encontró ninguna noticia con el ID proporcionado.");
        }
    }
    // obtener fecha publicacion por id
    public Integer obtenerIdEmpresa(int id) {
        Optional<Noticia> noticiaOptional = noticiaRepository.findById(id);
        if (noticiaOptional.isPresent()) {
            return noticiaOptional.get().getEmpresa().getId();
        } else {
            // Manejo de caso en el que la empresa no se encuentra
            throw new RuntimeException("No se encontró ninguna noticia con el ID proporcionado.");
        }
    }
}
