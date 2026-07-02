/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta interfaz define el servicio para manejar operaciones especificas de la entidad Category,
 * extendiendo las operaciones CRUD genericas de ICRUDService y agregando metodos personalizados
 * para realizar consultas y manipulaciones adicionales sobre las categorias.
 */
package com.hwongu.service;

import com.hwongu.model.Category;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * La interfaz ICategoryService extiende de ICRUDService y define metodos adicionales especificos
 * para la entidad Category, permitiendo operaciones personalizadas que no estan incluidas en
 * el servicio CRUD generico.
 */
public interface ICategoryService extends ICRUDService<Category, Integer> {

    /**
     * Encuentra una lista de categorias cuyo nombre coincide parcial o completamente con
     * el parametro categoryName.
     *
     * @param categoryName el nombre o parte del nombre de la categoria que se desea buscar.
     * @return una lista de categorias que coinciden con el criterio de busqueda.
     */
    List<Category> findByNameLike(String categoryName);

    /**
     * Encuentra una lista de categorias cuyo nombre coincide con el parametro categoryName
     * y que estan habilitadas.
     *
     * @param categoryName el nombre o parte del nombre de la categoria que se desea buscar.
     * @return una lista de categorias habilitadas que coinciden con el criterio de busqueda.
     */
    List<Category> findCategoryByNameIsEnabled(String categoryName);

    /**
     * Inserta una nueva categoria en la base de datos.
     *
     * @param category la categoria que se desea insertar.
     * @return el ID de la categoria recien insertada.
     */
    Integer insertCategory(Category category);
}
