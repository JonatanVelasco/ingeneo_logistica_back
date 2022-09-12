package com.ingeneo.logistica.services.Interfaces;

import com.ingeneo.logistica.entities.RolEntity;

import java.util.List;

public interface RolInterfaceService {
    public List<RolEntity> listAll();

    public RolEntity save(RolEntity rol);

    public RolEntity findById(Integer id);

    public void delete(Integer id);
}
