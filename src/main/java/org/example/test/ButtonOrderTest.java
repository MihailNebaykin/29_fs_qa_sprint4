package org.example;


import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.example.constants.Constants.*;

public class ButtonOrderTest {
    private WebDriver driver;

    @Test
    public void HeaderButtonOpenOrderPage() {
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();                                            // веб-драйвер для Google Chrome
        driver.get(testURL);                 //сайт
        HomePage objHomePage = new HomePage(driver);                           // объект класса домашней страницей
        objHomePage.clickButtonHeader();                                        // Клик на кнопку Заказать в хедере
        OrderPage objOrderPage = new OrderPage(driver);                        // объект класса со страницей заказа
        objOrderPage.isPageOpen(objOrderPage.getOrderHeader(), HEADER_ORDER);   // Проверка, что страница заказа открылась

    }

    @Test
    public void MiddleButtonOpenOrderPage() {
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();                                            // веб-драйвер для Google Chrome
        driver.get(testURL);                                                    //сайт
        HomePage objHomePage = new HomePage(driver);                           // Создать объект класса с домашней страницей
        objHomePage.clickButtonMiddle();                                        //Клик на кнопку Заказать в хедере
        OrderPage objOrderPage = new OrderPage(driver);                        // объект класса со страницей заказа
        objOrderPage.isPageOpen(objOrderPage.getOrderHeader(), HEADER_ORDER);   // Проверка, что страница заказа открылась
    }

    @After
    public void teardown() {
        driver.quit();
    }
}