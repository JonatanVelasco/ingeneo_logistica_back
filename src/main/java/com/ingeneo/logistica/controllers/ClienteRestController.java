package com.ingeneo.logistica.controllers;

import com.ingeneo.logistica.entities.ClienteEntity;
import com.ingeneo.logistica.exceptions.BadRequestException;
import com.ingeneo.logistica.exceptions.DontFindElementException;
import com.ingeneo.logistica.services.Interfaces.ClienteInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/clientes")
public class ClienteRestController {

    @Autowired
    private ClienteInterfaceService clienteInterfaceService;

    @GetMapping(value = "/listar")
    private ResponseEntity<List<ClienteEntity>> listAll(){

        List<ClienteEntity> clientees = clienteInterfaceService.listAll();

        System.out.println(clientees);
        if (clientees == null ){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok( clientees );
        }

    }

    @GetMapping(value = "/listSelect")
    private ResponseEntity<List<ClienteEntity>> listSelect(){

        List<ClienteEntity> clientees = clienteInterfaceService.listSelect();

        if (clientees == null ){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok( clientees );
        }

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public  ResponseEntity findById( @PathVariable("id") Integer id){
        ClienteEntity cliente = clienteInterfaceService.findById(id);
        if (id < 0 ){
            throw new BadRequestException("id: " + id);
        }

        if (cliente == null){
            throw new DontFindElementException("id: " + id);
        }
        else{
            return ResponseEntity.ok(cliente);
        }
    }

    @PostMapping
    public ResponseEntity<ClienteEntity> create(@RequestBody ClienteEntity cliente){
        ClienteEntity newClienteEntity = clienteInterfaceService.save(cliente);

        if (newClienteEntity == null){
            throw new BadRequestException("cliente: " + cliente);
        }
        else{
            return ResponseEntity.ok(newClienteEntity);
        }
    }

    @DeleteMapping(value = "{id}")
    public  ResponseEntity<ClienteEntity> deleteById(@PathVariable("id") Integer id){
        ClienteEntity cliente = clienteInterfaceService.findById(id);

        if (id < 0 ){
            throw new BadRequestException("id: " + id);
        }

        if (cliente == null){
            throw new DontFindElementException("id: " + id);
        }
        else{
            clienteInterfaceService.delete(id);
            return ResponseEntity.ok(cliente);
        }
    }

    @PutMapping
    public ResponseEntity<ClienteEntity> update(@RequestBody ClienteEntity cliente){
        ClienteEntity newClienteEntity = clienteInterfaceService.findById(cliente.getId());

        if (cliente == null ){
            throw new BadRequestException("clientes: " + cliente);
        }

        if (newClienteEntity == null){
            throw new DontFindElementException("id: " + cliente.getId());
        }
        else{
            newClienteEntity.setNombres( cliente.getNombres() );
            newClienteEntity.setApellidos( cliente.getApellidos() );
            newClienteEntity.setDocumento(cliente.getDocumento());
            newClienteEntity.setCorreo(cliente.getCorreo());
            newClienteEntity.setTelefono(cliente.getTelefono());
            clienteInterfaceService.save(newClienteEntity);
            return ResponseEntity.ok(newClienteEntity);
        }
    }

}
