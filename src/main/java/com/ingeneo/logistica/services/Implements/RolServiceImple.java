package com.ingeneo.logistica.services.Implements;

import com.ingeneo.logistica.entities.RolEntity;
import com.ingeneo.logistica.repository.RolInterfaceRepository;
import com.ingeneo.logistica.services.Interfaces.RolInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("serviceRol")
public class RolServiceImple implements RolInterfaceService {
    @Autowired
    private RolInterfaceRepository rolInterfaceRepository;


    @Override
    @Transactional(readOnly = true)
    public List<RolEntity> listAll() {
        return (List<RolEntity>) rolInterfaceRepository.findAll();
    }

    @Override
    public RolEntity save(RolEntity rol) {
        return rolInterfaceRepository.save(rol);
    }

    @Override
    @Transactional(readOnly = true)
    public RolEntity findById(Integer id) {
        return rolInterfaceRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        rolInterfaceRepository.deleteById(id);
    }
}
