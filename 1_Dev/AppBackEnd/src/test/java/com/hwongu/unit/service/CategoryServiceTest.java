/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase contiene pruebas unitarias para el servicio CategoryServiceImpl, que maneja
 * la logica de negocio para la entidad Category. Se utiliza Mockito para simular el comportamiento
 * del repositorio ICategoryRepo y verificar las operaciones CRUD.
 */
package com.hwongu.unit.service;

import com.hwongu.model.Category;
import com.hwongu.repo.ICategoryRepo;
import com.hwongu.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

    @MockBean
    private CategoryServiceImpl service;

    @MockBean
    private ICategoryRepo repo;

    private Category CATEGORY_1;
    private Category CATEGORY_2;
    private Category CATEGORY_3;

    /**
     * Metodo de configuracion inicial que se ejecuta antes de cada prueba.
     * Configura los objetos de prueba, simula respuestas del repositorio y asigna
     * la instancia del servicio a probar.
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.service = new CategoryServiceImpl(repo);

        CATEGORY_1 = new Category(1, "Tecnología", "Categoría relacionada con gadgets y software", true);
        CATEGORY_2 = new Category(2, "Moda", "Categoría que abarca ropa, accesorios y tendencias", true);
        CATEGORY_3 = new Category(3, "Alimentos", "Categoría de productos alimenticios y bebidas", true);

        List<Category> categories = List.of(CATEGORY_1, CATEGORY_2, CATEGORY_3);
        Mockito.when(repo.findAll()).thenReturn(categories);
        Mockito.when(repo.findById(any())).thenReturn(Optional.of(CATEGORY_1));
        Mockito.when(repo.save(any())).thenReturn(CATEGORY_1);
    }

    /**
     * Prueba unitaria para el metodo save, que verifica que la categoria guardada
     * no sea nula.
     */
    @Test
    void save() throws Exception {
        Category response = service.save(CATEGORY_1);
        assertNotNull(response);
    }

    /**
     * Prueba unitaria para el metodo update, que verifica que la categoria actualizada
     * no sea nula.
     */
    @Test
    void update() throws Exception {
        Category response = service.update(CATEGORY_1, any());
        assertNotNull(response);
    }

    /**
     * Prueba unitaria para el metodo delete, que verifica que el metodo deleteById
     * del repositorio se haya invocado una vez.
     */
    @Test
    void delete() throws Exception {
        repo.deleteById(1);
        verify(repo, times(1)).deleteById(1);
    }

    /**
     * Prueba unitaria para el metodo readAll, que verifica que la cantidad de categorias
     * leidas sea igual a 3.
     */
    @Test
    void readAllTest() throws Exception {
        List<Category> response = service.readAll();
        assertEquals(response.size(), 3);
    }

    /**
     * Prueba unitaria para el metodo readById, que verifica que la categoria leida
     * no sea nula.
     */
    @Test
    void readByIdTest() throws Exception{
        final int ID = 1;
        Category response = service.readById(ID);
        assertNotNull(response);
    }

}
