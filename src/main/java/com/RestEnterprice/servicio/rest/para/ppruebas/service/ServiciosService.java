package com.RestEnterprice.servicio.rest.para.ppruebas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.RestEnterprice.servicio.rest.para.ppruebas.DAO.ProductoDao;
import com.RestEnterprice.servicio.rest.para.ppruebas.DAO.ServicioDAO;
import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Producto;
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

    //GUARDAR CON IMAGENES
    public void saveServiceWitdImage(ServicioDAO serv, String url){
            Servicios servicios = new Servicios();
            servicios.setNombreServicio(serv.getNombreServicio());
            servicios.setDescripcion(serv.getDescripcion());
            servicios.setEstadoServicio(serv.getEstadoServicio());
            servicios.setDisponibilidadServicio(serv.getDisponibilidadServicio());
            servicios.setPrecio(serv.getPrecio());
            servicios.setRutaImagen(url);
            servicesrepo.save(servicios);
     
        
    }

    // LISTAR
    public List<Servicios> listarServicios(String estado, String textoBusqueda,String disponibilidad) {
        return servicesrepo.buscarPorEstadoYNombre(estado, textoBusqueda,disponibilidad);
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
