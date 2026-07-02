/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase configura el bean para ModelMapper, una herramienta que permite mapear
 * entre objetos de diferentes clases. Es util para convertir entre entidades y DTOs
 * en la aplicacion.
 */
package com.hwongu.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    /**
     * Configura y registra un bean de ModelMapper en el contexto de Spring. Este bean
     * esta etiquetado como "modelMapper" y se utiliza para mapear automaticamente entre
     * entidades y objetos DTO en toda la aplicacion.
     *
     * @return una instancia de ModelMapper configurada.
     */
    @Bean("modelMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
