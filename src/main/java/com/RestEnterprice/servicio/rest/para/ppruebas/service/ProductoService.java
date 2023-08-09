package com.RestEnterprice.servicio.rest.para.ppruebas.service;

import org.springframework.stereotype.Service;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Producto;
import com.RestEnterprice.servicio.rest.para.ppruebas.Repository.ProductoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductoService {

    private final ProductoRepository productorepo;

    public void saveProducto(Producto prod){
        productorepo.save(prod);
    }


    public Iterable<Producto> allProducto(){
        return productorepo.findAll();
    }
    
}
