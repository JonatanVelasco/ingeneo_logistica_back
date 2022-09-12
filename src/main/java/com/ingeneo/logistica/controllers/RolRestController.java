package com.ingeneo.logistica.controllers;

import com.ingeneo.logistica.entities.RolEntity;
import com.ingeneo.logistica.exceptions.BadRequestException;
import com.ingeneo.logistica.exceptions.DontFindElementException;
import com.ingeneo.logistica.services.Interfaces.RolInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/roles")
public class RolRestController {

    @Autowired
    private RolInterfaceService rolInterfaceService;

    @GetMapping(value = "/listar")
    private ResponseEntity<List<RolEntity>> listAll(){

        List<RolEntity> roles = rolInterfaceService.listAll();

        if (roles == null ){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok( roles );
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public  ResponseEntity findById( @PathVariable("id") Integer id){
        RolEntity rol = rolInterfaceService.findById(id);
        if (id < 0 ){
            throw new BadRequestException("id: " + id);
        }

        if (rol == null){
            throw new DontFindElementException("id: " + id);
        }
        else{
            return ResponseEntity.ok(rol);
        }
    }

    @PostMapping
    public ResponseEntity<RolEntity> create(@RequestBody RolEntity rol){
        RolEntity newRol = rolInterfaceService.save(rol);

        if (newRol == null){
            throw new BadRequestException("rol: " + rol);
        }
        else{
            return ResponseEntity.ok(newRol);
        }
    }

    @DeleteMapping(value = "{id}")
    public  ResponseEntity<RolEntity> deleteById(@PathVariable("id") Integer id){
        RolEntity rol = rolInterfaceService.findById(id);

        if (id < 0 ){
            throw new BadRequestException("id: " + id);
        }

        if (rol == null){
            throw new DontFindElementException("id: " + id);
        }
        else{
            rolInterfaceService.delete(id);
            return ResponseEntity.ok(rol);
        }
    }

    @PutMapping
    public ResponseEntity<RolEntity> update(@RequestBody RolEntity rol){
        RolEntity newRol = rolInterfaceService.findById(rol.getId());

        if (rol == null ){
            throw new BadRequestException("roles: " + rol);
        }

        if (newRol == null){
            throw new DontFindElementException("id: " + rol.getId());
        }
        else{
            newRol.setNombre( rol.getNombre() );
            rolInterfaceService.save(newRol);
            return ResponseEntity.ok(newRol);
        }
    }
}
