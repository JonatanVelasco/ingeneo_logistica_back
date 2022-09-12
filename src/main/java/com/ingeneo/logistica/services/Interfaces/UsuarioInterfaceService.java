package com.ingeneo.logistica.services.Interfaces;

import com.ingeneo.logistica.entities.UsuarioEntity;

import java.util.List;

public interface UsuarioInterfaceService {
    public List<UsuarioEntity> listAll();

    public UsuarioEntity save(UsuarioEntity almacen);

    public void delete(Integer id);

    UsuarioEntity findById(Integer id);
}
