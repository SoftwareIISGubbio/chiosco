package it.edu.iisgubbio.spaggiari;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
/****************************************************************************
 * Permette di scaricare le circolari presenti sul sito, archivandole tramite
 * "ArchivioCircolari" salvandole su una cartella predefinita scelta
 * dall'utente
 *
 * @author Filippo Nardoni
 ***************************************************************************/
public class DownloadCircolari {

    /************************************************************************
     * Main che permette di individuare la cartella dove sono salvati i file,
     * accede al sito in cui sono presenti le circolari, le scarica e crea un
     * oggetto Circolare che evrrà salvato nell'oggetto ArchivioCircolari
     *
     * @param args
     ***********************************************************************/
    public static void main(String[] args) throws InterruptedException {
        //TODO: si potrebbe utilizzare args per individuare il percoso della cartella
        String cartellaDownload = "";
        if(args.length>0) {
            cartellaDownload = args[0];
        } else {
            cartellaDownload = System.getProperty("user.home")+"/archivio/";
        }
        if(args.length>1) {
            // controlla headless
        }

        ArchivioCircolari archivo =new ArchivioCircolari(cartellaDownload);
        // configuro il driver
        // Configura le opzioni di Firefox per gestire i download dei PDF
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();

        // https://stackoverflow.com/questions/36309314/set-firefox-profile-to-download-files-automatically-using-selenium-and-java
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", cartellaDownload);
        profile.setPreference("browser.download.useDownloadDir", true);
        profile.setPreference("browser.download.viewableInternally.enabledTypes", "");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf;text/plain;application/text;text/xml;application/xml");
        profile.setPreference("pdfjs.disabled", true);  // disable the built-in PDF viewer

        options.setProfile(profile);
        // Impostato headless per far si che il programma funzioni aprendo il
        //browser in background
        options.addArguments("-headless");

        // Inizializza il driver di Firefox con le opzioni configurate
        FirefoxDriver driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        // eseguo il lavoro
        driver.get("https://www.iisgubbio.edu.it/comunicati");
        // Fermo il processo per far si che spunti la casella dei cookie
        Thread.sleep(2000);
        // Individuo la casella per rifiutare i cookie e la clicco
        List<WebElement> cookie = driver.findElements( By.cssSelector("div.cookiebar"));
        WebElement linkC = cookie.get(0).findElement(By.cssSelector("button.cookiebar-ko"));
        linkC.click();

        // Individuo tutti i pulsanti a cui sono collegati dei file da scaricare
        List<WebElement> elementi = driver.findElements( By.cssSelector("div.media"));
        System.out.println(elementi.size());
        for(WebElement elem: elementi) {
            // Prendo il nome della circolare
            String titolo = elem.findElement( By.cssSelector("p") ).getText();
            System.out.println( titolo);
            // Scarto eventuali allegati che non devono essere scaricati scaricare
            WebElement link = elem.findElement(By.cssSelector("div.media-right a.link-to-file"));
            // Controllo che la circolare rispetti i requisiti per esserlo e che non sia già stata scaricata
            if(!archivo.esiste(Circolare.getNumeroDaNome(titolo)) && Circolare.isCircolare(titolo)){
            	link.click();
            }
        }
        // Attendo per far si che eventuali download in corso vengano completati
        Thread.sleep(2000);
        driver.quit();
    }

}
