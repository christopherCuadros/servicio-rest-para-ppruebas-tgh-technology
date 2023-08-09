package com.RestEnterprice.servicio.rest.para.ppruebas.Controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Administrador;
import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Categoria;
import com.RestEnterprice.servicio.rest.para.ppruebas.service.AdministradorService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdministradorController {

    private final AdministradorService adminservice;

    @PostMapping("/guardaradministrador")
    public ResponseEntity<Void> createServicio(@RequestBody Administrador admin) {
        try {
            adminservice.saveAdministrador(admin);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/listarAdministrador")
    public ResponseEntity<List<Administrador>> listarPersonal() {
        List<Administrador> catego = (List<Administrador>) adminservice.allAdmin();
        return ResponseEntity.status(HttpStatus.OK).body(catego);
    }

    
}
