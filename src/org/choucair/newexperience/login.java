package org.choucair.newexperience;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class login {

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

        String  correo = "juniorromero@gmail.com",
                password = "";


        try {

            driver.findElement(By.linkText("Sign in")).click();

            if (correo != ""){
                WebElement txtemail = driver.findElement(By.id("email"));
                txtemail.click();
                txtemail.sendKeys(correo);

                if(password!= "") {
                    WebElement txtpassword = driver.findElement(By.id("passwd"));
                    txtpassword.click();
                    txtpassword.sendKeys(password);
                    driver.findElement(By.id("SubmitLogin")).click();
                    System.out.println("Prueba Exitosa, El usuario " + driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")).getText() + ", se logueo Correctamente..");


                }else {
                    System.out.println("Por favor, Ingresar Password..");}

            }else {
                System.out.println("Por favor, Ingresar Email..");}

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
