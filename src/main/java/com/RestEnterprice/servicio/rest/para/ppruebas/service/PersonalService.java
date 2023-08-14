package com.RestEnterprice.servicio.rest.para.ppruebas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Personal;
import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Servicios;
import com.RestEnterprice.servicio.rest.para.ppruebas.Repository.PersonalRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PersonalService {

    private final PersonalRepository personalrepo;


    public void savePersonal(Personal perso){
        personalrepo.save(perso);
    }

    // LISTAR
    public List<Personal> listarPersonal(String estado, String textoBusqueda, String disponibilidad) {
        return personalrepo.buscarPorEstadoYNombre(estado, textoBusqueda,disponibilidad);
    }


    public void deletePersonal(Integer id){
        if(id!=null){
            personalrepo.deleteById(id);
        }
    }

    // Actualizar estado del servicio
    public void actualizarEstadoAInactivo(Integer id) {
        Optional<Personal> serv = personalrepo.findById(id);
        if(serv.isPresent()){
            Personal obtenservice = serv.get();
            obtenservice.setEstado("Inactivo");
            personalrepo.save(obtenservice);
        }
    }
    
}
