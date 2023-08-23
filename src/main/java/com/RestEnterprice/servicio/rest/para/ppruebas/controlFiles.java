package com.RestEnterprice.servicio.rest.para.ppruebas;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

@Service
public class controlFiles{
    
    @Value("${media.location}")
    private String mediaLocation;

    private Path rootLocation;


    @PostConstruct
    public void init() {
        try {
            rootLocation = Paths.get(mediaLocation);
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String storage(MultipartFile file) {
        try{

            if(file.isEmpty()){
            throw new RuntimeException("fallo al cargar la imagen");
            }

            String filename = file.getOriginalFilename();
            Path destinationFile = rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();

            try(InputStream inputStream = file.getInputStream()){
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);

            }
            return filename;

        } catch(IOException e){
            throw new RuntimeException("failed to storage file ", e);
        }

        
        
    }

    public Resource loadResource(String filename) {
        
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource((file.toUri()));

            if(resource.exists() || resource.isReadable()){
                return resource;
            } else{
                throw new RuntimeException("no se encontro " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("NO SE LEYO EL ARCHIVO" + filename);
        }
        
    }
}
