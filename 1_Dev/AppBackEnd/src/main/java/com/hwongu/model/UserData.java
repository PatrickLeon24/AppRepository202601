/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase representa la entidad UserData mapeada a una tabla en la base de datos.
 * Se utiliza para almacenar informacion sobre los datos de usuario y contiene atributos
 * como el ID de usuario, nombre de usuario, contrasena y estado (habilitado/deshabilitado).
 */

package com.hwongu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * La clase UserData es una entidad de modelo para la representacion de datos de usuario.
 * Contiene los siguientes atributos:
 * - idUser: identificador unico de cada usuario.
 * - username: nombre de usuario, requerido y unico, con un maximo de 50 caracteres.
 * - password: contrasena del usuario, requerida y con un maximo de 250 caracteres.
 * - enabled: indica si el usuario esta activo (true) o inactivo (false).
 *
 * La clase utiliza anotaciones de Lombok para generar automaticamente getter, setter, equals,
 * hashcode y constructores.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class UserData {

    /**
     * Llave primaria del usuario, identificador unico incluido en equals y hashcode.
     */
    @Id
    @EqualsAndHashCode.Include
    private Integer idUser;
    /**
     * Nombre de usuario. Este campo es requerido, unico y tiene un maximo de 50 caracteres.
     */
    @Column(length = 50, nullable = false, unique = true)
    private String username;
    /**
     * Contrasena del usuario. Este campo es requerido y tiene un maximo de 250 caracteres.
     */
    @Column(length = 250, nullable = false)
    private String password;
    /**
     * Estado del usuario que indica si esta habilitado (true) o deshabilitado (false).
     */
    @Column(nullable = false)
    private boolean enabled;
}
