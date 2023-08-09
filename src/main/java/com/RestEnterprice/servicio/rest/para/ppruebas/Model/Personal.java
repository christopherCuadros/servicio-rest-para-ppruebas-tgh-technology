package com.RestEnterprice.servicio.rest.para.ppruebas.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "personal")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codigoPersonal;
    private String nombre;
    private String apellidos;
    private String dni;
    private String telefono;
    private String correo;
    private String estado;
    private String disponiblidad;
    
    public Integer getCodigoPersonal() {
        return codigoPersonal;
    }
    public void setCodigoPersonal(Integer codigoPersonal) {
        this.codigoPersonal = codigoPersonal;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getDisponiblidad() {
        return disponiblidad;
    }
    public void setDisponiblidad(String disponiblidad) {
        this.disponiblidad = disponiblidad;
    }

    
}
