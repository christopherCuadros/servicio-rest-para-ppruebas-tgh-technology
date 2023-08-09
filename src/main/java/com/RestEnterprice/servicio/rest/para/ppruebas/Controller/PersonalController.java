package com.RestEnterprice.servicio.rest.para.ppruebas.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/listarpersonal")
    public ResponseEntity<List<Personal>> listarPersonal() {
        List<Personal> product = (List<Personal>) personalservice.allPersonal();
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
    
}
