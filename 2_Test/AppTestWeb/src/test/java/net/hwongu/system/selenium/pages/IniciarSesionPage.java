package net.hwongu.system.selenium.pages;

import net.hwongu.system.selenium.driver.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IniciarSesionPage {

    private By cajaUsuario = By.name("txtUsuario");
    private By cajaClave = By.name("txtClave");
    private By botonIniciarSesion = By.name("btnIniciarSesion");
    private WebDriver webDriver = null;

    public IniciarSesionPage(String navegador){
        this.webDriver = SeleniumDriver.inicializarDriver(navegador);
    }

    public void ingresarPaginaIniciarSesion(String urlInicial) throws Exception{
        this.webDriver.get(urlInicial);
        Thread.sleep(2000);
    }

    public void iniciarSesion(String usuario, String clave) throws Exception{
        webDriver.findElement(cajaUsuario).clear();
        webDriver.findElement(cajaUsuario).sendKeys(usuario);
        webDriver.findElement(cajaClave).clear();
        webDriver.findElement(cajaClave).sendKeys(clave);
        webDriver.findElement(botonIniciarSesion).click();
        Thread.sleep(2000);
    }

    public void cerrarPagina(){
        SeleniumDriver.cerrarPagina(webDriver);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
