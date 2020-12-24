package org.choucair.newexperience.pruebasderegresion;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class Contactanos {

    static WebDriver driver;

    public static void main (String[] args){
        String chromePath = System.getProperty("user.dir")+ "\\drivers\\chromedriver.exe";
        String baseURL = "http://automationpractice.com/index.php";
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //VARIABLES INPUTS

        String  contact = "2", // "Valor 2="Customer Service", valor 1="WebMaster"
                email = "",
                order = "",
                description ="";

        try {

            WebElement btncontact = driver.findElement(By.id("contact-link"));
            btncontact.click();

            if(contact=="1"||contact=="2"){

                Select drpcontact = new Select(driver.findElement(By.id("id_contact")));
                Thread.sleep(2000);
                drpcontact.selectByValue(contact);

               if(email!=""){
                   WebElement txtemail = driver.findElement(By.id("email"));

                   txtemail.click();
                   txtemail.sendKeys(email);

                   if(order !=""){
                        WebElement txtorder = driver.findElement(By.id("id_order"));
                        txtorder.click();
                        txtorder.sendKeys(order);

                        if(description!=""){
                            WebElement txtdescription = driver.findElement(By.id("message"));
                            txtdescription.click();
                            txtdescription.sendKeys(description);

                            driver.findElement(By.id("submitMessage")).click();

                            System.out.println("Validación Correcta.. "+ driver.findElement(By.xpath("//*[@id=\"center_column\"]/p")).getText());

                        }else {
                            System.out.println("ERROR, campo incompleto ** Description **..");}
                   }else{
                       System.out.println("ERROR, campo incompleto ** Order **..");}
               }else{
                   System.out.println("ERROR, campo incompleto **Email**..");}
            }else{
                System.out.println("ERROR, campo ingresado INCORRECTO **Contact**");}

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
