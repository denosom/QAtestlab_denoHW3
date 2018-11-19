package PageNavigation;

import TestConfig.ConfigurationVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageElement {

    public static void Delay(long ms){
        try{
            Thread.sleep(ms);
        }
        catch(InterruptedException e){
            System.out.println("thread interrupted");
        }
    }

    public static void Authorization(WebDriver currdriver)
    {
        // authorization
        WebElement emailInput = currdriver.findElement(By.id("email"));
        emailInput.sendKeys(ConfigurationVariables.USERNAME);

        WebElement passInput = currdriver.findElement(By.id("passwd"));
        passInput.sendKeys(ConfigurationVariables.USERPASS);

        WebElement inButton = currdriver.findElement(By.name("submitLogin"));
        inButton.click();
        // authorization complete
    }
}
