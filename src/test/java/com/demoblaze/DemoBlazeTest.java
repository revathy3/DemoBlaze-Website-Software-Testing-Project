package com.demoblaze;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class DemoBlazeTest{

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
    }

  
    // 1.Login with valid credentials
   
    @Test(priority = 1)
    public void validLogin() throws InterruptedException {

        driver.findElement(By.id("login2")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("loginusername")).sendKeys("admin@30");
        Thread.sleep(1000);

        driver.findElement(By.id("loginpassword")).sendKeys("User@123");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.id("nameofuser")).isDisplayed());
    }

    
    // 2. Login with invalid password
   
    @Test(priority = 2)
    public void invalidLogin() throws InterruptedException {

        driver.findElement(By.id("login2")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("loginusername")).sendKeys("admin@30");
        Thread.sleep(1000);

        driver.findElement(By.id("loginpassword")).sendKeys("User@1234");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Thread.sleep(3000);

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    
    // 3. Login with empty credentials
  
    @Test(priority = 3)
    public void emptyLogin() throws InterruptedException {

        driver.findElement(By.id("login2")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Thread.sleep(3000);

        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("Please fill out Username and Password."));
        alert.accept();
    }

    
    // 4.View product
   
    @Test(priority = 4)
    public void viewProduct() throws InterruptedException {

        driver.findElement(By.linkText("Phones")).click();
        Thread.sleep(1000);

        driver.findElement(By.linkText("Samsung galaxy s6")).click();
        Thread.sleep(3000);
    }

  
    // 5. Add to cart
   
    @Test(priority = 5)
    public void addToCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Phones"))).click();
 
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));
        
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Nexus 6"))).click();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart"))).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains("Product added"));
        alert.accept();
    }
   
    // 6. Verify product in cart
  
    @Test(priority = 6)
    public void verifyCartProduct() throws InterruptedException {

        driver.findElement(By.linkText("Phones")).click();
        Thread.sleep(1000);

        driver.findElement(By.linkText("Nexus 6")).click();
        Thread.sleep(2000);

        driver.findElement(By.linkText("Add to cart")).click();
        Thread.sleep(1000);

        driver.switchTo().alert().accept();

        driver.findElement(By.id("cartur")).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.getPageSource().contains("Nexus 6"));
    }
    
    // 7. Logout
    
    @Test(priority = 7)
    public void logoutTest() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

       
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login2"))).click();

     
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys("admin@30");
        driver.findElement(By.id("loginpassword")).sendKeys("User@123");

       
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));

        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout2"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login2")));

        Assert.assertTrue(driver.findElement(By.id("login2")).isDisplayed());
    }
    
    // 8. Order placement without login
    
    @Test(priority = 8)
    public void placeOrder() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Monitors']"))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='ASUS Full HD']"))).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();      
        Thread.sleep(1000);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "Product added");
        alert.accept();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur"))).click();      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Order']"))).click();      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='name']"))).sendKeys("Arya");      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='country']"))).sendKeys("India");      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='city']"))).sendKeys("Kochi");      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='card']"))).sendKeys("12345676578");      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='month']"))).sendKeys("04");      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='year']"))).sendKeys("2026");      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Purchase']"))).click();     
        Thread.sleep(2000);
    } 
    
    
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}