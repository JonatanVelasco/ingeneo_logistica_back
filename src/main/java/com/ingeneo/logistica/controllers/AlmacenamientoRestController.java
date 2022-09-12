package com.ingeneo.logistica.controllers;

import com.ingeneo.logistica.entities.AlmacenamientoEntity;
import com.ingeneo.logistica.exceptions.BadRequestException;
import com.ingeneo.logistica.exceptions.DontFindElementException;
import com.ingeneo.logistica.services.Interfaces.AlmacenamientoInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/almacenamientos")
public class AlmacenamientoRestController {

    @Autowired
    private AlmacenamientoInterfaceService almacenamientoInterfaceService;

    @GetMapping(value = "/listar")
    private ResponseEntity<List<AlmacenamientoEntity>> listAll() {

        List<AlmacenamientoEntity> almacenes = almacenamientoInterfaceService.obtenerConTipoAlmacenamiento();

        if (almacenes == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(almacenes);
        }
    }

    @PostMapping
    public ResponseEntity<AlmacenamientoEntity> create(@RequestBody AlmacenamientoEntity almacen) {
        if (almacen == null) {
            throw new BadRequestException("almacen: " + almacen);
        }

        Integer idAlmacenamientoEntity = almacenamientoInterfaceService.insertar(almacen);

        if (idAlmacenamientoEntity == null) {
            throw new BadRequestException("almacen: " + almacen);
        } else {
            return ResponseEntity.ok(almacenamientoInterfaceService.findById2(idAlmacenamientoEntity));
        }
    }

/*    @RequestMapping(value = "listar/cliente/{id}", method = RequestMethod.GET)
    private ResponseEntity<List<AlmacenamientoEntity>> listByClient(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new BadRequestException("id: " + id);
        }

        List<AlmacenamientoEntity> almacenes = almacenamientoInterfaceService.listByClient(id);

        if (almacenes == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(almacenes);
        }
    }*/

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new BadRequestException("id: " + id);
        }

        AlmacenamientoEntity almacen = almacenamientoInterfaceService.findById2(id);

        if (almacen == null) {
            throw new DontFindElementException("id: " + id);
        } else {
            return ResponseEntity.ok(almacen);
        }
    }


    @DeleteMapping(value = "{id}")
    public ResponseEntity<AlmacenamientoEntity> deleteById(@PathVariable("id") Integer id) {
        AlmacenamientoEntity almacen = almacenamientoInterfaceService.findById(id);

        if (id < 0) {
            throw new BadRequestException("id: " + id);
        }

        if (almacen == null) {
            throw new DontFindElementException("id: " + id);
        } else {
            almacenamientoInterfaceService.delete(id);
            return ResponseEntity.ok(almacen);
        }
    }

    @PutMapping
    public ResponseEntity<AlmacenamientoEntity> update(@RequestBody AlmacenamientoEntity almacen) {

        AlmacenamientoEntity newAlmacenamientoEntity = almacenamientoInterfaceService.findById2(almacen.getId());

        if (almacen == null) {
            throw new BadRequestException("almacenes: " + almacen);
        }

        if (newAlmacenamientoEntity == null) {
            throw new DontFindElementException("id: " + almacen.getId());
        }
        else {
            almacenamientoInterfaceService.update(almacen);
            return ResponseEntity.ok(almacen);
        }
    }
}
