package com.RestEnterprice.servicio.rest.para.ppruebas.service;

import org.springframework.stereotype.Service;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Categoria;
import com.RestEnterprice.servicio.rest.para.ppruebas.Repository.CategoriaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoriaService {
    
    private final CategoriaRepository categoriarepo;

    public void savecategoria(Categoria cat){
        categoriarepo.save(cat);
    }

    public Iterable<Categoria> allCategoria(){
        return categoriarepo.findAll();
    }
}
