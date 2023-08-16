package com.RestEnterprice.servicio.rest.para.ppruebas.service;

import java.util.List;
import java.util.Optional;

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

    public List<Categoria> getAllCategories(String estado, String buscar){
        return categoriarepo.buscarPorEstadoYNombre(estado, buscar);  
    }

    public void inactivarCategoria(Integer id){
        Optional<Categoria> cat = categoriarepo.findById(id);
        if(cat.isPresent()){
            Categoria categoria = cat.get();
            categoria.setEstado("Inactivo");
            categoriarepo.save(categoria);
        }
    }

    public void activarCategoria(Integer id){
        Optional<Categoria> cat = categoriarepo.findById(id);
        if(cat.isPresent()){
            Categoria categoria = cat.get();
            categoria.setEstado("Activo");
            categoriarepo.save(categoria);
        }
    }

    public void deleteCategoria(Integer id){
        Optional<Categoria> cat = categoriarepo.findById(id);
        if(cat.isPresent()){
            categoriarepo.deleteById(id);
        }
    }

}
