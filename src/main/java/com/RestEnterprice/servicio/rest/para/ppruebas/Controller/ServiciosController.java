package com.RestEnterprice.servicio.rest.para.ppruebas.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<List<Servicios>> listarServicios() {
        List<Servicios> servicios = (List<Servicios>) servicioservice.allServices();
        return ResponseEntity.status(HttpStatus.OK).body(servicios);
    }
    
    
}
