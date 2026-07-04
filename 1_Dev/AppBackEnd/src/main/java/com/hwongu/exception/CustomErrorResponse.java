/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase representa una respuesta de error personalizada que se utiliza para enviar
 * informacion detallada sobre los errores ocurridos en el sistema, incluyendo la fecha y hora,
 * un mensaje descriptivo y la ruta donde ocurrio el error.
 */
package com.hwongu.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * La clase CustomErrorResponse define la estructura de la respuesta de error personalizada.
 * Incluye informacion sobre el momento en que ocurrio el error, un mensaje explicativo y
 * la ruta especifica donde se produjo el error.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {

    /**
     * Fecha y hora en que ocurrio el error.
     */
    private LocalDateTime datetime;
    /**
     * Mensaje descriptivo del error.
     */
    private String message;
    /**
     * Ruta o endpoint en el cual se produjo el error.
     */
    private String path;
}
