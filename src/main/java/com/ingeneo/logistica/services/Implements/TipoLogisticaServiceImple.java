package com.ingeneo.logistica.services.Implements;

import com.ingeneo.logistica.entities.TipoLogisticaEntity;
import com.ingeneo.logistica.repository.TipoLogisticaInterfaceRepository;
import com.ingeneo.logistica.services.Interfaces.TipoLogisticaInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("serviceTipoLogistica")
public class TipoLogisticaServiceImple implements TipoLogisticaInterfaceService {

    @Autowired
    private TipoLogisticaInterfaceRepository tipoLogisticaInterfaceRepository;

    @Override
    public List<TipoLogisticaEntity> listAll() {
        return (List<TipoLogisticaEntity>) tipoLogisticaInterfaceRepository.findAll();
    }

    @Override
    public TipoLogisticaEntity save(TipoLogisticaEntity cliente) {
        return tipoLogisticaInterfaceRepository.save(cliente);
    }

    @Override
    public TipoLogisticaEntity findById(Integer id) {
        return tipoLogisticaInterfaceRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        tipoLogisticaInterfaceRepository.deleteById(id);
    }
}
