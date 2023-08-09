package com.RestEnterprice.servicio.rest.para.ppruebas.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Producto;
import com.RestEnterprice.servicio.rest.para.ppruebas.service.ProductoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService prodservice;

    @PostMapping("/guardarProducto")
    public ResponseEntity<Void> createServicio(@RequestBody Producto serv) {
        try {
            prodservice.saveProducto(serv);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/listarproducto")
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> product = (List<Producto>) prodservice.allProducto();
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
    
}
