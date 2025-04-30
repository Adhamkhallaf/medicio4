package bbt;

import java.util.HashMap;
import java.util.Map;
//import org.testng.Assert;
//import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;



public class SeleniumExample {

    public static void main(String[] args) {
     
       
          WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--incognito"); // private mode, avoids saved profile settings
        options.addArguments("--disable-features=PasswordLeakDetection,AutofillServerCommunication");
        

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Open the SauceDemo website
        driver.get("https://www.saucedemo.com/");

        // Maximize the window
        driver.manage().window().maximize();
         driver.findElement(By.id("user-name")).sendKeys("standard_user");
         driver.findElement(By.id("password")).sendKeys("secret_sauce");
         driver.findElement(By.id("login-button")).click();
        // Pause to view the result (optional)
        try {
            Thread.sleep(3000); // 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currentUrl = driver.getCurrentUrl();
if (currentUrl.contains("inventory.html")) {
    System.out.println("Login successful. Landed on the inventory page.");
} else {
    System.out.println("Login failed or wrong page.");
}
// Wait briefly to ensure page is loaded
try {
    Thread.sleep(2000);
} catch (InterruptedException e) {
    e.printStackTrace();
}

// Check if the inventory_container is displayed
if (driver.findElement(By.id("inventory_container")).isDisplayed()) {
    System.out.println("Inventory container is displayed. Login successful.");
} else {
    System.out.println("Inventory container is NOT displayed.");
}
 driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
 driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
 driver.findElement(By.id("shopping_cart_container")).click();

 boolean item0InCart = driver.findElements(By.id("item_0_title_link")).size() > 0;
boolean item4InCart = driver.findElements(By.id("item_4_title_link")).size() > 0;

 if (item0InCart && item4InCart) {
     System.out.println("Both items are present in the cart.");
 } else {
     if (!item0InCart) {
         System.out.println("Item 0 is missing from the cart.");
     }
     if (!item4InCart) {
         System.out.println("Item 4 is missing from the cart.");
     }
 }
    
driver.findElement(By.id("checkout")).click();
driver.findElement(By.id("first-name")).sendKeys("adham");
driver.findElement(By.id("last-name")).sendKeys("khallaf");
driver.findElement(By.id("postal-code")).sendKeys("90210");
driver.findElement(By.id("continue")).click();
try {
    Thread.sleep(3000); // 3 seconds
} catch (InterruptedException e) {
    e.printStackTrace();
}

// For Item 0
WebElement item0Container = driver.findElement(By.id("item_0_title_link")).findElement(By.xpath("./ancestor::div[@class='cart_item']"));
String item0Name = item0Container.findElement(By.className("inventory_item_name")).getText();
String item0Price = item0Container.findElement(By.className("inventory_item_price")).getText();

System.out.println("Item 0 Name: " + item0Name);
System.out.println("Item 0 Price: " + item0Price);

// For Item 4
WebElement item4Container = driver.findElement(By.id("item_4_title_link")).findElement(By.xpath("./ancestor::div[@class='cart_item']"));
String item4Name = item4Container.findElement(By.className("inventory_item_name")).getText();
String item4Price = item4Container.findElement(By.className("inventory_item_price")).getText();

System.out.println("Item 4 Name: " + item4Name);
System.out.println("Item 4 Price: " + item4Price);
driver.findElement(By.id("finish")).click();
try {
    Thread.sleep(2000);
} catch (InterruptedException e) {
    e.printStackTrace();
}

// Locate the element and get its text
WebElement thankYouMessage = driver.findElement(By.className("complete-header"));
String messageText = thankYouMessage.getText();
System.out.println("Confirmation Message: " + messageText);
if (messageText.equals("Thank you for your order!")) {
    System.out.println("Order confirmation message verified.");
} else {
    System.out.println("Unexpected confirmation message: " + messageText);
}

        // Close the browser
       // driver.quit();
    }
}