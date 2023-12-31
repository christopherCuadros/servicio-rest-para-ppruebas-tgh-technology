package com.RestEnterprice.servicio.rest.para.ppruebas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Servicios;

@Repository
public interface ServicioRepository extends CrudRepository<Servicios,Integer>{

    // seleccion total con filtrado
    @Query("SELECT s FROM Servicios s WHERE (:estado IS NULL OR s.estadoServicio = :estado) " +
           "AND (:textoBusqueda IS NULL OR LOWER(s.nombreServicio) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')))"+
           "AND (:disponibilidad IS NULL OR s.disponibilidadServicio = :disponibilidad)")
    List<Servicios> buscarPorEstadoYNombre(@Param("estado") String estado, @Param("textoBusqueda") String textoBusqueda,@Param("disponibilidad") String disponibilidad);

}
