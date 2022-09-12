package com.ingeneo.logistica.controllers;

import com.ingeneo.logistica.entities.UsuarioEntity;
import com.ingeneo.logistica.exceptions.BadRequestException;
import com.ingeneo.logistica.exceptions.DontFindElementException;
import com.ingeneo.logistica.services.Interfaces.UsuarioInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuariosRestController {
    @Autowired
    public UsuarioInterfaceService iServiceUsuario;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<UsuarioEntity>> findAll(){
        List<UsuarioEntity> usuarios = iServiceUsuario.listAll();
        if (usuarios.size() > 0){
            return ResponseEntity.ok( usuarios );
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET) // /api/users/{id} -> /api/users/1
    public  ResponseEntity findById( @PathVariable("id") Integer id){
        UsuarioEntity usuario = iServiceUsuario.findById(id);
        if (id < 0 ){
            throw new BadRequestException("id: " + id);
        }

        if (usuario == null){
            throw new DontFindElementException("id: " + id);
        }
        else{
            return ResponseEntity.ok(usuario);
        }
   }

   @PostMapping
   public ResponseEntity<UsuarioEntity> create(@RequestBody UsuarioEntity usuario){
       if (usuario == null){
           throw new BadRequestException("usuario: " + usuario);
       }

        UsuarioEntity newUsuario = iServiceUsuario.save(usuario);

       if (newUsuario == null){
           throw new BadRequestException("usuario: " + usuario);
       }
       else{
           return ResponseEntity.ok(newUsuario);
       }
   }

   @DeleteMapping(value = "{id}")
   public  ResponseEntity<UsuarioEntity> deleteById(@PathVariable("id") Integer id){
       UsuarioEntity usuario = iServiceUsuario.findById(id);

       if (id < 0 ){
           throw new BadRequestException("id: " + id);
       }

       if (usuario == null){
           throw new DontFindElementException("id: " + id);
       }
       else{
           iServiceUsuario.delete(id);
           return ResponseEntity.ok(usuario);
       }
   }

   @PutMapping
   public ResponseEntity<UsuarioEntity> update(@RequestBody UsuarioEntity usuario){
       UsuarioEntity newUsuario = iServiceUsuario.findById(usuario.getId());

       if (usuario == null ){
           throw new BadRequestException("usuarioss: " + usuario);
       }

       if (newUsuario == null){
           throw new DontFindElementException("id: " + usuario.getId());
       }
       else{
           newUsuario.setUsername( usuario.getUsername() );
           newUsuario.setPassword( usuario.getPassword() );

           iServiceUsuario.save(newUsuario);
           return ResponseEntity.ok(newUsuario);
       }
   }

}
