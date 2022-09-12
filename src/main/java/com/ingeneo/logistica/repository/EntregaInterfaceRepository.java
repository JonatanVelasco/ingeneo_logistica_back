package com.ingeneo.logistica.repository;

import com.ingeneo.logistica.entities.EntregaEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface EntregaInterfaceRepository extends CrudRepository<EntregaEntity, Integer> {

    @Query(value = "SELECT e.*, "
            + " c.nombres as nombre_cliente, "
            + " c.apellidos as apellido_cliente, "
            + " p.descripcion as nombre_tipo_producto, "
            + " a.nombre as nombre_almacenamiento, "
            + " l.nombre as nombre_tipo_logistica "
            + " FROM entregas e "
            + " INNER JOIN clientes c ON e.fk_cliente = c.id "
            + " INNER JOIN tipos_productos p ON e.fk_tipo_producto = p.id "
            + " INNER JOIN almacenamientos a ON e.fk_almacenamiento = a.id "
            + " INNER JOIN tipos_logisticas l ON e.fk_tipo_logistica = l.id "
            , nativeQuery=true)
    public List<EntregaEntity> obtenerEntregasConNombres();

    @Query(value = "SELECT e.*, "
            + " c.nombres as nombre_cliente, "
            + " p.descripcion as nombre_producto, "
            + " a.nombre as nombre_almacen "
            + " FROM entregas e "
            + " INNER JOIN clientes c ON e.fk_cliente = c.id "
            + " INNER JOIN tipos_productos p ON e.fk_tipo_producto = p.id "
            + " INNER JOIN almacenes a ON e.fk_almacenamiento = a.id "
            + " WHERE c.id = :id "
            , nativeQuery=true)
    public List<EntregaEntity> listByClient(@Param("id") Integer id);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO entregas ( " +
            "   nro_transporte,  nro_guia, cant_producto, fecha_registro, fecha_entrega, descuento, precio_envio, " +
            "   fk_cliente, fk_almacenamiento, fk_tipo_producto, fk_tipo_logistica " +
            " ) " +
            " VALUES ( " +
            "   :nro_transporte,  :nro_guia, :cant_producto, :fecha_registro, :fecha_entrega, :descuento, :precio_envio, " +
            "   :fk_cliente, :fk_almacenamiento, :fk_tipo_producto, :fk_tipo_logistica " +
            " ) "
            , nativeQuery=true)
    public Integer insertar(
            @Param("cant_producto") Integer cant_producto,
            @Param("descuento") Double descuento ,
            @Param("fecha_entrega") Date fecha_entrega ,
            @Param("fecha_registro") Date fecha_registro,
            @Param("nro_transporte") String nro_transporte,
            @Param("nro_guia") String nro_guia,
            @Param("precio_envio") Double precio_envio,
            @Param("fk_almacenamiento") Integer fk_almacenamiento,
            @Param("fk_cliente") Integer fk_cliente,
            @Param("fk_tipo_producto") Integer fk_tipo_producto,
            @Param("fk_tipo_logistica") Integer fk_tipo_logistica
    );

    @Query(value = "SELECT e.*, "
            + " c.nombres as nombre_cliente, "
            + " c.apellidos as apellido_cliente, "
            + " p.descripcion as nombre_tipo_producto, "
            + " a.nombre as nombre_almacenamiento, "
            + " l.nombre as nombre_tipo_logistica "
            + " FROM entregas e "
            + " INNER JOIN clientes c ON e.fk_cliente = c.id "
            + " INNER JOIN tipos_productos p ON e.fk_tipo_producto = p.id "
            + " INNER JOIN almacenamientos a ON e.fk_almacenamiento = a.id "
            + " INNER JOIN tipos_logisticas l ON e.fk_tipo_logistica = l.id "
            + " WHERE e.id = :id "
            , nativeQuery=true)
    public EntregaEntity findById2(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE entregas SET " +
            "   nro_transporte=:nro_transporte,  nro_guia=:nro_guia, cant_producto=:cant_producto, " +
            "  fecha_registro=:fecha_registro, fecha_entrega=:fecha_entrega, descuento=:descuento, precio_envio=:precio_envio, " +
            "   fk_cliente=:fk_cliente, fk_almacenamiento=:fk_almacenamiento, fk_tipo_producto=:fk_tipo_producto, fk_tipo_logistica=:fk_tipo_logistica " +
            " WHERE id=:id "
             , nativeQuery=true)
    public Integer update(
            @Param("id") Integer id,
            @Param("cant_producto") Integer cant_producto,
            @Param("descuento") Double descuento ,
            @Param("fecha_entrega") Date fecha_entrega ,
            @Param("fecha_registro") Date fecha_registro,
            @Param("nro_transporte") String nro_transporte,
            @Param("nro_guia") String nro_guia,
            @Param("precio_envio") Double precio_envio,
            @Param("fk_almacenamiento") Integer fk_almacenamiento,
            @Param("fk_cliente") Integer fk_cliente,
            @Param("fk_tipo_producto") Integer fk_tipo_producto,
            @Param("fk_tipo_logistica") Integer fk_tipo_logistica
    );
}
