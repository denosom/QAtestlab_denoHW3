package BrowserManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class SelectBrowser {

    public static WebDriver getDriver(String browser){
        switch (browser) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//resources//geckodriver.exe");
                return new FirefoxDriver();

                //doesn't work correct with IE yet
                case "ie":
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "//resources//IEDriverServer.exe");
                return new InternetExplorerDriver();
            case "chrome":
            default:
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//resources//chromedriver.exe");
                return new ChromeDriver();
        }

}
}

