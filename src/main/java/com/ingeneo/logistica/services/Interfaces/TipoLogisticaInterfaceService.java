package com.ingeneo.logistica.services.Interfaces;

import com.ingeneo.logistica.entities.TipoLogisticaEntity;

import java.util.List;

public interface TipoLogisticaInterfaceService {
    public List<TipoLogisticaEntity> listAll();

    public TipoLogisticaEntity save(TipoLogisticaEntity almacen);

    public void delete(Integer id);

    TipoLogisticaEntity findById(Integer id);
}
