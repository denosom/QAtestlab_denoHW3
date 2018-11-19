package Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebDriverLogger extends AbstractWebDriverEventListener {

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        System.out.println("Before navigating to: '" + url + "'");
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println(timeStamp()+" Navigated to:'" + url + "'");
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        System.out.println(timeStamp()+" Trying to click on: " + element.toString());
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        System.out.println(timeStamp()+" Clicked on: " + element.toString());
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println(timeStamp()+" Search for element: " + by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println(timeStamp()+" Found element: " + by.toString());
    }

    @Override
    public void onException(Throwable error, WebDriver driver) {
        System.out.println(timeStamp()+" Error occurred: " + error);
    }

    public String timeStamp (){
            SimpleDateFormat sdt = new SimpleDateFormat("HH:mm:ss:SSS");
            return sdt.format(new Date());
        }

}
