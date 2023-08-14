package com.RestEnterprice.servicio.rest.para.ppruebas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Personal;


public interface PersonalRepository extends CrudRepository<Personal,Integer>{

    @Query("SELECT s FROM Personal s WHERE (:estado IS NULL OR s.estado = :estado) " +
       "AND (:textoBusqueda IS NULL OR LOWER(s.apellidos) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))) " +
       "AND (:disponibilidad IS NULL OR s.disponibilidad = :disponibilidad)")
    List<Personal> buscarPorEstadoYNombre(@Param("estado") String estado, @Param("textoBusqueda") String textoBusqueda, @Param("disponibilidad") String disponibilidad);

    
}
