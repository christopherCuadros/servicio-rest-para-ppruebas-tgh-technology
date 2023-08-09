package com.RestEnterprice.servicio.rest.para.ppruebas.service;

import org.springframework.stereotype.Service;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Administrador;
import com.RestEnterprice.servicio.rest.para.ppruebas.Repository.AdministradorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdministradorService {
    
    private final AdministradorRepository adminrepo;

    public void saveAdministrador(Administrador admin){
        adminrepo.save(admin);
    }

    public Iterable<Administrador> allAdmin(){
        return adminrepo.findAll();
    }
}
