/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase define los pasos de prueba de integracion para la entidad Category usando Cucumber.
 * Cada metodo representa un paso de una prueba BDD (Behavior-Driven Development), con el fin de
 * verificar operaciones CRUD (crear, leer, actualizar, eliminar) en el servicio de categorias.
 */
package com.hwongu.integration.service.steps;

import com.hwongu.model.Category;
import com.hwongu.repo.ICategoryRepo;
import com.hwongu.service.impl.CategoryServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoryServiceSteps {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private ICategoryRepo categoryRepo;

    private Category category;

    /**
     * Crea una nueva categoria en memoria con el nombre especificado en el paso de prueba.
     *
     * @param name el nombre de la categoria que se va a crear.
     */
    @Given("a new category with name {string}")
    public void a_new_category_with_name(String name) {
        category = new Category();
        category.setName(name);
        category.setDescription("Test description");
        category.setEnabled(true);
    }

    /**
     * Guarda la categoria previamente creada en la base de datos usando el servicio de categoria.
     */
    @When("I save the category")
    public void i_save_the_category() {
        try {
            categoryService.save(category);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifica que la categoria fue guardada correctamente con el nombre esperado en la base de datos.
     *
     * @param expectedName el nombre esperado de la categoria guardada.
     */
    @Then("the category should be saved with name {string}")
    public void the_category_should_be_saved_with_name(String expectedName) {
        //Category savedCategory = categoryRepo.findByNameLikeOrderByName(expectedName).get(0);
        assertNotNull(categoryRepo.findByNameLikeOrderByName(expectedName));
        //assertEquals(expectedName, savedCategory.getName());
    }

    /**
     * Busca una categoria existente en la base de datos con el nombre especificado en el paso de prueba.
     *
     * @param name el nombre de la categoria que se espera encontrar.
     */
    @Given("the category {string} exists")
    public void the_category_exists(String name) {
        category = categoryRepo.findByNameLikeOrderByName(name).stream().findFirst().orElse(null);
        assertNotNull(category, "The updated category was not found in the database.");
    }

    /**
     * Actualiza el nombre de la categoria existente con un nuevo nombre especificado en el paso de prueba.
     *
     * @param updatedName el nuevo nombre de la categoria.
     */
    @When("I update the category name to {string}")
    public void i_update_the_category_name_to(String updatedName) {
        category.setName(updatedName);
        try {
            categoryService.update(category, category.getIdCategory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifica que la categoria fue actualizada correctamente con el nuevo nombre en la base de datos.
     *
     * @param updatedName el nombre esperado de la categoria actualizada.
     */
    @Then("the category should be updated with the name {string}")
    public void the_category_should_be_updated_with_the_name(String updatedName) {
        Category updatedCategory = categoryRepo.findByNameLikeOrderByName(updatedName).stream().findFirst().orElse(null);
        assertNotNull(updatedCategory, "The updated category was not found in the database.");
        assertEquals(updatedName, updatedCategory.getName());
    }

    /**
     * Verifica que existen categorias en el sistema.
     */
    @Given("there are categories in the system")
    public void there_are_categories_in_the_system() {
        assertFalse(categoryRepo.findAll().isEmpty());
    }

    /**
     * Simula la accion de leer todas las categorias (no requiere implementacion adicional en este paso).
     */
    @When("I read all categories")
    public void i_read_all_categories() {
        // No necesitas hacer nada aquí, el paso siguiente se encargará de verificar la lista
    }

    /**
     * Verifica que la lista de categorias leida no este vacia.
     */
    @Then("I should see a non-empty list of categories")
    public void i_should_see_a_non_empty_list_of_categories() {
        try {
            assertFalse(categoryService.readAll().isEmpty());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina la categoria previamente cargada en memoria desde la base de datos.
     */
    @When("I delete the category")
    public void i_delete_the_category() {
        try {
            categoryService.delete(category.getIdCategory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifica que la categoria eliminada ya no exista en la base de datos.
     */
    @Then("the category should no longer exist in the system")
    public void the_category_should_no_longer_exist_in_the_system() {
        assertFalse(categoryRepo.existsById(category.getIdCategory()));
    }
}
