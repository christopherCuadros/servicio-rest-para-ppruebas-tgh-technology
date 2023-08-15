package com.RestEnterprice.servicio.rest.para.ppruebas.service;

import java.util.List;
import java.util.Optional;

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
    public List<Servicios> listarServicios(String estado, String textoBusqueda) {
        return servicesrepo.buscarPorEstadoYNombre(estado, textoBusqueda);
    }

    // Eliminar
    public void deleteService(Integer id){
        if(id!=null){
            servicesrepo.deleteById(id);
        }
    }

    // Actualizar estado del servicio
    public void actualizarEstadoAInactivo(Integer id) {
        Optional<Servicios> serv = servicesrepo.findById(id);
        if(serv.isPresent()){
            Servicios obtenservice = serv.get();
            obtenservice.setEstadoServicio("Inactivo");
            servicesrepo.save(obtenservice);
        }
    }
    
    public void activarEstadoaActivo(Integer id){
        Optional<Servicios> serv = servicesrepo.findById(id);
        if(serv.isPresent()){
            Servicios oserv = serv.get();
            oserv.setEstadoServicio("Activo");
            servicesrepo.save(oserv);
        }
    }

    
}
