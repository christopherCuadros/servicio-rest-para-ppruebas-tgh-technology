package com.RestEnterprice.servicio.rest.para.ppruebas.service;

import java.util.List;

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

    public List<Administrador> getAllAdmins(String estado, String apellido){
        return adminrepo.buscarPorEstadoYNombre(estado, apellido);
    }
}
