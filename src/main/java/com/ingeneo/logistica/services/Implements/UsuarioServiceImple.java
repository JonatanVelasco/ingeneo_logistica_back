package com.ingeneo.logistica.services.Implements;

import com.ingeneo.logistica.entities.UsuarioEntity;
import com.ingeneo.logistica.exceptions.BadRequestException;
import com.ingeneo.logistica.repository.UsuarioInterfaceRepository;
import com.ingeneo.logistica.services.Interfaces.UsuarioInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;*/
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("serviceUsuario")
public class UsuarioServiceImple implements UsuarioInterfaceService {

    @Autowired
    private UsuarioInterfaceRepository usuarioInterfaceRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImple(UsuarioInterfaceRepository usuarioInterfaceRepository) {
        this.usuarioInterfaceRepository = usuarioInterfaceRepository;
    }


    @Override
    public List<UsuarioEntity> listAll() {
        return (List<UsuarioEntity>) usuarioInterfaceRepository.findAll();
    }

    @Override
    public UsuarioEntity save(UsuarioEntity usuario) {
        if (usuario == null){
            throw new BadRequestException("usuario: " + usuario);
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()) );
        return usuarioInterfaceRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true) // vuelve el objeto una transaccion
    public UsuarioEntity findById(Integer id) {
        return usuarioInterfaceRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        usuarioInterfaceRepository.deleteById(id);
    }
}
