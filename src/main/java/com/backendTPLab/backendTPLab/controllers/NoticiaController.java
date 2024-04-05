package com.backendTPLab.backendTPLab.controllers;

import com.backendTPLab.backendTPLab.dto.NoticiaRequest;
import com.backendTPLab.backendTPLab.entities.Empresa;
import com.backendTPLab.backendTPLab.entities.Noticia;
import com.backendTPLab.backendTPLab.services.EmpresaService;
import com.backendTPLab.backendTPLab.services.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "noticias")
public class NoticiaController {
    //inyeccion del servicio
    @Autowired
    private NoticiaService noticiaService;

    // Endpoint para obtener todas las empresas
    @GetMapping
    public List<Noticia> listarNoticias() {
        return noticiaService.list();
    }

    // Endpoint para obtener una noticia por su ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Noticia>> obtenerNoticiaPorId(@PathVariable int id) throws Exception {
        try {
            Optional<Noticia> noticia = noticiaService.findById(id);
            return noticia.isPresent() ? ResponseEntity.ok(noticia) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para obtener las noticias de una empresa por su id
    @GetMapping("/empresa/{idEmpresa}/noticias")
    public List<Noticia> obtenerNoticiasPorEmpresa(@PathVariable Integer idEmpresa) {
        return noticiaService.obtenerNoticiasPorEmpresa(idEmpresa);
    }

    // Endpoint para agregar una nueva noticia
    @PostMapping("/crear")
    public ResponseEntity<?> agregarNoticia(@RequestBody NoticiaRequest noticiaRequest) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(noticiaService.save(noticiaRequest));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }




    // Endpoint para modificar una noticia existente
    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> modificarNoticia(@PathVariable Integer id, @RequestBody Map<String, Object> cambios) {
        try {
            Noticia noticia = noticiaService.modificarNoticia(id, cambios);
            return ResponseEntity.ok(noticia);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar la empresa: " + e.getMessage());
        }
    }

    // Endpoint para eliminar una empresa por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNoticia(@PathVariable Integer id) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(noticiaService.deleteNoticia(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde. \"}");
        }
    }

    // Endpoint para obtener titulo de una noticia por su ID
    @GetMapping("/titulo/{id}")
    public ResponseEntity<?> obtenerTitulo(@PathVariable int id) {
        try {
            String titulo = noticiaService.obtenerTitulo(id);
            return ResponseEntity.ok(titulo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    // Endpoint para obtener resumen de una noticia por su ID
    @GetMapping("/resumen/{id}")
    public ResponseEntity<?> obtenerResumen(@PathVariable int id) {
        try {
            String resumen = noticiaService.obtenerResumen(id);
            return ResponseEntity.ok(resumen);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    // Endpoint para obtener imagen de una noticia por su ID
    @GetMapping("/imagen/{id}")
    public ResponseEntity<?> obtenerImagen(@PathVariable int id) {
        try {
            String imagen = noticiaService.obtenerImagen(id);
            return ResponseEntity.ok(imagen);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    // Endpoint para obtener contenido de una noticia por su ID
    @GetMapping("/contenido/{id}")
    public ResponseEntity<?> obtenerContenido(@PathVariable int id) {
        try {
            String contenido = noticiaService.obtenerContenido(id);
            return ResponseEntity.ok(contenido);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    // Endpoint para obtener publicada de una noticia por su ID
    @GetMapping("/publicada/{id}")
    public ResponseEntity<?> obtenerPublicada(@PathVariable int id) {
        try {
            Character publicada = noticiaService.obtenerPublicada(id);
            return ResponseEntity.ok(publicada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    // Endpoint para obtener fecha publicacion de una noticia por su ID
    @GetMapping("/fechaPublicacion/{id}")
    public ResponseEntity<?> obtenerFechaPublicacion(@PathVariable int id) {
        try {
            LocalDate fecha = noticiaService.obtenerFechaPublicacion(id);
            return ResponseEntity.ok(fecha);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    // Endpoint para obtener idEmpresa de una noticia por su ID
    @GetMapping("/idEmpresa/{id}")
    public ResponseEntity<?> obtenerIdEmpresa(@PathVariable int id) {
        try {
            Integer idEmpresa = noticiaService.obtenerIdEmpresa(id);
            return ResponseEntity.ok(idEmpresa);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
