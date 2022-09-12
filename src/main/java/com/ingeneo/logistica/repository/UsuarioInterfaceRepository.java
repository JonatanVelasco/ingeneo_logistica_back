package com.ingeneo.logistica.repository;

import com.ingeneo.logistica.entities.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioInterfaceRepository extends CrudRepository<UsuarioEntity, Integer> {
    public UsuarioEntity findByUsername(String username);
}
