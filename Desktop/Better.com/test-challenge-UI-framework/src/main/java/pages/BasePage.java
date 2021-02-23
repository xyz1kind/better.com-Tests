package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Driver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BasePage {

    public static WebDriver driver;
    private static Random random = new Random();

    public static int pickRandomNumber(int maxValue) {
        return random.nextInt(maxValue);
    }

    public static void wait(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void waitForVisibility (WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), 5);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForVisibilityOfElements(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), 5);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public static void waitForClickabilityOfElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), 5);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static String getText (WebElement element) {
        waitForVisibility(element);
        return element.getText().trim();
    }

    public static void click (WebElement element) {
        waitForVisibility(element);
        waitForClickabilityOfElement(element);
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].click();", element);
    }

    public static void sendKeys (WebElement element, String keyword) {
        waitForVisibility(element);
        char[] chars = keyword.toCharArray();
        for(char c : chars) {
            element.sendKeys(String.valueOf(c));
        }
    }

    public static WebElement pickRandomElement (List<WebElement> elements) {
        int filter = pickRandomNumber(elements.size());
        WebElement selectElement = elements.get(filter);
        waitForClickabilityOfElement(selectElement);
        return selectElement;
    }

    public static String getScreenshot(String name) {
        SimpleDateFormat df = new SimpleDateFormat("-yyyy-MM-dd-HH-mm");

        String date = df.format(new Date());
        TakesScreenshot ts = (TakesScreenshot) Driver.get();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";

        File finalDestination = new File(target);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return target;
    }
}
