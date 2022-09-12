package com.ingeneo.logistica.services.Interfaces;

import com.ingeneo.logistica.entities.TipoAlmacenamientoEntity;

import java.util.List;

public interface TipoAlmacenamientoInterfaceService {

    public List<TipoAlmacenamientoEntity> listAll();

    public TipoAlmacenamientoEntity save(TipoAlmacenamientoEntity almacen);

    public void delete(Integer id);

    TipoAlmacenamientoEntity findById(Integer id);
}
