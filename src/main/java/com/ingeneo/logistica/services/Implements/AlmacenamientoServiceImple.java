package com.ingeneo.logistica.services.Implements;

import com.ingeneo.logistica.entities.AlmacenamientoEntity;
import com.ingeneo.logistica.repository.AlmacenamientoInterfaceRepository;
import com.ingeneo.logistica.services.Interfaces.AlmacenamientoInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("serviceAlmacenamiento")
public class AlmacenamientoServiceImple implements AlmacenamientoInterfaceService {


    @Autowired
    private AlmacenamientoInterfaceRepository almacenamientoInterfaceRepository;

    public List<AlmacenamientoEntity> listAll() {
        return (List<AlmacenamientoEntity>) almacenamientoInterfaceRepository.findAll();
    }

    public List<AlmacenamientoEntity> obtenerConTipoAlmacenamiento() {
        return (List<AlmacenamientoEntity>) almacenamientoInterfaceRepository.obtenerConTipoAlmacenamiento();
    }

    @Override
    public AlmacenamientoEntity save(AlmacenamientoEntity almacen) {
        return almacenamientoInterfaceRepository.save(almacen);
    }
    @Override
    public Integer insertar(AlmacenamientoEntity almacenamiento) {
        return almacenamientoInterfaceRepository.insertar(
                almacenamiento.getNombre(),
                almacenamiento.getDescripcion(),
                almacenamiento.getUbicacion(),
                almacenamiento.getFk_tipo_almacenamiento()
        );
    }

    @Override
    public Integer update(AlmacenamientoEntity almacenamiento) {
        return almacenamientoInterfaceRepository.update(
                almacenamiento.getId(),
                almacenamiento.getNombre(),
                almacenamiento.getDescripcion(),
                almacenamiento.getUbicacion(),
                almacenamiento.getFk_tipo_almacenamiento()
        );
    }


    @Override
    public AlmacenamientoEntity findById(Integer id) {
        return almacenamientoInterfaceRepository.findById(id).orElse(null);
    }
    @Override
    public AlmacenamientoEntity findById2(Integer id) {
        return almacenamientoInterfaceRepository.findById2(id);
    }

    @Override
    public void delete(Integer id) {
        almacenamientoInterfaceRepository.deleteById(id);
    }

}
