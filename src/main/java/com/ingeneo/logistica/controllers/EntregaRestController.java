package com.ingeneo.logistica.controllers;

import com.ingeneo.logistica.entities.EntregaEntity;
import com.ingeneo.logistica.exceptions.BadRequestException;
import com.ingeneo.logistica.exceptions.DontFindElementException;
import com.ingeneo.logistica.services.Interfaces.EntregaInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/entregas")
public class EntregaRestController {

    @Autowired
    private EntregaInterfaceService entregaInterfaceService;

    @GetMapping(value = "/listar")
    private ResponseEntity<List<EntregaEntity>> listAll() {

        List<EntregaEntity> entregas = entregaInterfaceService.obtenerEntregasConNombres();

        if (entregas == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(entregas);
        }
    }

    @RequestMapping(value = "listar/cliente/{id}", method = RequestMethod.GET)
    private ResponseEntity<List<EntregaEntity>> listByClient(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new BadRequestException("id: " + id);
        }
        List<EntregaEntity> entregas = entregaInterfaceService.listByClient(id);

        if (entregas == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(entregas);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        //System.out.println("findById - " + id);
        EntregaEntity entrega = entregaInterfaceService.findById2(id);
        if (id < 0) {
            throw new BadRequestException("id: " + id);
        }

        if (entrega == null) {
            throw new DontFindElementException("id: " + id);
        } else {
            return ResponseEntity.ok(entrega);
        }
    }

    @PostMapping
    public ResponseEntity<EntregaEntity> create(@RequestBody EntregaEntity entrega) {
        if (entrega == null) {
            throw new BadRequestException("entrega: " + entrega);
        }

        if (entregaInterfaceService.validateIdentificacion(entrega.getFk_tipo_logistica(), entrega.getNro_transporte())){
            entrega.setDescuento(
                    entregaInterfaceService.generateDiscount(entrega.getFk_tipo_logistica(), entrega.getCant_producto())
            );
        }
        else{
            if (entrega.getFk_tipo_logistica()==0){
                throw new BadRequestException("La identificacion para el tipo de logistica MARITIMA: Debe contener 4 letras al inicio y 3 números al final: " +entrega.getNro_transporte() );
            }
            else if(entrega.getFk_tipo_logistica()==1){
                throw new BadRequestException("La identificacion para el tipo de logistica TERRESTRE: Debe contener 3 letras al inicio y 3 números al final: " + entrega.getNro_transporte());
            }
        }

        Integer idEntregaEntity = entregaInterfaceService.insertar(entrega);

        if (idEntregaEntity == null) {
            throw new BadRequestException("entrega: " + entrega);
        } else {
            return ResponseEntity.ok(entregaInterfaceService.findById2( idEntregaEntity));
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<EntregaEntity> deleteById(@PathVariable("id") Integer id) {
        EntregaEntity entrega = entregaInterfaceService.findById2(id);

        if (id < 0) {
            throw new BadRequestException("id: " + id);
        }

        if (entrega == null) {
            throw new DontFindElementException("id: " + id);
        } else {
            entregaInterfaceService.delete(id);
            return ResponseEntity.ok(entrega);
        }
    }

    @PutMapping
    public ResponseEntity<EntregaEntity> update(@RequestBody EntregaEntity entrega) {
        if (entrega == null) {
            throw new BadRequestException("entrega: " + entrega);
        }

        EntregaEntity newEntregaEntity= entregaInterfaceService.findById2( entrega.getId() );

        if (newEntregaEntity == null) {
            throw new DontFindElementException("id: " + entrega.getId());
        }
        else {
            if (entregaInterfaceService.validateIdentificacion(entrega.getFk_tipo_logistica(), entrega.getNro_transporte())){
                entrega.setDescuento(
                        entregaInterfaceService.generateDiscount(entrega.getFk_tipo_logistica(), entrega.getCant_producto())
                );
            }
            else{
                if (entrega.getFk_tipo_logistica()==0){
                    throw new BadRequestException("El tipo de logistica MARITIMA: Debe contener 4 letras al inicio y 3 números al final");
                }
                else if(entrega.getFk_tipo_logistica()==1){
                    throw new BadRequestException("El tipo de logistica TERRESTRE: Debe contener 3 letras al inicio y 3 números al final");
                }
            }
            entregaInterfaceService.update(entrega);
            return ResponseEntity.ok(entrega);
        }
    }
}
