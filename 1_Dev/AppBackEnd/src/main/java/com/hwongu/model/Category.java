/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase representa una entidad Category mapeada a la tabla "category" en la base de datos.
 * Se utiliza para almacenar informacion sobre diferentes categorias e incluye atributos como
 * el ID de categoria, nombre, descripcion y estado (habilitado/deshabilitado).
*/

package com.hwongu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * La clase Category es una entidad de modelo para la tabla "category".
 * Incluye los siguientes atributos:
 * - idCategory: identificador unico para cada categoria, auto-generado.
 * - name: el nombre de la categoria, requerido y limitado a 50 caracteres.
 * - description: una descripcion de la categoria, requerida y limitada a 500 caracteres.
 * - enabled: indica si la categoria esta activa (true) o inactiva (false).
 *
 * La clase utiliza anotaciones de Lombok para generar getter, setter, equals, hashcode y constructores.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "category")
public class Category {

    /**
     * Llave primaria de la categoria, auto-generada usando la estrategia IDENTITY.
     * Este campo esta incluido en los metodos equals y hashcode.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_category")
    private Integer idCategory;
    /**
     * Nombre de la categoria. Este campo es requerido y tiene un maximo de 50 caracteres.
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    /**
     * Descripcion de la categoria. Este campo es requerido y tiene un maximo de 500 caracteres.
     */
    @Column(name = "description", nullable = false, length = 500)
    private String description;
    /**
     * Estado de la categoria que indica si esta habilitada (true) o deshabilitada (false).
     */
    @Column(nullable = false)
    private boolean enabled;

}
