/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase configura el manejo de mensajes y la localizacion (locale) en la aplicacion.
 * Define beans para el manejo de mensajes de validacion y establece un locale predeterminado.
 */
package com.hwongu.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


import java.util.Locale;

@Configuration
public class MessageConfig {

    /**
     * Configura el origen de mensajes de la aplicacion para cargar mensajes desde un archivo
     * de recursos externo (messages.properties). Este bean permite la internacionalizacion
     * de mensajes, cargando mensajes de acuerdo al idioma especificado.
     *
     * @return un MessageSource configurado para la carga de mensajes de la aplicacion.
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        return messageSource;
    }

    /**
     * Configura el validador de mensajes para utilizar el MessageSource de la aplicacion.
     * Permite que los mensajes de validacion esten localizados segun el idioma configurado.
     *
     * @return un LocalValidatorFactoryBean que utiliza el MessageSource para los mensajes de validacion.
     */
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    /**
     * Establece un locale predeterminado para la aplicacion. Utiliza SessionLocaleResolver
     * para mantener el locale en la sesion del usuario. El locale por defecto es ROOT, que
     * representa el idioma base de la aplicacion.
     *
     * @return un LocaleResolver configurado con el locale predeterminado.
     */
    @Bean
    @Scope("prototype")
    public LocaleResolver localeResolver(){
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ROOT);
        return localeResolver;
    }
}
