/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase maneja globalmente las excepciones lanzadas en la aplicacion, proporcionando
 * respuestas detalladas y personalizadas para diferentes tipos de errores que puedan ocurrir.
 * Utiliza un CustomErrorResponse para devolver detalles como la fecha y hora, mensaje del error,
 * y la ruta donde ocurrio el problema.
 */
package com.hwongu.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * La clase GlobalErrorHandler es un manejador global de excepciones que intercepta y gestiona
 * diferentes tipos de errores en la aplicacion, enviando una respuesta estandarizada y
 * logueando los detalles del error.
 */
@Slf4j
@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    /**
     * Maneja todas las excepciones no controladas de manera especifica, devolviendo una respuesta
     * personalizada con el estado HTTP 500.
     *
     * @param ex la excepcion generica.
     * @param req la solicitud web donde ocurrio el error.
     * @return una respuesta de error personalizada con detalles del error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleAllException(Exception ex, WebRequest req){
        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        log.info("Exception executed: " + ex.getClass());
        log.info("Exception message: " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Maneja excepciones ModelNotFoundException, que ocurren cuando no se encuentra el modelo
     * solicitado, devolviendo un estado HTTP 404.
     *
     * @param ex la excepcion ModelNotFoundException.
     * @param req la solicitud web donde ocurrio el error.
     * @return una respuesta de error personalizada con detalles del error.
     */
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleModelNotFoundException(ModelNotFoundException ex, WebRequest req){
        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        log.info("Exception executed: " + ex.getClass());
        log.info("Exception message: " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja excepciones SQLException, que ocurren en casos de conflictos con la base de datos,
     * devolviendo un estado HTTP 409.
     *
     * @param ex la excepcion SQLException.
     * @param req la solicitud web donde ocurrio el error.
     * @return una respuesta de error personalizada con detalles del error.
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<CustomErrorResponse> handleSQLException(SQLException ex, WebRequest req){
        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        log.info("Exception executed: " + ex.getClass());
        log.info("Exception message: " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Maneja excepciones NoHandlerFoundException cuando no se encuentra un endpoint solicitado,
     * devolviendo un estado HTTP 404.
     *
     * @param ex la excepcion NoHandlerFoundException.
     * @param headers cabeceras HTTP de la solicitud.
     * @param status codigo de estado HTTP.
     * @param request la solicitud web donde ocurrio el error.
     * @return una respuesta de error personalizada con detalles del error.
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        CustomErrorResponse er = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        log.info("Exception executed: " + ex.getClass());
        log.info("Exception message: " + ex.getMessage());
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja excepciones MethodArgumentNotValidException cuando hay errores de validacion en los
     * argumentos de los metodos, devolviendo un estado HTTP 400.
     *
     * @param ex la excepcion MethodArgumentNotValidException.
     * @param headers cabeceras HTTP de la solicitud.
     * @param status codigo de estado HTTP.
     * @param request la solicitud web donde ocurrio el error.
     * @return una respuesta de error personalizada con detalles del error.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(","));
        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), message, request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
