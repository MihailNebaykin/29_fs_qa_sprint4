package org.example;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class OrderPage {
    private WebDriver driver;
    //          *** ЛОКАТОРЫ  ***
    //----------------------------ФОРМА------------------------------------------------
    // Имя
    private By inputName = By.xpath(".//input[@placeholder='* Имя']");
    // Фамилия
    private By inputSurname = By.xpath(".//input[@placeholder='* Фамилия']");
    // Адрес: куда привезти заказ
    private By inputAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Селектор со списком станций метро
    private By inputMetro = By.xpath(".//input[@placeholder='* Станция метро']");
    // Телефон: на него позвонит курьер
    private By inputPhoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка Далее
    private By nextButton = By.xpath(".//button[text()='Далее']");
    // Когда привезти самокат
    private By dateOrder = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Поле Срок аренды
    private By rentsDays = By.xpath(".//div[@class='Dropdown-placeholder']"); //1элемент
    // Комментарий для курьера
    private By inputComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //----------------------------------------------------------------------------
    // Кнопка Заказать
    private By orderCreateButton = By.xpath("//div[contains(@class,'Order_Buttons')]/button[text()='Заказать']");
    // Кнопка Да подтверждения заказа
    private By buttonYes = By.xpath(".//button[text()='Да']");
    // Кнопка посмотреть статус
    private By statusOrder = By.xpath(".//button[text()='Посмотреть статус']");
    //Заголовок для проверки
    private By orderHeader = By.xpath(".//div[text()='Для кого самокат']");

    //конструктор
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //           *** МЕТОДЫ Заполнения полей форм ***
    // Метод для ввода: Имя
    public void setName(String name) {
        driver.findElement(inputName).sendKeys(name);
    }

    // Метод для ввода: Фамилия
    public void setSurname(String surname) {
        driver.findElement(inputSurname).sendKeys(surname);
    }

    // Метод для ввода: Адрес: куда привезти заказ
    public void setAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address);
    }

    // Метод для ввода: Cтанция метро
    public void setMetro(String metro) {
        driver.findElement(inputMetro).click();
        driver.findElement(By.xpath(String.format("//div[text()='%s']", metro))).click();
    }

    // Метод для ввода: Телефон: на него позвонит курьер
    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(inputPhoneNumber).sendKeys(phoneNumber);
    }

    // Метод для кнопки Далее (переход на вторую страницу формы заказа)
    public void clickOrderNextButton() {
        driver.findElement(nextButton).click();
    }

    //  Метод для выбора: поля Дата (когда привезти самокат)
    public void setDate(String date) {
        driver.findElement(dateOrder).sendKeys(date);
        driver.findElement(dateOrder).sendKeys(Keys.ENTER); //закрываем календарь
    }

    // Метод для ввода: поля Срок аренды
    public void setRentalPeriod(String rentalPeriod) {
        driver.findElement(rentsDays).click();
        //ОЖИДАНИЕ
        //  new WebDriverWait(driver, Duration.ofSeconds(3))
        //           .until(ExpectedConditions.visibilityOfElementLocated(rentsDays));
        driver.findElement(By.xpath(".//div[text()='" + rentalPeriod + "']")).click();
    }

    // Метод для выбора: поля Цвет самоката
    public void setColor(String color) {
        driver.findElement(By.xpath(String.format(".//label[text()='%s']", color))).click();
    }

    // Метод для ввода: поля Комментарий для курьера
    public void setComment(String comment) {
        driver.findElement(inputComment).sendKeys(comment);
    }

    //  Метод для ввода: к подтверждению заказа
    public void clickOrderCreateButton() {
        driver.findElement(orderCreateButton).click();
    }

    // Метод для подтверждения заказа
    public void clickOrderConfirmButton() {
        driver.findElement(buttonYes).click();
    }

    // Метод для получения текста заголовка страницы заказа
    public String getOrderHeader() {
        return driver.findElement(orderHeader).getText();
    }

    // Геттер для получения текста на кнопке для просмотра статуса заказа
    public String getConfirmHeader() {
        return driver.findElement(statusOrder).getText();
    }

    // Метод для проверки открытия страницы
    public void isPageOpen(String headerText, String text) {
        Assert.assertEquals(headerText, text);
        // System.out.println("headerText, text: " + headerText + " " + text);
    }
}