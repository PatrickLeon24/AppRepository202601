/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase configura un filtro de CORS (Cross-Origin Resource Sharing) para la aplicacion.
 * Permite que solicitudes desde diferentes dominios puedan acceder a los recursos de la aplicacion
 * configurando los encabezados CORS necesarios. Este filtro se ejecuta con la mas alta precedencia.
 */
package com.hwongu.util;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * La clase CORS implementa la interfaz Filter para definir un filtro de CORS que permite a la
 * aplicacion responder a solicitudes HTTP de origen cruzado. Configura los encabezados necesarios
 * para permitir el acceso desde diferentes dominios.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORS implements Filter {

    /**
     * Metodo init. No se utiliza en esta implementacion, pero es requerido por la interfaz Filter.
     *
     * @param filterConfig configuracion del filtro, no utilizada en este caso.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "DELETE, GET, OPTIONS, PATCH, POST, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    /**
     * Metodo destroy. No se utiliza en esta implementacion, pero es requerido por la interfaz Filter.
     */
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }


}