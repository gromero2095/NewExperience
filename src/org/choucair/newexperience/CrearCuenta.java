package org.choucair.newexperience;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CrearCuenta {

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
                genero = "1",         /*Valores: 1=Masculino, 2= Femenino*/
                firstname = "Junior",
                lastname = "Romero",
                password = "juniorromero",
                fechadia = "22",
                fechames = "8",      /*Valor Numérico. del 1 al 12*/
                fechaaño = "1998",
                address = "jr tiahuanaco 1445",
                city = "Lima",
                state = "New York",
                codpostal = "10005",
                textoadicional = "Texto de prueba",
                telefonocasa = "4591811",
                telefonomobile = "971248535",
                direccionopcional = "villac umo 1445";


        try{

            driver.findElement(By.linkText("Sign in")).click();

            WebElement txtemail = driver.findElement(By.id("email_create"));
            txtemail.click();
            txtemail.sendKeys(correo);

            driver.findElement(By.id("SubmitCreate")).click();

            if(genero =="1" || genero =="2") {
                List<WebElement> checklist = driver.findElements(By.xpath("//input[@type='radio']"));

                for (int i = 0; i < checklist.size(); i++) {
                    if (checklist.get(i).getAttribute("value").equalsIgnoreCase(genero)) {
                        checklist.get(i).click();}
                }
                if(firstname !="") {
                    WebElement txtfirstname = driver.findElement(By.id("customer_firstname"));
                    txtfirstname.click();
                    txtfirstname.sendKeys(firstname);

                    if(lastname !="") {
                        WebElement txtlasttname = driver.findElement(By.id("customer_lastname"));
                        txtlasttname.click();
                        txtlasttname.sendKeys(lastname);

                        driver.findElement(By.id("email")).click();

                        if(password!=""){
                            WebElement txtpass = driver.findElement(By.id("passwd"));
                            txtpass.click();
                            txtpass.sendKeys(password);

                            Select drpdia = new Select(driver.findElement(By.id("days")));
                            Thread.sleep(2000);
                            drpdia.selectByValue(fechadia);

                            Select drpmes = new Select(driver.findElement(By.id("months")));
                            Thread.sleep(2000);
                            drpmes.selectByValue(fechames);

                            Select drpaño = new Select(driver.findElement(By.id("years")));
                            Thread.sleep(2000);
                            drpaño.selectByValue(fechaaño);

                            driver.findElement(By.id("firstname")).click();
                            driver.findElement(By.id("lastname")).click();

                            if(address!=""){
                                WebElement txtaddress = driver.findElement(By.id("address1"));
                                txtaddress.click();
                                txtaddress.sendKeys(address);

                                if(city!="") {
                                    WebElement txtcity = driver.findElement(By.id("city"));
                                    txtcity.click();
                                    txtcity.sendKeys(city);

                                    if(state!=""){
                                        Select drpstate = new Select(driver.findElement(By.id("id_state")));
                                        Thread.sleep(2000);
                                        drpstate.selectByVisibleText(state);

                                        if(codpostal!="") {
                                            WebElement txtcodepostal = driver.findElement(By.id("postcode"));
                                            txtcodepostal.click();
                                            txtcodepostal.sendKeys(codpostal);

                                            Select drpcountry = new Select(driver.findElement(By.id("id_country")));
                                            Thread.sleep(2000);
                                            drpcountry.selectByVisibleText("United States");

                                            WebElement txtinfo = driver.findElement(By.id("other"));
                                            txtinfo.click();
                                            txtinfo.sendKeys(textoadicional);

                                            WebElement txttlfcasa = driver.findElement(By.id("phone"));
                                            txttlfcasa.click();
                                            txttlfcasa.sendKeys(telefonocasa);

                                            if(telefonomobile!=""){
                                                WebElement txttlfmob = driver.findElement(By.id("phone_mobile"));
                                                txttlfmob.click();
                                                txttlfmob.sendKeys(telefonomobile);

                                                if(direccionopcional!=""){
                                                    WebElement txtaddressopcional = driver.findElement(By.id("alias"));
                                                    txtaddressopcional.click();
                                                    txtaddressopcional.sendKeys(direccionopcional);

                                                    driver.findElement(By.id("submitAccount")).click();

                                                    System.out.println("Prueba Exitosa, El usuario " + driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")).getText() + ", se creo Correctamente..");

                                                }else{
                                                    System.out.println("Completar el campo **Addres reference** ");}
                                            }else{
                                                System.out.println("Completar el campo **Mobile Phone** "); }
                                        }else{
                                            System.out.println("Completar el campo **Postal Code** "); }
                                    }else{
                                        System.out.println("Completar el campo **State** ");}
                                }else{
                                    System.out.println("Completar el campo **City** ");}
                            }else{
                                System.out.println("Completar el campo **Address** ");}
                        }else{
                            System.out.println("Completar el campo **PASSWORD** ");}
                    }else{
                        System.out.println("Completar el campo **Last Name** "); }
                }else{
                        System.out.println("Completar el campo **First Name** ");}
            }else {
                System.out.println("El valor ingresado en genero es Incorrecto, Validar..");}

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
