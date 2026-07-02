package net.hwongu.system.selenium.pages;

import net.hwongu.system.selenium.driver.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BienvenidaPage {

    private By linkMenuPrincipal = By.xpath("/html/body/app-root/app-layout/div/mat-toolbar/button[1]");
    private By linkMenuCategoria = By.xpath("/html/body/app-root/app-layout/div/mat-sidenav-container/mat-sidenav/div/button[2]");
    private WebDriver webDriver = null;

    public BienvenidaPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public void hacerClicMenuPrincipal() throws Exception{
        webDriver.findElement(linkMenuPrincipal).click();
        Thread.sleep(2000);
    }

    public void hacerClicMantenimientoCategoria() throws Exception{
        webDriver.findElement(linkMenuCategoria).click();
        Thread.sleep(2000);
    }



    public void cerrarPagina(){
        SeleniumDriver.cerrarPagina(webDriver);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

}
