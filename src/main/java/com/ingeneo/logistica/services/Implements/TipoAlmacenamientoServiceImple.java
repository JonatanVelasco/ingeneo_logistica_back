package com.ingeneo.logistica.services.Implements;


import com.ingeneo.logistica.entities.TipoAlmacenamientoEntity;
import com.ingeneo.logistica.repository.TipoAlmacenamientoInterfaceRepository;
import com.ingeneo.logistica.services.Interfaces.TipoAlmacenamientoInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("serviceTipoAlmacenamiento")
public class TipoAlmacenamientoServiceImple implements TipoAlmacenamientoInterfaceService {

    @Autowired
    private TipoAlmacenamientoInterfaceRepository tipoAlmacenamientoInterfaceRepository;

    @Override
    public List<TipoAlmacenamientoEntity> listAll() {
        return (List<TipoAlmacenamientoEntity>) tipoAlmacenamientoInterfaceRepository.findAll();
    }

    @Override
    public TipoAlmacenamientoEntity save(TipoAlmacenamientoEntity cliente) {
        return tipoAlmacenamientoInterfaceRepository.save(cliente);
    }

    @Override
    public TipoAlmacenamientoEntity findById(Integer id) {
        return tipoAlmacenamientoInterfaceRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        tipoAlmacenamientoInterfaceRepository.deleteById(id);
    }
}