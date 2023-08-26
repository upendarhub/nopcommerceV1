package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

public class BaseClass {

    public static WebDriver driver;
    public ResourceBundle rb;
    public Logger logger;

@BeforeClass(alwaysRun = true)
@Parameters("browser")
    public void setup(String br){

         String brName = System.getProperty("browser")!=null ? System.getProperty("browser") : br;

       rb = ResourceBundle.getBundle("config");
        logger = LogManager.getLogger(this.getClass());

        if(brName.contains("chrome")){
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if(brName.contains("headless")){
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));
        } else if (brName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }  else {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(rb.getString("appURL"));
    }
    @AfterClass(alwaysRun = true)
    public void tearDown(){
    driver.close();
    }
    public String getRandomString(Integer num){
    return RandomStringUtils.randomAlphabetic(num);
    }
    public String getRandomNumber(Integer num){
    return RandomStringUtils.randomNumeric(num);
    }
    public String getRandomAlphanumeric(Integer num){
    return RandomStringUtils.randomAlphanumeric(num);
    }
    public String getEmail(Integer num){
        String email = RandomStringUtils.randomAlphanumeric(num);
        return email+"@email.com";
    }

    public String takeScreenShot(String testName){
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
        Screenshot source = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(driver);
        String destination = System.getProperty("user.dir")+"/screenShots/"+testName+"_"+timeStamp+".png";
//        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//        File source1 = takesScreenshot.getScreenshotAs(OutputType.FILE);

        try {
            ImageIO.write(source.getImage(),"png", new File(destination));
//            FileUtils.copyFile(source1, new File(destination));

        } catch (Exception e){
            e.getMessage();
        }
        return destination;
    }
}
