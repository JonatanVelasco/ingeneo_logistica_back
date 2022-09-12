package com.ingeneo.logistica.services.Interfaces;

import com.ingeneo.logistica.entities.TipoProductoEntity;

import java.util.List;

public interface TipoProductoInterfaceService {

    public List<TipoProductoEntity> listAll();

    public TipoProductoEntity save(TipoProductoEntity almacen);

    public void delete(Integer id);

    TipoProductoEntity findById(Integer id);
}
