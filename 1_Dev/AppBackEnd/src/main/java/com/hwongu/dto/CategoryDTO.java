/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase representa el objeto de transferencia de datos (DTO) para la entidad Category.
 * Se utiliza para transportar datos de categoria entre la capa de presentacion y la capa de logica de negocio.
 * Incluye validaciones para asegurar la integridad de los datos y solo incluye campos no nulos en la respuesta JSON.
 */
package com.hwongu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase CategoryDTO define los atributos de la categoria que seran transferidos a traves
 * de la aplicacion. Utiliza validaciones para asegurar que los datos sean validos antes
 * de ser procesados.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {

    /**
     * Identificador de la categoria.
     */
    private int idCategory;
    /**
     * Nombre de la categoria. Este campo es obligatorio, no puede estar vacio y tiene
     * un tamaño maximo de 50 caracteres.
     */
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50, message = "{name.size}")
    private String nameCategory;
    /**
     * Descripcion de la categoria. Este campo es obligatorio, no puede estar vacio y tiene
     * un tamaño maximo de 500 caracteres.
     */
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 500, message = "{description.size}")
    private String descriptionCategory;
    /**
     * Estado de la categoria, que indica si esta habilitada (true) o deshabilitada (false).
     * Este campo es obligatorio.
     */
    @NotNull
    private boolean enabledCategory;

}
