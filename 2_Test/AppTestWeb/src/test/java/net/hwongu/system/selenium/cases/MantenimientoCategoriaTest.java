package net.hwongu.system.selenium.cases;

import net.hwongu.system.selenium.dataManager.Excel;
import net.hwongu.system.selenium.pages.BienvenidaPage;
import net.hwongu.system.selenium.pages.IniciarSesionPage;
import net.hwongu.system.selenium.pages.MantenimientoCategoriaPage;
import net.hwongu.system.selenium.util.ConfigManager;
import net.hwongu.system.selenium.util.Utilitario;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class MantenimientoCategoriaTest {

    private IniciarSesionPage iniciarSesionPage = null;
    private BienvenidaPage bienvenidaPage = null;
    private MantenimientoCategoriaPage mantenimientoCategoriaPage = null;
    private String rutaCarpeta;

    @BeforeTest
    @Parameters({ "navegador" })
    public void inicioClase(String navegador) throws Exception {
        this.iniciarSesionPage = new IniciarSesionPage(navegador);
        this.bienvenidaPage = new BienvenidaPage(this.iniciarSesionPage.getWebDriver());
        this.mantenimientoCategoriaPage = new MantenimientoCategoriaPage(this.iniciarSesionPage.getWebDriver());
        this.rutaCarpeta = ConfigManager.getProperty("util.ruta.capturaPantalla");
    }

    @DataProvider(name = "datosEntrada")
    public static Object[][] datosPoblados(ITestContext context) {
        Object[][] datos = null;
        String fuenteDatos = context.getCurrentXmlTest().getParameter("fuenteDatos");
        if (fuenteDatos.equalsIgnoreCase("excel")) {
            String rutaArchivo = context.getCurrentXmlTest().getParameter("rutaArchivo");
            datos = Excel.leerExcel(rutaArchivo);
        }
        return datos;
    }


    @Test(dataProvider = "datosEntrada")
    public void insertarCategoria(String casoPrueba, String urlInicial, String usuario, String clave, String nombre, String descripcion,
                                  String valorEsperado) throws Exception {
        try {
            this.iniciarSesionPage.ingresarPaginaIniciarSesion(urlInicial);
            this.iniciarSesionPage.iniciarSesion(usuario, clave);
            this.bienvenidaPage.hacerClicMenuPrincipal();
            this.bienvenidaPage.hacerClicMantenimientoCategoria();
            this.mantenimientoCategoriaPage.hacerClicBotonNuevo();
            String valorObtenido = this.mantenimientoCategoriaPage.agregarCategoria(nombre, descripcion, casoPrueba);
            System.out.println(valorObtenido);
            Assert.assertEquals(valorObtenido, valorEsperado);
            Utilitario.caputarPantallarError(rutaCarpeta, "Ejecución correcta", this.mantenimientoCategoriaPage.getWebDriver());
        } catch (AssertionError e) {
            Utilitario.caputarPantallarError(rutaCarpeta, e.getMessage(), this.mantenimientoCategoriaPage.getWebDriver());
            e.printStackTrace();
            Assert.fail(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());

        }
    }

    @AfterTest
    public void tearDown() throws Exception {
        mantenimientoCategoriaPage.cerrarPagina();
    }

}
