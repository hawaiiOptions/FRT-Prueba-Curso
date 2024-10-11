package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    protected static WebDriver driver;
    protected WebDriverWait wait;

    static {
        WebDriverManager.chromedriver().setup();

        // Configuramos ChromeOptions para deshabilitar notificaciones y evitar la detección
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications"); // Desactiva las notificaciones
        options.addArguments("--disable-blink-features=AutomationControlled"); // Ocultar automatización
        options.setExperimentalOption("excludeSwitches", List.of("enable-automation")); // Evitar banderas de Selenium

        // Inicializar WebDriver con las opciones configuradas
        driver = new ChromeDriver(options);

        // Ocultar el WebDriver a través de JavaScript
        ((JavascriptExecutor) driver).executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
    }

    // Constructor para inicializar la instancia de WebDriver 
    // que se compartirá entre los diferentes objetos de página
    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Espera explícita de 5 segundos
    }

    public void setFullScreen() {
        driver.manage().window().fullscreen();
    }

    // Método para navegar a una URL específica
    public static void navigateTo(String url) {
        driver.get(url); // Navega a la URL
        driver.manage().window().fullscreen(); // Pantalla completa al navegar
    }
    

    public static void closeBrowser() {
        driver.quit();
    }

    private WebElement Find(String locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public void clickElement(String locator) {
        Find(locator).click();
    }

    public void write(String locator, String keysToSend) {
        Find(locator).clear(); // Limpia el campo de texto antes
        Find(locator).sendKeys(keysToSend);
    }

    public void selectFromDropdownByValue(String locator, String value) {
        Select dropdown = new Select(Find(locator));
        dropdown.selectByValue(value);
    }

    public void selectFromDropdownByIndex(String locator, Integer value) {
        Select dropdown = new Select(Find(locator));
        dropdown.selectByIndex(value);
    }

    public int dropdownSize(String locator) {
        Select dropdown = new Select(Find(locator));
        List<WebElement> dropdownOptions = dropdown.getOptions();
        return dropdownOptions.size();
    }

    public List<String> getDropdownValues(String locator) {
        Select dropdown = new Select(Find(locator));
        List<WebElement> dropdownOptions = dropdown.getOptions();
        List<String> values = new ArrayList<>();
        for (WebElement option : dropdownOptions) {
            values.add(option.getText());
        }
        return values;
    }
}
