/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase representa una excepcion personalizada que se lanza cuando un modelo o entidad
 * especifica no se encuentra en la base de datos o en la aplicacion. Extiende RuntimeException
 * para permitir su uso en tiempo de ejecucion sin necesidad de manejo obligatorio.
 */
package com.hwongu.exception;

/**
 * La clase ModelNotFoundException es una excepcion personalizada que se utiliza para indicar
 * que un modelo o entidad no fue encontrado. Hereda de RuntimeException para que sea una
 * excepcion no verificada.
 */
public class ModelNotFoundException extends RuntimeException{

    /**
     * Constructor que permite inicializar la excepcion con un mensaje especifico.
     *
     * @param message el mensaje descriptivo de la excepcion.
     */
    public ModelNotFoundException(String message) {
        super(message);
    }
}
