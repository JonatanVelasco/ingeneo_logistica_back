package com.ingeneo.logistica.controllers;

import com.ingeneo.logistica.entities.TipoLogisticaEntity;
import com.ingeneo.logistica.exceptions.BadRequestException;
import com.ingeneo.logistica.exceptions.DontFindElementException;
import com.ingeneo.logistica.services.Interfaces.TipoLogisticaInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/tipoLogisticas")
public class TipoLogisticaRestController {

    @Autowired
    private TipoLogisticaInterfaceService tipoLogisticaInterfaceService;

    @GetMapping(value = "/listar")
    private ResponseEntity<List<TipoLogisticaEntity>> listAll(){

        List<TipoLogisticaEntity> tipoLogisticas = tipoLogisticaInterfaceService.listAll();

        System.out.println(tipoLogisticas);
        if (tipoLogisticas == null ){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok( tipoLogisticas );
        }

    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public  ResponseEntity findById( @PathVariable("id") Integer id){
        TipoLogisticaEntity tipoLogistica = tipoLogisticaInterfaceService.findById(id);
        if (id < 0 ){
            throw new BadRequestException("id: " + id);
        }

        if (tipoLogistica == null){
            throw new DontFindElementException("id: " + id);
        }
        else{
            return ResponseEntity.ok(tipoLogistica);
        }
    }

    @PostMapping
    public ResponseEntity<TipoLogisticaEntity> create(@RequestBody TipoLogisticaEntity tipoLogistica){
        TipoLogisticaEntity newTipoLogisticaEntity = tipoLogisticaInterfaceService.save(tipoLogistica);

        if (newTipoLogisticaEntity == null){
            throw new BadRequestException("tipoLogistica: " + tipoLogistica);
        }
        else{
            return ResponseEntity.ok(newTipoLogisticaEntity);
        }
    }

    @DeleteMapping(value = "{id}")
    public  ResponseEntity<TipoLogisticaEntity> deleteById(@PathVariable("id") Integer id){
        TipoLogisticaEntity tipoLogistica = tipoLogisticaInterfaceService.findById(id);

        if (id < 0 ){
            throw new BadRequestException("id: " + id);
        }

        if (tipoLogistica == null){
            throw new DontFindElementException("id: " + id);
        }
        else{
            tipoLogisticaInterfaceService.delete(id);
            return ResponseEntity.ok(tipoLogistica);
        }
    }

    @PutMapping
    public ResponseEntity<TipoLogisticaEntity> update(@RequestBody TipoLogisticaEntity tipoLogistica){
        TipoLogisticaEntity newTipoLogisticaEntity = tipoLogisticaInterfaceService.findById(tipoLogistica.getId());

        if (tipoLogistica == null ){
            throw new BadRequestException("tipoLogisticas: " + tipoLogistica);
        }

        if (newTipoLogisticaEntity == null){
            throw new DontFindElementException("id: " + tipoLogistica.getId());
        }
        else{
            newTipoLogisticaEntity.setNombre( tipoLogistica.getNombre() );
            tipoLogisticaInterfaceService.save(newTipoLogisticaEntity);
            return ResponseEntity.ok(newTipoLogisticaEntity);
        }
    }
}
