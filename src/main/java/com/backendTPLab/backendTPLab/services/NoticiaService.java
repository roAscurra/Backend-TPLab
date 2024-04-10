package com.backendTPLab.backendTPLab.services;

import com.backendTPLab.backendTPLab.dto.NoticiaRequest;
import com.backendTPLab.backendTPLab.entities.Empresa;
import com.backendTPLab.backendTPLab.entities.Noticia;
import com.backendTPLab.backendTPLab.repositories.EmpresaRepository;
import com.backendTPLab.backendTPLab.repositories.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoticiaService {
    @Autowired
    NoticiaRepository noticiaRepository;
    @Autowired
    EmpresaRepository empresaRepository;

//    private final Path imageStorageLocation = Paths.get("src/main/resources/picture");
private final Path imageStorageLocation = Paths.get("C:\\Users\\Usuario\\Documents\\1 UTN\\CrearNoticia\\picture");


    //traer todas las noticia
    public List<Noticia> list(){
        return noticiaRepository.findAll();
    }
    // Método para obtener las noticias de una empresa por su id
    public List<Noticia> obtenerNoticiasPorEmpresa(Integer idEmpresa) {
        return noticiaRepository.findByEmpresaId(idEmpresa);
    }
    //buscador general
    public List<Noticia> buscador(String param) throws Exception{
        try{
            return noticiaRepository.buscador(param);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
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
    //obtener imagen
    public Resource cargarImagen(String nombreImagen) throws MalformedURLException {
        try {
            Path imagePath = imageStorageLocation.resolve(nombreImagen).normalize();
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("No se pudo encontrar la imagen " + nombreImagen);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Error al cargar la imagen " + nombreImagen, ex);
        }
    }

    //subir imagen
    public String subirImagen(MultipartFile file) throws Exception{
        try{
            String fileName = UUID.randomUUID().toString();
            byte[] bytes = file.getBytes();
            String fileOriginalName = file.getOriginalFilename();

            long fileSize = file.getSize();
            long maxFileSize = 5 * 1024 * 1024;

            if(fileSize > maxFileSize){
                return "File size must be less then or equal 5MB";
            }
            if(
                    !fileOriginalName.endsWith(".jpg") &&
                            !fileOriginalName.endsWith(".jpeg") &&
                            !fileOriginalName.endsWith(".png")
            ){
                return "Only JPG, JPEG, PNG files are allowed";
            }
            String fileExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
            String newFileName = fileName + fileExtension;

//            File folder = new File("src/main/resources/picture");
            File folder = new File("C:", "Users/Usuario/Documents/1 UTN/CrearNoticia/picture");
            if(!folder.exists()){
                folder.mkdirs();
            }
//            Path path = Paths.get("src/main/resources/picture/"+newFileName);
            Path path = Paths.get("C:\\Users\\Usuario\\Documents\\1 UTN\\CrearNoticia\\picture", newFileName);

            Files.write(path, bytes);
            return newFileName;

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    //alta
//    public Noticia save(NoticiaRequest noticiaRequest, MultipartFile imagen) throws Exception {
//        try {
//            Integer idEmpresa = noticiaRequest.getIdEmpresa();
//            Noticia noticia = noticiaRequest.getNoticia();
//
//            // Subir la imagen y obtener el nombre del archivo
//            String nombreImagen = subirImagen(imagen);
//
//            // Obtener la instancia de Empresa correspondiente al idEmpresa
//            Empresa empresa = empresaRepository.findById(idEmpresa)
//                    .orElseThrow(() -> new Exception("No se encontró la empresa con el id: " + idEmpresa));
//
//            // Establecer la empresa y la imagen en la noticia
//            noticia.setEmpresa(empresa);
//            noticia.setImagenNoticia(nombreImagen);
//
//            // Guardar la noticia en la base de datos
//            noticia = noticiaRepository.save(noticia);
//
//            // Imprimir información de depuración
//            System.out.println("Estoy en servicio con el id generado: " + noticia.getId());
//            System.out.println("Estoy en servicio generado: " + noticia.getTituloNoticia());
//
//            return noticia;
//        } catch (Exception e) {
//            throw new Exception("Error al guardar la noticia: " + e.getMessage());
//        }
//    }

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
