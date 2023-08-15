package com.RestEnterprice.servicio.rest.para.ppruebas.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Servicios;
import com.RestEnterprice.servicio.rest.para.ppruebas.service.ServiciosService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/servicio")
public class ServiciosController {

    private final ServiciosService servicioservice;


    @PostMapping("/guardar")
    public ResponseEntity<Void> createServicio(@RequestBody Servicios serv) {
        try {
            servicioservice.saveServicio(serv);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Servicios>> listarServiciosActivos(@RequestParam(name = "estado", required = false) String estado,
            @RequestParam(name = "nameservice", required = false) String textoBusqueda) {
                List<Servicios> servicios = servicioservice.listarServicios(estado, textoBusqueda);
                return ResponseEntity.status(HttpStatus.OK).body(servicios);
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
    
    
}
