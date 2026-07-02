/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase abstracta proporciona una implementacion generica para las operaciones CRUD
 * utilizando un repositorio de PostgreSQL. Implementa los metodos basicos de ICRUDService
 * y delega las operaciones de persistencia al repositorio especifico definido en cada
 * implementacion concreta.
 */
package com.hwongu.service.impl;

import com.hwongu.exception.ModelNotFoundException;
import com.hwongu.repo.IPostgreRepo;
import com.hwongu.service.ICRUDService;

import java.util.List;

/**
 * La clase CRUDImpl es una implementacion abstracta de ICRUDService que define los metodos CRUD
 * basicos para cualquier entidad. La logica de persistencia es manejada por un repositorio
 * PostgreSQL especifico proporcionado por cada subclase.
 *
 * @param <T> el tipo de la entidad.
 * @param <ID> el tipo del identificador de la entidad.
 */
public abstract class CRUDImpl<T, ID> implements ICRUDService<T, ID> {

    /**
     * Metodo abstracto que debe ser implementado por las subclases para proporcionar
     * el repositorio especifico de PostgreSQL que manejará las operaciones CRUD de la entidad.
     *
     * @return el repositorio PostgreSQL para la entidad.
     */
    protected abstract IPostgreRepo<T, ID> getPostgreRepo();

    /**
     * Guarda una nueva entidad en la base de datos.
     *
     * @param entity la entidad que se desea guardar.
     * @return la entidad guardada.
     * @throws Exception en caso de error al guardar la entidad.
     */
    @Override
    public T save(T entity) throws Exception {
        return getPostgreRepo().save(entity);
    }

    /**
     * Actualiza una entidad existente en la base de datos.
     *
     * @param entity la entidad con los datos actualizados.
     * @param id el identificador de la entidad que se desea actualizar.
     * @return la entidad actualizada.
     * @throws Exception si no se encuentra el ID o si ocurre un error en la actualizacion.
     */
    @Override
    public T update(T entity, ID id) throws Exception {
        getPostgreRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("No se encontro el ID: " + id));
        return getPostgreRepo().save(entity);
    }

    /**
     * Lee una entidad por su identificador.
     *
     * @param id el identificador de la entidad que se desea leer.
     * @return la entidad encontrada.
     * @throws Exception si no se encuentra el ID o si ocurre un error en la lectura.
     */
    @Override
    public T readById(ID id) throws Exception {
        return getPostgreRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("No se encontro el ID: " + id));
    }

    /**
     * Lee todas las entidades disponibles en la base de datos.
     *
     * @return una lista de todas las entidades.
     * @throws Exception en caso de error al leer las entidades.
     */
    @Override
    public List<T> readAll() throws Exception {
        return getPostgreRepo().findAll();
    }

    /**
     * Elimina una entidad por su identificador.
     *
     * @param id el identificador de la entidad que se desea eliminar.
     * @throws Exception si no se encuentra el ID o si ocurre un error al eliminar la entidad.
     */
    @Override
    public void delete(ID id) throws Exception {
        getPostgreRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("No se encontro el ID: " + id));
        getPostgreRepo().deleteById(id);
    }
}
