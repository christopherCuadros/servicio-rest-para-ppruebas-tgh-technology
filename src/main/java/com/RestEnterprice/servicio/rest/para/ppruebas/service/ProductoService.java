package com.RestEnterprice.servicio.rest.para.ppruebas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.RestEnterprice.servicio.rest.para.ppruebas.DAO.ProductoDao;
import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Producto;
import com.RestEnterprice.servicio.rest.para.ppruebas.Repository.ProductoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductoService {

    private final ProductoRepository productorepo;


    public void saveProducto(Producto prod){
        productorepo.save(prod);
    }

    public void saveProductoWitdImage(ProductoDao prod, String url){
        Producto producto = new Producto();
        producto.setNombre(prod.getNombre());
        producto.setDescripcion(prod.getDescripcion());
        producto.setPrecio(prod.getPrecio());
        producto.setStock(prod.getStock());
        producto.setEstado(prod.getEstado());
        producto.setRutaImagen(url);
        productorepo.save(producto);
    }


    public List<Producto> listarProducto(String estado, String textoBusqueda) {
        return productorepo.buscarPorEstadoYNombre(estado, textoBusqueda);
    }

    public void deleteProduct(Integer id){
        if(id!=null){
            productorepo.deleteById(id);
        }
    }

    // Actualizar estado del servicio
    public void actualizarEstadoAInactivo(Integer id) {
        Optional<Producto> prod =  productorepo.findById(id);
        if(prod.isPresent()){
            Producto obtenProd = prod.get();
            obtenProd.setEstado("Inactivo");
            productorepo.save(obtenProd);
        }
    }

    //Activar producto
    public void activarProducto(Integer id){
        Optional<Producto> prod = productorepo.findById(id);
        if(prod.isPresent()){
            Producto  producto= prod.get();
            producto.setEstado("Activo");
            productorepo.save(producto);
        }

    }
    
}
