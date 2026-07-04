/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase contiene pruebas unitarias para el controlador CategoryController utilizando
 * MockMvc para simular solicitudes HTTP y verificar el comportamiento del controlador.
 * Se prueban operaciones de creacion, actualizacion, eliminacion y lectura de categorias.
 */
package com.hwongu.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwongu.controller.CategoryController;
import com.hwongu.dto.CategoryDTO;
import com.hwongu.model.Category;
import com.hwongu.service.ICategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryService categoryService;

    @MockBean(name = "modelMapper")
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    Category CATEGORY_1 = new Category(1, "Tecnología", "Categoría relacionada con gadgets y software", true);
    Category CATEGORY_2 = new Category(2, "Moda", "Categoría que abarca ropa, accesorios y tendencias", true);
    Category CATEGORY_3 = new Category(3, "Alimentos", "Categoría de productos alimenticios y bebidas", true);
    CategoryDTO CATEGORYDTO_1 = new CategoryDTO(1, "Tecnología", "Categoría relacionada con gadgets y software", true);
    CategoryDTO CATEGORYDTO_2 = new CategoryDTO(2, "Moda", "Categoría que abarca ropa, accesorios y tendencias", true);
    CategoryDTO CATEGORYDTO_3 = new CategoryDTO(3, "Alimentos", "Categoría de productos alimenticios y bebidas", true);


    @Test
    void createTest() throws Exception{
        Mockito.when(categoryService.save(any())).thenReturn(CATEGORY_3);
        Mockito.when(modelMapper.map(CATEGORY_3, CategoryDTO.class)).thenReturn(CATEGORYDTO_3);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/categories/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CATEGORYDTO_3));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nameCategory", is("Alimentos")))
                .andExpect(jsonPath("$.enabledCategory", is(true)));
    }


    @Test
    void updateTest() throws Exception{
        int ID = 2;

        Mockito.when(categoryService.update(any(), any())).thenReturn(CATEGORY_2);
        Mockito.when(modelMapper.map(CATEGORY_2, CategoryDTO.class)).thenReturn(CATEGORYDTO_2);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/categories/update/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CATEGORYDTO_2));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameCategory", is("Moda")))
                .andExpect(jsonPath("$.enabledCategory", is(true)));
    }


    @Test
    public void deleteTest() throws Exception {
        final int ID_CATEGORY = 1;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/categories/delete/" + ID_CATEGORY)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());
    }

    /**
     * Prueba unitaria para la lectura de una categoria por su ID mediante una solicitud GET.
     * Verifica que la respuesta tenga un estado HTTP 200 (OK) y que el nombre de la categoria
     * leida sea correcto.
     */
    @Test
    void readById() throws Exception{
        final int ID = 1;

        Mockito.when(categoryService.readById(any())).thenReturn(CATEGORY_1);
        Mockito.when(modelMapper.map(CATEGORY_1, CategoryDTO.class)).thenReturn(CATEGORYDTO_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/categories/readById/" + ID)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameCategory", is("Tecnología")));
    }

}
