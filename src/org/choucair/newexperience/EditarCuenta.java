package org.choucair.newexperience;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class EditarCuenta {

    static WebDriver driver;

    public static void main (String[] args){
        String chromePath = System.getProperty("user.dir")+ "\\drivers\\chromedriver.exe";
        String baseURL = "http://automationpractice.com/index.php";
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //INPUTS DATOS DE LA CUENTA - LOGIN

        String  correo = "juniorromero@gmail.com",
                password = "juniorromero";


        //INPUTS DATOS A EDITAR

        String  genero = "3",             /*Valores: 1=Masculino, 2= Femenino*/
                firstname = "Junior",
                lastname = "Romero",
                email = "",
                fechadia = "" ,
                fechames = "8",           /*Valor Numérico, del 1 al 12*/
                fechaaño = "",
                passold = "gersonromero", /* DATO OBLIGATORIO */
                passnew = "";

        try{
            //Login ----------------
            driver.findElement(By.linkText("Sign in")).click();

            WebElement txtemail = driver.findElement(By.id("email"));
            txtemail.click();
            txtemail.sendKeys(correo);

            WebElement txtpassword = driver.findElement(By.id("passwd"));
            txtpassword.click();
            txtpassword.sendKeys(password);

            driver.findElement(By.id("SubmitLogin")).click();
            driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[4]/a")).click();


            //Datos a Editar ----------------

            if(genero =="1" || genero =="2"){
                List<WebElement> checklist = driver.findElements(By.xpath("//input[@type='radio']"));

                for (int i = 0; i < checklist.size(); i++) {
                    if (checklist.get(i).getAttribute("value").equalsIgnoreCase(genero)) {
                        checklist.get(i).click();
                    }
                }
            }else{
                System.out.println("El valor ingresado de genero no es el correcto");

            }

            if(firstname != "") {
                WebElement txtfirstnameedit = driver.findElement(By.id("firstname"));
                txtfirstnameedit.clear();
                txtfirstnameedit.sendKeys(firstname);}


            if(lastname != "") {
                WebElement txtlastnameedit = driver.findElement(By.id("lastname"));
                txtlastnameedit.clear();
                txtlastnameedit.sendKeys(lastname);}


            if(email != "") {
                WebElement txtemailedit = driver.findElement(By.id("email"));
                txtemailedit.clear();
                txtemailedit.sendKeys(email);}


            if(fechadia !="") {
                Select drpdia = new Select(driver.findElement(By.id("days")));
                Thread.sleep(2000);
                drpdia.selectByValue(fechadia);}


            if(fechames != "") {
                Select drpmes = new Select(driver.findElement(By.id("months")));
                Thread.sleep(2000);
                drpmes.selectByValue(fechames);}


            if(fechaaño != ""){
            Select drpaño = new Select(driver.findElement(By.id("years")));
            Thread.sleep(2000);
            drpaño.selectByValue(fechaaño);}

            if(passold != ""){
            WebElement txtpassold = driver.findElement(By.id("old_passwd"));
            txtpassold.click();
            txtpassold.clear();
            txtpassold.sendKeys(passold);}

            if(passnew != ""){
            WebElement txtnewpass = driver.findElement(By.id("passwd"));
            txtnewpass.click();
            txtnewpass.sendKeys(passnew);

            WebElement txtnewpassconf = driver.findElement(By.id("confirmation"));
            txtnewpassconf.click();
            txtnewpassconf.sendKeys(passnew);}

            driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/form/fieldset/div[11]/button")).click();

            System.out.println("Prueba Exitosa, Se modificaron los datos correctamente, " + driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/p")).getText() + "" );


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
