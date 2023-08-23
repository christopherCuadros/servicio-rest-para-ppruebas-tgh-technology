package com.RestEnterprice.servicio.rest.para.ppruebas.Controller;

import java.io.IOException;
import org.springframework.http.HttpHeaders;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;


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
import org.springframework.core.io.Resource;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Producto;
import com.RestEnterprice.servicio.rest.para.ppruebas.service.ProductoService;

import jakarta.servlet.http.HttpServletRequest;

import com.RestEnterprice.servicio.rest.para.ppruebas.controlFiles;
import com.RestEnterprice.servicio.rest.para.ppruebas.DAO.ProductoDao;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService prodservice;
    private final controlFiles controlFiles;
    private final HttpServletRequest request;

    @PostMapping("/guardarProducto")
    public ResponseEntity<Void> createServicio(@RequestBody Producto serv) {
        try {
            prodservice.saveProducto(serv);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/saveProductoImage")
    public ResponseEntity<Void> saveProductoImage(@ModelAttribute ProductoDao prod, @RequestParam(name ="file", required=false) MultipartFile file) {
        try {
            String nameOrigin = controlFiles.storage(file);
            String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
            String url = UriComponentsBuilder
                        .fromHttpUrl(host)
                        .path("/producto/")
                        .path(nameOrigin)
                        .toUriString();

            prodservice.saveProductoWitdImage(prod, url);
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

    @GetMapping("/listarWitdImage")
    public ResponseEntity<List<Producto>> getAllProducts(@RequestParam(name = "estado", required = false) String estado,
            @RequestParam(name = "productname", required = false) String textoBusqueda) {

        List<Producto> prod = prodservice.listarProducto(estado, textoBusqueda);

        if (prod.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<Producto> productoResponses = prod.stream()
            .map(producto -> new Producto(producto.getId(),producto.getNombre(),producto.getDescripcion(),producto.getPrecio(),producto.getStock(),producto.getEstado(),producto.getRutaImagen()))
            .collect(Collectors.toList());

        return ResponseEntity.ok(productoResponses);
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
            prodservice.activarProducto(codigo);
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
