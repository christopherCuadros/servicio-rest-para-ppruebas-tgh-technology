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

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Producto;
import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Servicios;
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

   
    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listarProductosActivos(@RequestParam(name = "estado", required = false) String estado,
            @RequestParam(name = "productname", required = false) String textoBusqueda) {
                List<Producto> prod = prodservice.listarProducto(estado, textoBusqueda);
                return ResponseEntity.status(HttpStatus.OK).body(prod);
    }

    
    @DeleteMapping("deleteProduct/{codigo}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer codigo){
        try {
            prodservice.deleteProduct(codigo);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @PutMapping("inactivarProduct/{codigo}")
    public ResponseEntity<Void> inactivarProducto(@PathVariable Integer codigo){
        try {
            prodservice.actualizarEstadoAInactivo(codigo);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("activarProduct/{codigo}")
    public ResponseEntity<Void> activarProducto(@PathVariable Integer codigo){
        try {
            prodservice.ActivarProducto(codigo);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
