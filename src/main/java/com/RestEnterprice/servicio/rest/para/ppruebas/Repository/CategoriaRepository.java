package com.RestEnterprice.servicio.rest.para.ppruebas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria,Integer>{
    
    @Query("SELECT s FROM Categoria s WHERE (:estado IS NULL OR s.estado = :estado) " +
       "AND (:textoBusqueda IS NULL OR LOWER(s.nombreCategoria) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))) ")
    List<Categoria> buscarPorEstadoYNombre(@Param("estado") String estado, @Param("textoBusqueda") String textoBusqueda);
}
