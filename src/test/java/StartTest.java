import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.List;

public class StartTest
{

    private WebDriver driver;
    private WebDriverWait wait;
    public String target;
    public String secondTarget;
    public HashMap<String, String> validTheme= new HashMap();

    public void one ()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Program files\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "http://www.google.com";
        driver.manage().window().maximize();
        driver.get(baseURL);
        wait = new WebDriverWait(driver, 10);

        driver.get("http://stackoverflow.com");
        driver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS );
    }

    public void send( String xPath, String Name ) throws InterruptedException
    {
        driver.findElement(By.xpath(xPath)).sendKeys(Name);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(5000);
    }


    public void click( String xPath )
    {
        driver.findElement(By.xpath(xPath)).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void clickById ( String id )
    {
        driver.findElement(By.id( id )).click();
    }

    public void clickByLink ( String link )
    {
        driver.findElement(By.linkText( link )).click();
    }


    public void search ( String xPathOne, String xPathTwo, String contain )
    {
        List<WebElement> spisok= driver.findElements(By.xpath( xPathOne ));
        for ( int i= 0; i < spisok.size(); i++ )
        {
            WebElement first= spisok.get(i);
            WebElement title= first.findElement(By.xpath( xPathTwo ));
            target= title.getText();
            System.out.println( target );

           boolean isAvalible= target.contains( contain );
           System.out.println( isAvalible );
        }
    }

    public void doubleSearch ( String xPathOne, String xPathTwo, String contain )
    {
        List<WebElement> spisok= driver.findElements(By.xpath( xPathOne ));
        for ( int i= 0; i < spisok.size(); i++ )
        {
            List<WebElement> secondSpisok= driver.findElements(By.xpath( xPathTwo ));
            for ( WebElement title : secondSpisok )
            {
                target= title.getText();
                System.out.println( target );

                boolean isAvalible= target.contains( contain );
                System.out.println( isAvalible );
            }
        }
    }

    public void check ()
    {
        for ( Map.Entry<String, String> entry : validTheme.entrySet() )
        {
            target= entry.getKey();
            String link= entry.getValue();
            System.out.println(target);

            driver.get(link);

            secondTarget= driver.findElement(By.xpath("//h1[contains(@class, 'fs-headline')]/a[contains(@class, 'question-hyperlink')]")).getText();
            boolean isAvalible= secondTarget.contains( target );
            System.out.println(secondTarget);
            System.out.println( isAvalible );
        }
    }

    public void close ()
    {
        driver.close();
        driver= null;
    }
}
