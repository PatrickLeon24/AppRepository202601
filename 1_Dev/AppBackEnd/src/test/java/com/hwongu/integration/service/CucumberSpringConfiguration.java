/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase configura el contexto de Spring para las pruebas de integracion con Cucumber,
 * permitiendo que los escenarios de prueba puedan aprovechar el contexto de la aplicacion
 * Spring Boot y sus beans.
 */
package com.hwongu.integration.service;

import com.hwongu.AppBackEndApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;


@CucumberContextConfiguration
@SpringBootTest(classes = AppBackEndApplication.class) // Clase principal de Spring Boot
public class CucumberSpringConfiguration {
    // Clase de configuracion que permite la integracion de Cucumber con el contexto de Spring.
    // Al utilizar @SpringBootTest, se carga el contexto completo de la aplicacion definido en
    // AppBackEndApplication para que las pruebas de Cucumber puedan acceder a los beans y configuraciones de Spring.
}