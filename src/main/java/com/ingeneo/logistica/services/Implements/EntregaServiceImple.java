package com.ingeneo.logistica.services.Implements;

import com.ingeneo.logistica.entities.EntregaEntity;
import com.ingeneo.logistica.repository.EntregaInterfaceRepository;
import com.ingeneo.logistica.services.Interfaces.EntregaInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service("serviceEntrega")
public class EntregaServiceImple implements EntregaInterfaceService {

    @Autowired
    private EntregaInterfaceRepository entregaInterfaceRepository;

    @Override
    public List<EntregaEntity> listAll() {
        return (List<EntregaEntity>) entregaInterfaceRepository.findAll();
    }

    @Override
    public EntregaEntity save(EntregaEntity entrega) {
        return entregaInterfaceRepository.save(entrega);
    }

    @Override
    public List<EntregaEntity> obtenerEntregasConNombres() {
        return (List<EntregaEntity>) entregaInterfaceRepository.obtenerEntregasConNombres();
    }

    @Override
    public List<EntregaEntity> listByClient(Integer id) {
        return (List<EntregaEntity>) entregaInterfaceRepository.listByClient(id);
    }

    @Override
    public Integer insertar(EntregaEntity entrega) {
        return entregaInterfaceRepository.insertar(
                entrega.getCant_producto(),
                entrega.getDescuento(),
                entrega.getFecha_entrega(),
                entrega.getFecha_registro(),
                entrega.getNro_transporte(),
                entrega.getNro_guia(),
                entrega.getPrecio_envio(),
                entrega.getFk_almacenamiento(),
                entrega.getFk_cliente(),
                entrega.getFk_tipo_producto(),
                entrega.getFk_tipo_logistica()
        );
    }

    @Override
    public Integer update(EntregaEntity entrega) {
        return entregaInterfaceRepository.update(
                entrega.getId(),
                entrega.getCant_producto(),
                entrega.getDescuento(),
                entrega.getFecha_entrega(),
                entrega.getFecha_registro(),
                entrega.getNro_transporte(),
                entrega.getNro_guia(),
                entrega.getPrecio_envio(),
                entrega.getFk_almacenamiento(),
                entrega.getFk_cliente(),
                entrega.getFk_tipo_producto(),
                entrega.getFk_tipo_logistica()
        );
    }

    @Override
    public EntregaEntity findById2(Integer id) {
        return entregaInterfaceRepository.findById2(id);
    }


    @Override
    public EntregaEntity findById(Integer id) {
        return entregaInterfaceRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        entregaInterfaceRepository.deleteById(id);
    }

    @Override
    public boolean validateIdentificacion(int tipoEntrega, String identificacion) {
        System.out.println(tipoEntrega);

        if (tipoEntrega==0){
            return validateFlota(identificacion);
        }
        else if(tipoEntrega==1 ){
            return validatePlaca(identificacion);
        }

        return false;
    }

    @Override
    public Double generateDiscount(int tipoEntrega, Integer cantidad_productos) {

        if (cantidad_productos > 10 ){
            if (tipoEntrega==0){
                return 0.03;
            }
            else if (tipoEntrega==1){
                return 0.05;
            }
        }

        return 0.0;

    }

    public boolean validatePlaca(String placa) {
        Pattern pat = Pattern.compile("^[A-Za-z]{3}([0-9]{3}$)");
        Matcher mat = pat.matcher(placa);
        System.out.println(mat.matches());
        return mat.matches();
    }

    public boolean validateFlota(String flota) {
        Pattern pat = Pattern.compile("^[A-Za-z]{4}([0-9]{3}$)");
        Matcher mat = pat.matcher(flota);
        System.out.println(mat.matches());
        return mat.matches();
    }
}
