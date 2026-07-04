/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase es un controlador REST para manejar las solicitudes HTTP relacionadas con las categorias.
 * Define endpoints para crear, actualizar, eliminar y buscar categorias. Utiliza CategoryDTO para
 * transferir datos entre la capa de presentacion y la capa de logica de negocio.
 */
package com.hwongu.controller;

import com.hwongu.dto.CategoryDTO;
import com.hwongu.model.Category;
import com.hwongu.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
public class CategoryController {


    // Servicio de categoria utilizado para realizar operaciones de negocio
    private final ICategoryService categoryService;
    // ModelMapper utilizado para convertir entre Category y CategoryDTO
    @Qualifier("modelMapper")
    private final ModelMapper modelMapper;


    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO dto) throws Exception{
        Category objectCreated = this.categoryService.save(this.convertToEntity(dto));
        CategoryDTO object = this.convertToDto(objectCreated);
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }


    @PostMapping("/createSql")
    public ResponseEntity<Integer> createCategoryBySql(@Valid @RequestBody CategoryDTO dto) throws Exception{
        return new ResponseEntity<>( this.categoryService.insertCategory(this.convertToEntity(dto)), HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Integer id, @Valid @RequestBody CategoryDTO dto) throws Exception{
        dto.setIdCategory(id);
        Category objectUpdated = this.categoryService.update(this.convertToEntity(dto), id);
        CategoryDTO object = this.convertToDto(objectUpdated);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
         this.categoryService.delete(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Lee todas las categorias existentes.
     *
     * @return una lista de todas las categorias como objetos DTO, con el estado HTTP 200 (OK).
     * @throws Exception si ocurre un error durante la lectura.
     */
    @GetMapping("/readAll")
    public ResponseEntity<List<CategoryDTO>> readAll() throws Exception{
        List<CategoryDTO> list = this.categoryService.findByNameLike("%").stream().map(e -> this.convertToDto(e)).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Lee una categoria por su identificador.
     *
     * @param id el identificador de la categoria a leer.
     * @return la categoria encontrada como un objeto DTO, con el estado HTTP 200 (OK).
     * @throws Exception si ocurre un error durante la lectura.
     */
    @GetMapping("/readById/{id}")
    public ResponseEntity<CategoryDTO> readById(@PathVariable("id") Integer id) throws Exception{
        CategoryDTO object = this.convertToDto(this.categoryService.readById(id));
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    /**
     * Encuentra categorias cuyo nombre coincide con el parametro especificado.
     *
     * @param name el nombre o parte del nombre de la categoria que se desea buscar.
     * @return una lista de categorias coincidentes como objetos DTO, con el estado HTTP 200 (OK).
     * @throws Exception si ocurre un error durante la busqueda.
     */
    @GetMapping("/find/byName")
    public ResponseEntity<List<CategoryDTO>> findByName(@RequestParam("name") String name) throws Exception{
        List<CategoryDTO> list = this.categoryService.findByNameLike(name).stream().map(e -> this.convertToDto(e)).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Encuentra categorias habilitadas cuyo nombre coincide con el parametro especificado.
     *
     * @param name el nombre o parte del nombre de la categoria que se desea buscar.
     * @return una lista de categorias habilitadas coincidentes como objetos DTO, con el estado HTTP 200 (OK).
     * @throws Exception si ocurre un error durante la busqueda.
     */
    @GetMapping("/find/byNameIsEnabled")
    public ResponseEntity<List<CategoryDTO>> findCategoryByNameIsEnabled(@RequestParam("name") String name) throws Exception{
        List<CategoryDTO> list = this.categoryService.findCategoryByNameIsEnabled(name).stream().map(e -> this.convertToDto(e)).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Convierte una entidad Category en un objeto DTO de categoria.
     *
     * @param obj la entidad Category a convertir.
     * @return el objeto convertido en un CategoryDTO.
     */
    private CategoryDTO convertToDto(Category obj){
        return this.modelMapper.map(obj, CategoryDTO.class);
    }

    /**
     * Convierte un objeto DTO de categoria en una entidad Category.
     *
     * @param dto el objeto CategoryDTO a convertir.
     * @return la entidad convertida en Category.
     */
    private Category convertToEntity(CategoryDTO dto){
        return this.modelMapper.map(dto, Category.class);
    }

}
