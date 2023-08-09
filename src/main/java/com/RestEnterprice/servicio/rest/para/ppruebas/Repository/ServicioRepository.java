package com.RestEnterprice.servicio.rest.para.ppruebas.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Servicios;

@Repository
public interface ServicioRepository extends CrudRepository<Servicios,Integer>{
    
}
