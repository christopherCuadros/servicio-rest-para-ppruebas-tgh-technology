package com.RestEnterprice.servicio.rest.para.ppruebas.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RestEnterprice.servicio.rest.para.ppruebas.Model.Personal;
import com.RestEnterprice.servicio.rest.para.ppruebas.service.PersonalService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/personal")
public class PersonalController {

    private final PersonalService personalservice;

    @PostMapping("/guardarpersonal")
    public ResponseEntity<Void> createServicio(@RequestBody Personal serv) {
        try {
            personalservice.savePersonal(serv);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/listarPersonal")
    public ResponseEntity<List<Personal>> listarProductosActivos(@RequestParam(name = "estado", required = false) String estado,
            @RequestParam(name = "apellido", required = false) String lastName, @RequestParam(name = "disponibilidad", required = false) String disponibilidad) {
                List<Personal> personal = personalservice.listarPersonal(estado, lastName, disponibilidad);
                return ResponseEntity.status(HttpStatus.OK).body(personal);
    }

    @DeleteMapping("deletePersonal/{codigo}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer codigo){
        try {
            personalservice.deletePersonal(codigo);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    

    @PutMapping("inactivarPersonal/{codigo}")
    public ResponseEntity<Void> inactivarPersonal(@PathVariable Integer codigo){
        try {
            personalservice.actualizarEstadoAInactivo(codigo);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
