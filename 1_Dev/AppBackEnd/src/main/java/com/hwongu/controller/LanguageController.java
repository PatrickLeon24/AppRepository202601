/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Este controlador permite cambiar la configuracion de idioma (locale) en la aplicacion.
 * Permite a los usuarios cambiar el idioma de acuerdo con el parametro proporcionado.
 */
package com.hwongu.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class LanguageController {

    // LocaleResolver utilizado para definir la configuracion de idioma de la aplicacion
    private final LocaleResolver localeResolver;
    // Solicitud HTTP para la configuracion de idioma
    private final HttpServletRequest request;
    // Respuesta HTTP para la configuracion de idioma
    private final HttpServletResponse response;

    /**
     * Cambia la configuracion de idioma de la aplicacion segun el parametro de idioma proporcionado.
     * Si se especifica "en" o "us" como parametro, se cambia a Ingles; de lo contrario, se establece
     * el idioma predeterminado.
     *
     * @param loc el codigo de idioma proporcionado en la URL.
     * @return una respuesta vacia con el estado HTTP 200 (OK) si el cambio fue exitoso.
     */
    @GetMapping("/locale/{loc}")
    public ResponseEntity<Void> changeLocale(@PathVariable("loc") String loc) {
        Locale userLocal = switch (loc){
            case "en", "us" -> Locale.ENGLISH;
            default -> Locale.ROOT;
        };
        localeResolver.setLocale(request, response, userLocal);
        return ResponseEntity.ok().build();
    }
}
