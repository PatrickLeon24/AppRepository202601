package net.hwongu.system.selenium.driver;


import java.util.concurrent.TimeUnit;

import net.hwongu.system.selenium.util.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;


public final class SeleniumDriver {


    private static final int IMPLICIT_WAIT_TIME = 30;

    public static WebDriver inicializarDriver(String navegador) {
        WebDriver webDriver = null;

        try {
            webDriver = switch (navegador.toLowerCase()) {
                case "firefox" -> {
                    System.setProperty(ConfigManager.getProperty("webdriver.firefox.system"), ConfigManager.getProperty("webdriver.firefox.path"));
                    FirefoxOptions options = new FirefoxOptions();
                    options.setBinary(ConfigManager.getProperty("webdriver.firefox.installer"));
                    yield new FirefoxDriver(options);
                }
                case "chrome" -> {
                    System.setProperty(ConfigManager.getProperty("webdriver.chrome.system"), ConfigManager.getProperty("webdriver.chrome.path"));
                    yield new ChromeDriver();
                }
                case "edge" -> {
                    System.setProperty(ConfigManager.getProperty("webdriver.edge.system"), ConfigManager.getProperty("webdriver.edge.path"));
                    yield new EdgeDriver();
                }
                default -> throw new IllegalArgumentException("Navegador no soportado: " + navegador);
            };
            if (webDriver != null) {
                webDriver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            if (webDriver != null) {
                cerrarPagina(webDriver);
            }
            e.printStackTrace();
        }

        return webDriver;
    }

    public static void cerrarPagina(WebDriver webDriver) {
        if (webDriver != null) {
            try {
                webDriver.quit();
            } catch (Exception e) {
            }
        }
    }


}
