package com.RestEnterprice.servicio.rest.para.ppruebas.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Categoria;
import com.RestEnterprice.servicio.rest.para.ppruebas.service.CategoriaService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@AllArgsConstructor
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService catservice;

    // @PostMapping("/guardarcategoria")
    // public ResponseEntity<Void> createServicio(@RequestBody Categoria serv) {
    //     try {
    //         catservice.savecategoria(serv);
    //         return ResponseEntity.status(HttpStatus.OK).build();
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //     }
    // }
    @PostMapping("/guardarcategoria")
    public ResponseEntity<Void> createServicio(@RequestBody Categoria serv) {
        
        if(serv == null || serv.getNombreCategoria().isEmpty() || serv.getDescripcion().isEmpty() || serv.getEstado().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
            
        try {
            catservice.savecategoria(serv);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    

    @GetMapping("/listarcategoria")
    public ResponseEntity<List<Categoria>> listarPersonal(@RequestParam(name = "estado", required = false) String estado,@RequestParam(name= "nameCategory", required = false) String name) {
        List<Categoria> catego = catservice.getAllCategories(estado, name);
        return ResponseEntity.status(HttpStatus.OK).body(catego);
    }

    @PutMapping(value="inactivarCategoria/{id}")
    public ResponseEntity<Void> inactivarCategoria(@PathVariable Integer id) {
       try {
         catservice.inactivarCategoria(id);
         return ResponseEntity.ok().build();
    
       } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }

    @PutMapping(value="activarCategoria/{id}")
    public ResponseEntity<Void> activarCategoria(@PathVariable Integer id) {
       try {
         catservice.activarCategoria(id);
         return ResponseEntity.ok().build();
    
       } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }

    @DeleteMapping("eliminarcategoria/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Integer id){
        try {
            catservice.deleteCategoria(id);
            return ResponseEntity.status(HttpStatus.OK).build();
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
