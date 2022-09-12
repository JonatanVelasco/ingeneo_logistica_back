package com.ingeneo.logistica.services.Interfaces;

import com.ingeneo.logistica.entities.EntregaEntity;

import java.util.List;

public interface EntregaInterfaceService {

    public List<EntregaEntity> listAll();

    public EntregaEntity save(EntregaEntity entrega);

    public List<EntregaEntity> obtenerEntregasConNombres();

    public List<EntregaEntity> listByClient(Integer id);

    public Integer insertar(EntregaEntity entrega);

    public Integer update(EntregaEntity entrega);

    public EntregaEntity findById2(Integer id);

    public EntregaEntity findById(Integer id);

    public void delete(Integer id);

    public boolean validateIdentificacion(int tipoEntrega, String documento);

    public Double generateDiscount(int tipoEntrega, Integer cantidad_productos);
}
