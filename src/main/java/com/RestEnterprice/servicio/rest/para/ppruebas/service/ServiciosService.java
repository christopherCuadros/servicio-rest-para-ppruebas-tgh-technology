package com.RestEnterprice.servicio.rest.para.ppruebas.service;

import org.springframework.stereotype.Service;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Servicios;
import com.RestEnterprice.servicio.rest.para.ppruebas.Repository.ServicioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiciosService {

    private final ServicioRepository servicesrepo;


    // GUARDAR
    public void saveServicio(Servicios serv){
        servicesrepo.save(serv);
    }

    // LISTAR
    public Iterable<Servicios> allServices(){
        return servicesrepo.findAll();
    }


    
}
