package org.example;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.example.constants.Constants.*;


@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phoneNumber;
    private final String date;
    private final String rentaPeriod;
    private final String color;
    private final String comment;

    public OrderTest(String name, String surname, String address, String subway, String phoneNumber, String date,
                     String rentalPeriod, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = subway;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentaPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getDateSetForOrder() {
        return new Object[][]{
                {"Олеся", "Пронина", "Москва, ул. Пролетарская 65", "Кузьминки", "89278565455", "20.07.2025", "сутки", COLOR_BLACK, "Сообщить о доставке"},
                {"Давид", "Джан", "Москва, ул Октябрьская 15", "Сокольники", "89059486622", "25.07.2025", "семеро суток", COLOR_GREY, "позвоните за пол часа"},
        };
    }

    @Test
    public void OrderPositiveTest() {
        //// веб-драйвер для Firefox
         driver = new FirefoxDriver();
        // веб-драйвер для Chrome
        //driver = new ChromeDriver();
        // Открыть страницу заказа Яндекс Самокат
        driver.get(testURL);
        // Создать объект класса с домашней страницей
        HomePage objHomePage = new HomePage(driver);
        // Принять куки
        objHomePage.clickAcceptCookieButton();
        // Нажать на кнопку Заказать в Header
        objHomePage.clickButtonHeader();
        // Создать объект класса со страницей заказа
        OrderPage objOrderPage = new OrderPage(driver);

        // Позитивный сценарий оформления заказа
        objOrderPage.setName(name);
        objOrderPage.setSurname(surname);
        objOrderPage.setAddress(address);
        objOrderPage.setMetro(metro);
        objOrderPage.setPhoneNumber(phoneNumber);
        objOrderPage.clickOrderNextButton();
        objOrderPage.setDate(date);
        objOrderPage.setRentalPeriod(rentaPeriod);
        objOrderPage.setColor(color);
        objOrderPage.setComment(comment);
        objOrderPage.clickOrderCreateButton();
        objOrderPage.clickOrderConfirmButton();
        objOrderPage.getConfirmHeader();
        objOrderPage.isPageOpen(objOrderPage.getConfirmHeader(), VIEW_STATUS);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}