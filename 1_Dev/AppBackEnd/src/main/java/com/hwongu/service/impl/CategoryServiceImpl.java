/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase implementa el servicio de categoria (CategoryService) utilizando el repositorio
 * de categoria (ICategoryRepo) y extiende las operaciones CRUD genericas de CRUDImpl.
 * Proporciona metodos especificos para realizar consultas y manipulaciones adicionales
 * sobre las categorias, asi como para la insercion de nuevas categorias en la base de datos.
 */
package com.hwongu.service.impl;

import com.hwongu.model.Category;
import com.hwongu.repo.ICategoryRepo;
import com.hwongu.repo.IPostgreRepo;
import com.hwongu.service.ICategoryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * La clase CategoryServiceImpl es la implementacion de ICategoryService y utiliza ICategoryRepo
 * para acceder a la base de datos de categorias. Esta clase define metodos adicionales
 * para realizar consultas personalizadas y para insertar categorias.
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends CRUDImpl<Category, Integer> implements ICategoryService {

    // Repositorio de categoria utilizado para operaciones de persistencia
    private final ICategoryRepo categoryRepository;

    // EntityManager utilizado para ejecutar consultas nativas e interacciones directas con la base de datos
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Obtiene el repositorio de PostgreSQL para realizar operaciones CRUD sobre la entidad Category.
     *
     * @return el repositorio de PostgreSQL para Category.
     */
    @Override
    public IPostgreRepo<Category, Integer> getPostgreRepo() {
        return this.categoryRepository;
    }

    /**
     * Encuentra una lista de categorias cuyo nombre coincide parcial o completamente con
     * el parametro categoryName, y las ordena alfabeticamente.
     *
     * @param categoryName el nombre o parte del nombre de la categoria que se desea buscar.
     * @return una lista de categorias que coinciden con el criterio de busqueda.
     */
    @Override
    public List<Category> findByNameLike(String categoryName) {
        return this.categoryRepository.findByNameLikeOrderByName("%" + categoryName + "%");
    }

    /**
     * Encuentra una lista de categorias cuyo nombre coincide con el parametro categoryName
     * y que estan habilitadas.
     *
     * @param categoryName el nombre o parte del nombre de la categoria que se desea buscar.
     * @return una lista de categorias habilitadas que coinciden con el criterio de busqueda.
     */
    @Override
    public List<Category> findCategoryByNameIsEnabled(String categoryName) {
        return this.categoryRepository.findCategoryByNameEnabled("%" + categoryName + "%", true);
    }

    /**
     * Inserta una nueva categoria en la base de datos utilizando una consulta SQL nativa.
     * Este metodo es transaccional.
     *
     * Nota: El metodo actual tiene un riesgo de inyeccion SQL y no cumple con las recomendaciones de SONARQUBE.
     *
     * @param category la categoria que se desea insertar.
     * @return el numero de filas afectadas por la insercion.
     */
    @Transactional
    @Override
    public Integer insertCategory(Category category) {
        // Codigo con error de SONARQUBE
        String sql = "INSERT INTO categoria (nombre, descripcion, activo) VALUES ('" +
                category.getName() + "', '" +
                category.getDescription() + "', " +
                (category.isEnabled() ? 1 : 0) + ")";
        Integer numRows = entityManager.createNativeQuery(sql).executeUpdate();
        entityManager.flush();
        entityManager.clear();
        return numRows;
    }
}
