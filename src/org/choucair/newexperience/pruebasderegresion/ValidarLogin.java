package org.choucair.newexperience.pruebasderegresion;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ValidarLogin {

    static WebDriver driver;

    public static void main (String[] args){
        String chromePath = System.getProperty("user.dir")+ "\\drivers\\chromedriver.exe";
        String baseURL = "http://automationpractice.com/index.php";
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //INPUTS
        //Datos de la Cuenta

        String  correo = "",
                password = "";

        try{

            driver.findElement(By.linkText("Sign in")).click();

            WebElement txtemail = driver.findElement(By.id("email"));
            txtemail.click();
            txtemail.sendKeys(correo);

            WebElement txtpassword = driver.findElement(By.id("passwd"));
            txtpassword.click();
            txtpassword.sendKeys(password);

            driver.findElement(By.id("SubmitLogin")).click();

            System.out.println("Validación correcta: "+ driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol")).getText());

        }
        catch(NoSuchElementException ne){
            System.err.println("No se encontro el WebElement: "+ ne.getMessage());
        }catch (WebDriverException we){
            System.err.println("WebDriver Falló: "+ we.getMessage());
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }finally {
            driver.quit();
        }

    }


}
