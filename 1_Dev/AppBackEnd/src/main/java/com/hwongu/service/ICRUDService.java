/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta interfaz define un servicio CRUD generico para manejar operaciones de creacion, lectura,
 * actualizacion y eliminacion de entidades. Los tipos de entidad y su identificador son definidos
 * de manera generica, permitiendo su reutilizacion en diferentes entidades del sistema.
 */
package com.hwongu.service;

import java.util.List;

/**
 * La interfaz ICRUDService establece los metodos CRUD basicos para cualquier entidad.
 * Incluye metodos para guardar, actualizar, leer por ID, leer todos y eliminar entidades.
 *
 * @param <T> el tipo de la entidad.
 * @param <ID> el tipo del identificador de la entidad.
 */
public interface ICRUDService<T, ID>{

    /**
     * Guarda una nueva entidad en la base de datos.
     *
     * @param entity la entidad que se desea guardar.
     * @return la entidad guardada.
     * @throws Exception en caso de error al guardar la entidad.
     */
    T save(T entity) throws Exception;

    /**
     * Actualiza una entidad existente en la base de datos.
     *
     * @param entity la entidad con los datos actualizados.
     * @param id el identificador de la entidad que se desea actualizar.
     * @return la entidad actualizada.
     * @throws Exception en caso de error al actualizar la entidad.
     */
    T update(T entity, ID id) throws Exception;

    /**
     * Lee una entidad por su identificador.
     *
     * @param id el identificador de la entidad que se desea leer.
     * @return la entidad encontrada.
     * @throws Exception en caso de error al leer la entidad o si no se encuentra.
     */
    T readById(ID id) throws Exception;

    /**
     * Lee todas las entidades disponibles en la base de datos.
     *
     * @return una lista de todas las entidades.
     * @throws Exception en caso de error al leer las entidades.
     */
    List<T> readAll() throws Exception;

    /**
     * Elimina una entidad por su identificador.
     *
     * @param id el identificador de la entidad que se desea eliminar.
     * @throws Exception en caso de error al eliminar la entidad.
     */
    void delete(ID id) throws Exception;
}
