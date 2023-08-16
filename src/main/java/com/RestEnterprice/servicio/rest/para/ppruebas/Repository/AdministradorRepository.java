package com.RestEnterprice.servicio.rest.para.ppruebas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Administrador;


public interface AdministradorRepository extends CrudRepository<Administrador,Integer>{
    
    @Query("SELECT s FROM Administrador s WHERE (:estado IS NULL OR s.estado = :estado) " +
           "AND (:textoBusqueda IS NULL OR LOWER(s.apellidos) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')))")
    List<Administrador> buscarPorEstadoYNombre(@Param("estado") String estado, @Param("textoBusqueda") String textoBusqueda);
}
