package com.RestEnterprice.servicio.rest.para.ppruebas.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.RestEnterprice.servicio.rest.para.ppruebas.controlFiles;
import com.RestEnterprice.servicio.rest.para.ppruebas.DAO.ServicioDAO;
import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Producto;
import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Servicios;
import com.RestEnterprice.servicio.rest.para.ppruebas.service.ServiciosService;


import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/servicio")
public class ServiciosController {

    private final ServiciosService servicioservice;
    private final controlFiles controlFiles;
    private final HttpServletRequest request;


    @PostMapping("/guardar")
    public ResponseEntity<Void> createServicio(@RequestBody Servicios serv) {
        try {
            servicioservice.saveServicio(serv);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/saveServiceWitdImage")
    public ResponseEntity<Void> createServiceWitdImage(@ModelAttribute ServicioDAO servicio,@RequestParam(name = "file", required = false) MultipartFile file){
        try {
            String nameOrigin = controlFiles.storage(file);
            String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
            String url = UriComponentsBuilder
                        .fromHttpUrl(host)
                        .path("/servicio/")
                        .path(nameOrigin)
                        .toUriString();

            servicioservice.saveServiceWitdImage(servicio, url);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Servicios>> listarServiciosActivos(@RequestParam(name = "estado", required = false) String estado,
            @RequestParam(name = "nameservice", required = false) String textoBusqueda,
            @RequestParam(name = "disponibilidad", required = false) String disponibilidad) {
                List<Servicios> servicios = servicioservice.listarServicios(estado, textoBusqueda,disponibilidad);
                return ResponseEntity.status(HttpStatus.OK).body(servicios);
    }

    @GetMapping("/listarServiceImage")
    public ResponseEntity<List<Servicios>> listarServiciosconImagen(@RequestParam(name = "estado", required = false) String estado,
            @RequestParam(name = "nameservice", required = false) String textoBusqueda,
            @RequestParam(name = "disponibilidad", required = false) String disponibilidad) {
                List<Servicios> servicios = servicioservice.listarServicios(estado, textoBusqueda,disponibilidad);

                if(servicios.isEmpty()){
                    return ResponseEntity.noContent().build();
                }
                List<Servicios> servicosResponse = servicios.stream()
                .map(serv -> new Servicios(serv.getIdServicio(),serv.getNombreServicio(), serv.getDescripcion(),serv.getPrecio(),serv.getEstadoServicio(),serv.getDisponibilidadServicio(),serv.getRutaImagen()))
                .collect(Collectors.toList());

                return ResponseEntity.ok(servicosResponse);
    }


    
    @DeleteMapping("/deleteService/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Integer id) {
        try {
            servicioservice.deleteService(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/inactivarServicio/{codigo}")
    public ResponseEntity<Void> inactivarServicio(@PathVariable Integer codigo) {
        try {
            servicioservice.actualizarEstadoAInactivo(codigo);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/activarService/{id}")
    public ResponseEntity<Void> getActivarService(@PathVariable Integer id){
        try {
            servicioservice.activarEstadoaActivo(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    

    @GetMapping("{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException{
        Resource file = controlFiles.loadResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(file);
    }
    
}
