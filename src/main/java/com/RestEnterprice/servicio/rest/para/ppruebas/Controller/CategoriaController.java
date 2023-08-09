package com.RestEnterprice.servicio.rest.para.ppruebas.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Categoria;
import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Personal;
import com.RestEnterprice.servicio.rest.para.ppruebas.service.CategoriaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService catservice;

    @PostMapping("/guardarcategoria")
    public ResponseEntity<Void> createServicio(@RequestBody Categoria serv) {
        try {
            catservice.savecategoria(serv);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/listarcategoria")
    public ResponseEntity<List<Categoria>> listarPersonal() {
        List<Categoria> catego = (List<Categoria>) catservice.allCategoria();
        return ResponseEntity.status(HttpStatus.OK).body(catego);
    }
    
}
