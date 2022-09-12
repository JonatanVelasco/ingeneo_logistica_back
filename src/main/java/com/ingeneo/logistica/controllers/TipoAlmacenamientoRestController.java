package com.ingeneo.logistica.controllers;

import com.ingeneo.logistica.entities.TipoAlmacenamientoEntity;
import com.ingeneo.logistica.exceptions.BadRequestException;
import com.ingeneo.logistica.exceptions.DontFindElementException;
import com.ingeneo.logistica.services.Interfaces.TipoAlmacenamientoInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/tipoAlmacenamientos")
public class TipoAlmacenamientoRestController {

    @Autowired
    private TipoAlmacenamientoInterfaceService tipoAlmacenamientoInterfaceService;

    @GetMapping(value = "/listar")
    private ResponseEntity<List<TipoAlmacenamientoEntity>> listAll(){

        List<TipoAlmacenamientoEntity> tipoAlmacenamientos = tipoAlmacenamientoInterfaceService.listAll();

        System.out.println(tipoAlmacenamientos);
        if (tipoAlmacenamientos == null ){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok( tipoAlmacenamientos );
        }

    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public  ResponseEntity findById( @PathVariable("id") Integer id){
        TipoAlmacenamientoEntity tipoAlmacenamiento = tipoAlmacenamientoInterfaceService.findById(id);
        if (id < 0 ){
            throw new BadRequestException("id: " + id);
        }

        if (tipoAlmacenamiento == null){
            throw new DontFindElementException("id: " + id);
        }
        else{
            return ResponseEntity.ok(tipoAlmacenamiento);
        }
    }

    @PostMapping
    public ResponseEntity<TipoAlmacenamientoEntity> create(@RequestBody TipoAlmacenamientoEntity tipoAlmacenamiento){
        TipoAlmacenamientoEntity newTipoAlmacenamientoEntity = tipoAlmacenamientoInterfaceService.save(tipoAlmacenamiento);

        if (newTipoAlmacenamientoEntity == null){
            throw new BadRequestException("tipoAlmacenamiento: " + tipoAlmacenamiento);
        }
        else{
            return ResponseEntity.ok(newTipoAlmacenamientoEntity);
        }
    }

    @DeleteMapping(value = "{id}")
    public  ResponseEntity<TipoAlmacenamientoEntity> deleteById(@PathVariable("id") Integer id){
        TipoAlmacenamientoEntity tipoAlmacenamiento = tipoAlmacenamientoInterfaceService.findById(id);

        if (id < 0 ){
            throw new BadRequestException("id: " + id);
        }

        if (tipoAlmacenamiento == null){
            throw new DontFindElementException("id: " + id);
        }
        else{
            tipoAlmacenamientoInterfaceService.delete(id);
            return ResponseEntity.ok(tipoAlmacenamiento);
        }
    }

    @PutMapping
    public ResponseEntity<TipoAlmacenamientoEntity> update(@RequestBody TipoAlmacenamientoEntity tipoAlmacenamiento){
        TipoAlmacenamientoEntity newTipoAlmacenamientoEntity = tipoAlmacenamientoInterfaceService.findById(tipoAlmacenamiento.getId());

        if (tipoAlmacenamiento == null ){
            throw new BadRequestException("tipoAlmacenamientos: " + tipoAlmacenamiento);
        }

        if (newTipoAlmacenamientoEntity == null){
            throw new DontFindElementException("id: " + tipoAlmacenamiento.getId());
        }
        else{
            newTipoAlmacenamientoEntity.setNombre( tipoAlmacenamiento.getNombre() );
            tipoAlmacenamientoInterfaceService.save(newTipoAlmacenamientoEntity);
            return ResponseEntity.ok(newTipoAlmacenamientoEntity);
        }
    }
}
