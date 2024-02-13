package it.edu.iisgubbio.spaggiari;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ProvaLink {

    static FirefoxDriver driver;
    static{
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
    }

    public static void main(String[] args) throws InterruptedException {
        driver.get("https://www.iisgubbio.edu.it/comunicati");

        List<WebElement> elementi = driver.findElements( By.cssSelector("div.media"));
        System.out.println(elementi.size());
        for(WebElement elem: elementi) {
            WebElement titolo = elem.findElement( By.cssSelector("p") );
            System.out.println( titolo.getText() );
        }
        String nome = elementi.get(0).findElement( By.cssSelector("p") ).getText();
        WebElement link = elementi.get(0).findElement( By.cssSelector("div.box-allegato-new a:first-child") );
        link.click();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); // id of the tab
        Thread.sleep(2000);
        String url = driver.getCurrentUrl();
        System.out.println(nome);
        System.out.println("=>");
        System.out.println(url);
    }

}
