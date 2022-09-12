package com.ingeneo.logistica.repository;

import com.ingeneo.logistica.entities.ClienteEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClienteInterfaceRepository  extends CrudRepository<ClienteEntity, Integer> {
}
