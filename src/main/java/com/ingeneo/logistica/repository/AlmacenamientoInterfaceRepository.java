package com.ingeneo.logistica.repository;

import com.ingeneo.logistica.entities.AlmacenamientoEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


public interface AlmacenamientoInterfaceRepository extends CrudRepository<AlmacenamientoEntity, Integer> {

    /*
        estos metodos ya vienen de la interfaz de CrudRepository
        public Almacenamiento findById(Long id);
        public List<Almacenamiento> listAll();
        public void save(Almacenamiento almacenamiento);
        public void delete(Integer id);
    */
    @Query(value = "SELECT a.*, "
            + " t.nombre as nombretipoalmacenamiento"
            + " FROM almacenamientos a "
            + " INNER JOIN tipo_almacenamiento t ON a.fk_tipo_almacenamiento = t.id "
            , nativeQuery=true)
    public List<AlmacenamientoEntity> obtenerConTipoAlmacenamiento();

    @Query(value = "SELECT a.*, "
            + " t.nombre as nombretipoalmacenamiento"
            + " FROM almacenamientos a "
            + " INNER JOIN tipo_almacenamiento t ON a.fk_tipo_almacenamiento = t.id "
            + " WHERE a.id = :id "
            , nativeQuery=true)
    public AlmacenamientoEntity findById2(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO almacenamientos ( " +
            "   nombre, descripcion, ubicacion, fk_tipo_almacenamiento " +
            " ) " +
            " VALUES ( " +
            "   :nombre, :descripcion, :ubicacion, :fk_tipo_almacenamiento " +
            " ) "
            , nativeQuery=true)
    public Integer insertar(

            @Param("nombre") String nombre,
            @Param("descripcion") String descripcion,
            @Param("ubicacion") String ubicacion,
            @Param("fk_tipo_almacenamiento") Integer fk_tipo_almacenamiento

    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE almacenamientos SET " +
            "   nombre=:nombre, descripcion=:descripcion, ubicacion=:ubicacion, fk_tipo_almacenamiento=:fk_tipo_almacenamiento  " +
            " WHERE id = :id  "
            , nativeQuery=true)
    public Integer update(
            @Param("id") Integer id,
            @Param("nombre") String nombre,
            @Param("descripcion") String descripcion,
            @Param("ubicacion") String ubicacion,
            @Param("fk_tipo_almacenamiento") Integer fk_tipo_almacenamiento

    );

}
