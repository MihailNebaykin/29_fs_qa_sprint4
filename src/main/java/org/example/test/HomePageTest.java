package org.example.test;

import org.example.HomePage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.example.constants.Constants.*;


public class HomePageTest {

    private WebDriver driver;

    @Test
    public void homePageTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--window-size=1280,800", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get(testURL);

        // Скролл до таблицы с вопросами
        WebElement importantQuestions = driver.findElement(By.cssSelector(".accordion"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", importantQuestions);

        //создаем объект класса HomePage
        HomePage objHomePage = new HomePage(driver);

        objHomePage.clickAcceptCookieButton();

        //вопрос?
        for (int i = 0; i < ANSWER_TEXTS.length; i++) {
            // System.out.println("Обработка" + i);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", importantQuestions);
            objHomePage.checkAnswer(ANSWER_TEXTS[i], i);
        }
        driver.quit();
    }
}