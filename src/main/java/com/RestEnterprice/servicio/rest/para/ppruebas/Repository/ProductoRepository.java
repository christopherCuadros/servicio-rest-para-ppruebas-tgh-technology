package com.RestEnterprice.servicio.rest.para.ppruebas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Producto;


public interface ProductoRepository extends CrudRepository<Producto,Integer>{

    @Query("SELECT s FROM Producto s WHERE (:estado IS NULL OR s.estado = :estado) " +
           "AND (:textoBusqueda IS NULL OR LOWER(s.nombre) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')))")
    List<Producto> buscarPorEstadoYNombre(@Param("estado") String estado, @Param("textoBusqueda") String textoBusqueda);
    
}
