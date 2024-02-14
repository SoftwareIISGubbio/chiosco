package it.edu.iisgubbio.spaggiari;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class DownloadCircolari {

    static FirefoxDriver driver;
    static{
        // Configura le opzioni di Firefox per gestire i download dei PDF
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();

        // https://stackoverflow.com/questions/36309314/set-firefox-profile-to-download-files-automatically-using-selenium-and-java
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", System.getProperty("user.home")+"/archivio/");
        profile.setPreference("browser.download.useDownloadDir", true);
        profile.setPreference("browser.download.viewableInternally.enabledTypes", "");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf;text/plain;application/text;text/xml;application/xml");
        profile.setPreference("pdfjs.disabled", true);  // disable the built-in PDF viewer

        options.setProfile(profile);

        // Inizializza il driver di Firefox con le opzioni configurate
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
    }

    public static void main(String[] args) throws InterruptedException {
        driver.get("https://www.iisgubbio.edu.it/comunicati");
        Thread.sleep(2000);
        List<WebElement> cookie = driver.findElements( By.cssSelector("div.cookiebar"));
        WebElement linkC = cookie.get(0).findElement(By.cssSelector("button.cookiebar-ko"));
        linkC.click();

        List<WebElement> elementi = driver.findElements( By.cssSelector("div.media"));
        System.out.println(elementi.size());
        for(WebElement elem: elementi) {
            WebElement titolo = elem.findElement( By.cssSelector("p") );
            System.out.println( titolo.getText() );
            WebElement link = elem.findElement(By.cssSelector("div.media-right a.link-to-file"));
            Circolare c = new Circolare(titolo.getText());
            // FIXME non riesce ad inteficare il numero della circolare
            System.out.println(c.getNumero());
            if(!ArchivioCircolari.esiste(c.getNumero())){
            	link.click();
            }
        }

        // il pezzo sotto per cambiare tab ma in questo contesto non serve
        // ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        // driver.switchTo().window(tabs.get(1)); // id of the tab

        Thread.sleep(5000);
        driver.quit();
    }

}
