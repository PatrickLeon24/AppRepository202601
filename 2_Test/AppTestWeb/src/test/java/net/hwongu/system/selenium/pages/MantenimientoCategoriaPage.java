package net.hwongu.system.selenium.pages;

import net.hwongu.system.selenium.driver.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MantenimientoCategoriaPage {

    private By txtNameCategory = By.id("txtNameCategory");
    private By txtDescriptionCategory = By.id("txtDescriptionCategory");
    private By btnGuardar = By.id("btnGuardar");
    private By txtMensaje = By.xpath("/html/body/div[3]/div/div/mat-snack-bar-container/div/div/div/div/simple-snack-bar/div[1]");
    private By txtMensajeError = By.xpath("/html/body/div[2]/div/div/mat-snack-bar-container/div/div/div/div/simple-snack-bar/div[1]");
    private By btnNuevo = By.name("btnNuevo");
    private WebDriver webDriver = null;

    public MantenimientoCategoriaPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void hacerClicBotonNuevo() throws Exception{
        webDriver.findElement(btnNuevo).click();
        Thread.sleep(2000);
    }

    public String agregarCategoria(String nombre, String descripcion, String tipo) throws Exception{
        webDriver.findElement(txtNameCategory).sendKeys(nombre);
        webDriver.findElement(txtDescriptionCategory).sendKeys(descripcion);
        Thread.sleep(2000);
        webDriver.findElement(btnGuardar).click();
        String repuesta = "";
        if(tipo.equalsIgnoreCase("Basico")) {
            repuesta = webDriver.findElement(txtMensaje).getText();
        }else {
            repuesta = webDriver.findElement(txtMensajeError).getText();
        }
        Thread.sleep(5000);
        return repuesta;
    }

    public void cerrarPagina(){
        SeleniumDriver.cerrarPagina(webDriver);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

}
