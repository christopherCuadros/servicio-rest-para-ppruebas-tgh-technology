package com.RestEnterprice.servicio.rest.para.ppruebas.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicio")
public class Servicios {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idServicio;
    private String nombreServicio;
    private String descripcion;
    private float precio;
    private String estadoServicio;
    private String disponibilidadServicio;
    
    public Integer getIdServicio() {
        return idServicio;
    }
    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }
    public String getNombreServicio() {
        return nombreServicio;
    }
    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public String getEstadoServicio() {
        return estadoServicio;
    }
    public void setEstadoServicio(String estadoServicio) {
        this.estadoServicio = estadoServicio;
    }
    public String getDisponibilidadServicio() {
        return disponibilidadServicio;
    }
    public void setDisponibilidadServicio(String disponibilidadServicio) {
        this.disponibilidadServicio = disponibilidadServicio;
    }

    
    
}
