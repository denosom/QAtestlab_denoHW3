import Logger.WebDriverLogger;
import PageNavigation.PageElement;
import TestConfig.ConfigurationVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import BrowserManager.SelectBrowser;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class ScriptC {

    public static void main(String[] args) {

       // WebDriver driver = SelectBrowser.getDriver(ConfigurationVariables.BROWSER);

        //Adding logger wrap to driver and start logging
        EventFiringWebDriver driver = new EventFiringWebDriver(SelectBrowser.getDriver(ConfigurationVariables.BROWSER));
        WebDriverLogger logger = new WebDriverLogger();
        driver.register(logger);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
        driver.get(ConfigurationVariables.MAIN_URL);
        driver.manage().window().maximize();

        PageElement.Authorization(driver);

        //Wait Admin Panel download
        WebDriverWait wait = new WebDriverWait(driver, 10);
       // PageElement.Delay(500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='employee_avatar_small']")));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='ajax_running' and @style='display: none;']")));

        //Choose Cataloge->Categories
        WebElement menuCatalog = driver.findElement(By.id("subtab-AdminCatalog"));
        Actions actions = new Actions(driver);
        actions.moveToElement(menuCatalog).build().perform();
        //Find and click on submenu Categories
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("subtab-AdminCategories")));
        //WebElement submenuCategories = driver.findElement(By.id("subtab-AdminCategories"));
       //submenuCategories.click();
        menuCatalog.findElements(By.cssSelector("li")).get(1).click();

        //Wait Category Page download by Add Category button is shown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("page-header-desc-category-new_category")));

        //Add New Category find and click
        WebElement btnAddCategory = driver.findElement(By.id("page-header-desc-category-new_category"));
        btnAddCategory.click();

        //Wait Add category Page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name_1")));

        //Input new category and press Save
        WebElement inputNewCategoryName = driver.findElement(By.id("name_1"));
        inputNewCategoryName.sendKeys(ConfigurationVariables.NEW_CATEGORY_NAME);
        WebElement btnSaveCategory = driver.findElement(By.id("category_form_submit_btn"));
        btnSaveCategory.click();
        // inputNewCategoryName.submit();


        //Wait success message
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='alert alert-success']")));
        System.out.println("New category has been successfully created");

        //Wait input FilterName field
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("categoryFilter_name")));

        //Input Category to filter
        WebElement inputFilterName = driver.findElement(By.name("categoryFilter_name"));
        inputFilterName.sendKeys(ConfigurationVariables.NEW_CATEGORY_NAME);
        inputFilterName.submit();

        //Wait filter results
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("pointer")));

        //check first row of result
        WebElement tableCategory = driver.findElement(By.id("table-category"));
        String foundCategoryName = tableCategory.findElements(By.cssSelector("td")).get(2).getText();
        if (foundCategoryName.equals(ConfigurationVariables.NEW_CATEGORY_NAME)){
            System.out.println("It's OK, we found our category");
        }
        else{
            System.out.println("Something is wrong...");
        }

       System.out.println("Expected category name: " + ConfigurationVariables.NEW_CATEGORY_NAME + " Actual category name: "  + foundCategoryName + ".");

        //Wait before quit
        PageElement.Delay(3000);
        //Close browser
        driver.quit();

    }


}
