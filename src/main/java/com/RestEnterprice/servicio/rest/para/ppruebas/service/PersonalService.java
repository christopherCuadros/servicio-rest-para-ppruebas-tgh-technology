package com.RestEnterprice.servicio.rest.para.ppruebas.service;

import org.springframework.stereotype.Service;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Personal;
import com.RestEnterprice.servicio.rest.para.ppruebas.Repository.PersonalRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PersonalService {

    private final PersonalRepository personalrepo;


    public void savePersonal(Personal perso){
        personalrepo.save(perso);
    }

    public Iterable<Personal> allPersonal(){
        return personalrepo.findAll();
    }
    
}
