/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta interfaz representa el repositorio de la entidad Category, permitiendo realizar
 * operaciones de consulta y manipulacion de datos en la base de datos para las categorias.
 */
package com.hwongu.repo;

import com.hwongu.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ICategoryRepo extends IPostgreRepo<Category, Integer> {


    List<Category> findByNameLikeOrderByName(String categoryName);


    @Query("FROM Category c where c.name like :categoryName and c.enabled = :categoryEnabled")
    List<Category> findCategoryByNameEnabled(@Param("categoryName") String categoryName, @Param("categoryEnabled") boolean categoryEnabled);

}
