import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;

import java.nio.file.Paths;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Luhn {
    static WebDriver driver;

    @BeforeTest
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
        WebDriverManager.operadriver().setup();
        WebDriverManager.chromiumdriver().setup();
        WebDriverManager.iedriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        MethodHelpers.writeCSVLine("C:\\Users\\Lungile.Motsweni\\Documents\\Personal\\Listed.txt", "",false);
        MethodHelpers.writeCSVLine("C:\\Users\\Lungile.Motsweni\\Documents\\Personal\\reject.txt", "",false);
    }


    @Test
    static void SellEqui () throws InterruptedException, IOException {
        Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\Lungile.Motsweni\\Documents\\Personal\\buy.txt"));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

        driver.get("https://platform.easyequities.io/Account/SignIn");
        driver.findElement(By.id("user-identifier-input")).sendKeys("lungilepm");
        driver.findElement(By.id("Password")).sendKeys("5744Lungile#1");
        driver.findElement(By.id("login-mobile")).click();
        int secs = 2*60;
        for (CSVRecord record : csvParser) {

            driver.get("https://platform.easyequities.io/ValueAllocation/Sell?contractCode=EQU.ZA." + record.get(0));

try{
            By laba = By.xpath("//label[@class='order-type-selector__label']");
            new WebDriverWait(driver, Duration.ofSeconds(secs)).until(ExpectedConditions.elementToBeClickable(laba));
            List<WebElement> label = driver.findElements(laba);
            if (label.size() > 0) {

                try {
                    label.get(1).click();
                    //Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;

                }

            }

            By val = By.id("js-value-amount");
            new WebDriverWait(driver, Duration.ofSeconds(secs)).until(ExpectedConditions.elementToBeClickable(val));
            List<WebElement> value = driver.findElements(val);
            if (value.size() > 0) {

                try {
                    By sel = By.id("js-percentage-to-sell");
                    new WebDriverWait(driver, Duration.ofSeconds(secs)).until(ExpectedConditions.elementToBeClickable(sel));
                    List<WebElement> sell = driver.findElements(sel);
                    sell.get(0).clear();
                    value.get(0).clear();
                    value.get(0).sendKeys("5");


                } catch (Exception e) {
                    e.printStackTrace();
                    continue;

                }

                By selec = By.xpath("//button[text()='MAX']");
                new WebDriverWait(driver, Duration.ofSeconds(secs)).until(ExpectedConditions.elementToBeClickable(selec));
                List<WebElement> select = driver.findElements(selec);
                if (select.size() > 0) {

                    try {
                        select.get(0).click();
                        //Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;

                    }
                    try {

                    By las = By.xpath("//*[text()='Place Sell Order']");
                new WebDriverWait(driver, Duration.ofSeconds(secs)).until(ExpectedConditions.elementToBeClickable(las));
                List<WebElement> found = driver.findElements(las);
                if (found.size() > 0) {

                    try {
                        found.get(0).click();
                        //Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;

                    }
                }
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            laba = By.xpath("//label[@class='order-type-selector__label']");
                            new WebDriverWait(driver, Duration.ofSeconds(secs)).until(ExpectedConditions.elementToBeClickable(laba));
                            label = driver.findElements(laba);
                            if (label.size() > 0) {


                                label.get(1).click();
                                //Thread.sleep(10000);


                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            continue;
                        }
                    }
                    By las = By.xpath("//*[text()='Place Sell Order']");
                    new WebDriverWait(driver, Duration.ofSeconds(secs)).until(ExpectedConditions.elementToBeClickable(las));
                    List<WebElement> found = driver.findElements(las);
                    if (found.size() > 0) {

                        try {
                            found.get(0).click();
                            //Thread.sleep(10000);
                        } catch (Exception e) {
                            e.printStackTrace();
                            continue;

                        }

                }
            }
        }
    } catch (Exception e) {
    e.printStackTrace();
    continue;
}
        }
    }

    //@Test
    static void BuyEqui () throws InterruptedException, IOException {
        Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\Lungile.Motsweni\\Documents\\Personal\\buy.txt"));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

        driver.get("https://platform.easyequities.io/Account/SignIn");
        driver.findElement(By.id("user-identifier-input")).sendKeys("lungilepm");
        driver.findElement(By.id("Password")).sendKeys("5744Lungile#1");
        driver.findElement(By.id("login-mobile")).click();

        for (CSVRecord record : csvParser) {
            try {
                driver.get("https://platform.easyequities.io/ValueAllocation/Buy?contractCode=EQU.ZA." + record.get(0));

                // driver.findElement(By.id("js-value-amount")).click();
                // driver.findElement(By.id("js-value-amount")).sendKeys("0.75");
                List<WebElement> value = driver.findElements(By.id("js-value-amount"));
                if (value.size() > 0) {
                    MethodHelpers.writeCSVLine("C:\\Users\\Lungile.Motsweni\\Documents\\Personal\\Listed.txt", record.get(0),true);
                    try {
                        value.get(0).click();
                        value.get(0).sendKeys("2");
                        // Thread.sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;

                    }
                }
                driver.findElement(By.id("js-number-of-shares")).click();
                new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@class='order-type-selector__label']")));
                List<WebElement> label = driver.findElements(By.xpath("//label[@class='order-type-selector__label']"));
                // List<WebElement> found = driver.findElements(By.xpath("//*[text()='Buy At Open']"));
                if (label.size() > 0) {

                    try {
                        label.get(1).click();
                        //Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;

                    }
                }
                new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Place Buy Order']")));



                List<WebElement> found = driver.findElements(By.xpath("//*[text()='Place Buy Order']"));
                // List<WebElement> found = driver.findElements(By.xpath("//*[text()='Buy At Open']"));
                if (found.size() > 0) {

                    try {
                        found.get(0).click();
                        //Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;

                    }
                }



            } catch (Exception e) {
                e.printStackTrace();
                MethodHelpers.writeCSVLine("C:\\Users\\Lungile.Motsweni\\Documents\\Personal\\reject.txt", record.get(0),true);
            }

        }
    }
}

