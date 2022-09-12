package com.ingeneo.logistica.controllers;


import com.ingeneo.logistica.entities.TipoProductoEntity;
import com.ingeneo.logistica.exceptions.BadRequestException;
import com.ingeneo.logistica.exceptions.DontFindElementException;
import com.ingeneo.logistica.services.Interfaces.TipoProductoInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/tipoProductos")
public class TipoProductoRestController {

    @Autowired
    private TipoProductoInterfaceService tipoProductoInterfaceService;

    @GetMapping(value = "/listar")
    private ResponseEntity<List<TipoProductoEntity>> listAll(){

        List<TipoProductoEntity> tipoProductos = tipoProductoInterfaceService.listAll();

        System.out.println(tipoProductos);
        if (tipoProductos == null ){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok( tipoProductos );
        }

    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public  ResponseEntity findById( @PathVariable("id") Integer id){
        TipoProductoEntity tipoProducto = tipoProductoInterfaceService.findById(id);
        if (id < 0 ){
            throw new BadRequestException("id: " + id);
        }

        if (tipoProducto == null){
            throw new DontFindElementException("id: " + id);
        }
        else{
            return ResponseEntity.ok(tipoProducto);
        }
    }

    @PostMapping
    public ResponseEntity<TipoProductoEntity> create(@RequestBody TipoProductoEntity tipoProducto){
        TipoProductoEntity newTipoProductoEntity = tipoProductoInterfaceService.save(tipoProducto);

        if (newTipoProductoEntity == null){
            throw new BadRequestException("tipoProducto: " + tipoProducto);
        }
        else{
            return ResponseEntity.ok(newTipoProductoEntity);
        }
    }

    @DeleteMapping(value = "{id}")
    public  ResponseEntity<TipoProductoEntity> deleteById(@PathVariable("id") Integer id){
        TipoProductoEntity tipoProducto = tipoProductoInterfaceService.findById(id);

        if (id < 0 ){
            throw new BadRequestException("id: " + id);
        }

        if (tipoProducto == null){
            throw new DontFindElementException("id: " + id);
        }
        else{
            tipoProductoInterfaceService.delete(id);
            return ResponseEntity.ok(tipoProducto);
        }
    }

    @PutMapping
    public ResponseEntity<TipoProductoEntity> update(@RequestBody TipoProductoEntity tipoProducto){
        TipoProductoEntity newTipoProductoEntity = tipoProductoInterfaceService.findById(tipoProducto.getId());

        if (tipoProducto == null ){
            throw new BadRequestException("tipoProductos: " + tipoProducto);
        }

        if (newTipoProductoEntity == null){
            throw new DontFindElementException("id: " + tipoProducto.getId());
        }
        else{
            newTipoProductoEntity.setDescripcion( tipoProducto.getDescripcion() );
            tipoProductoInterfaceService.save(newTipoProductoEntity);
            return ResponseEntity.ok(newTipoProductoEntity);
        }
    }
}
