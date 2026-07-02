/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta interfaz genérica representa un repositorio base para entidades en PostgreSQL.
 * Se utiliza para definir un repositorio generico que extiende de JpaRepository, proporcionando
 * metodos CRUD comunes para las entidades.
 */
package com.hwongu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * La interfaz IPostgreRepo es una interfaz base generica para repositorios, que extiende
 * de JpaRepository y no es una implementacion directa de un repositorio.
 * La anotacion NoRepositoryBean indica que esta interfaz no debe ser considerada como un
 * repositorio por Spring y que debe ser extendida por otros repositorios especificos.
 *
 * @param <T> el tipo de la entidad.
 * @param <ID> el tipo del identificador de la entidad.
 */
@NoRepositoryBean
public interface IPostgreRepo<T, ID> extends JpaRepository<T , ID> {

}
