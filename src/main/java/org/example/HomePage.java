package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;


public class HomePage {

    private WebDriver driver;

    //           *** ЛОКАТОРЫ ВОПРОСОВ ***
    // Создаем массив текстовый из фильтров для xpath для вопросов
    private String[] questions = new String[]{
            ".//div[@class='accordion__item'][1]", //Сколько это стоит? И как оплатить?
            ".//div[@class='accordion__item'][2]", //Хочу сразу несколько самокатов! Так можно?
            ".//div[@class='accordion__item'][3]", //Как рассчитывается время аренды?
            ".//div[@class='accordion__item'][4]", //Текст кнопки: Можно ли заказать самокат прямо на сегодня?
            ".//div[@class='accordion__item'][5]", //Текст кнопки: Можно ли продлить заказ или вернуть самокат раньше?
            ".//div[@class='accordion__item'][6]", //Текст кнопки: Вы привозите зарядку вместе с самокатом?
            ".//div[@class='accordion__item'][7]", //Текст кнопки: Можно ли отменить заказ?
            ".//div[@class='accordion__item'][8]"  //Текст кнопки: Я жизу за МКАДом, привезёте?
    };

    //          *** ЛОКАТОРЫ ОТВЕТОВ ***
    private String[] answers = new String[]{
            "accordion__panel-0",  //Сколько это стоит? И как оплатить?
            "accordion__panel-1",  //Хочу сразу несколько самокатов! Так можно?
            "accordion__panel-2",  //Как рассчитывается время аренды?
            "accordion__panel-3",  //Текст кнопки: Можно ли заказать самокат прямо на сегодня?
            "accordion__panel-4",  //Текст кнопки: Можно ли продлить заказ или вернуть самокат раньше?
            "accordion__panel-5",  //Текст кнопки: Вы привозите зарядку вместе с самокатом?
            "accordion__panel-6",  //Текст кнопки: Можно ли отменить заказ?
            "accordion__panel-7"   //Текст кнопки: Я жизу за МКАДом, привезёте?
    };

    //             *** ЛОКАТОРЫ КНОПОК ***
//  кнопка «Заказать» Button_Button__ra12g в Header
    private By buttonHeader = By.cssSelector(".Button_Button__ra12g");
    //  кнопка «Заказать» Button_Button__ra12g Button_Middle__1CSJM в центре
    private By buttonMiddle = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
//Заполнить форму заказа

    //             *** ЛОКАТОР КУКИ ***
//private By acceptCookieButton = By.xpath(".//button[text()='да все привыкли']");
    private By acceptCookieButton = By.cssSelector(".App_CookieButton__3cvqF");

    //конструктор
    public HomePage(WebDriver driver) {
        System.out.println("driver" + driver);
        this.driver = driver;
    }

    //_________________МЕТОДЫ_______________________
    //клик по кнопке "Заказать" в Header
    public void clickButtonHeader() {
        driver.findElement(buttonHeader).click();
    }

    // клик и скролл для кнопки Заказать
    public void clickButtonMiddle() {

//        new WebDriverWait(driver, Duration.ofSeconds(3))
//                .until(ExpectedConditions.visibilityOfElementLocated(buttonMiddle));
//        driver.findElementElement(buttonMiddle);
//        clickAcceptCookieButton();
        WebElement buttonMiddleWe = driver.findElement(buttonMiddle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", buttonMiddleWe);
        driver.findElement(buttonMiddle).click();
    }

    //клик по кнопке Куки "да все привыкли"
    public void clickAcceptCookieButton() {
        driver.findElement(acceptCookieButton).click();
    }

    //метод клик по вопросу - из массива формируем xpath вопроса, находим его и кликаем по нему.
    public void clickQestion(int indexNum) {
        // System.out.println("question" + questions[indexNum]);//убрать после отладки
        // Из массива questions по индексу indexNum берем значение фильтра для xpath
        String question_xpath = questions[indexNum];
        By question = By.xpath(question_xpath);
        //  System.out.println("question" + question);//убрать после отладки
        driver.findElement(question).click();
    }

    //Метод получения текста из ответа - из массива по индексу находим xpath ответа, находим его,
    //ждем отображение элемента, возвращаем текст
    public String getTextAnswer(int indexNum) {
        // Из массива answers по индексу indexNum берем значение фильтра для xpath
        String answer_xpath = answers[indexNum];
        By answer = By.id(answer_xpath);

        //ждем элемент
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(answer));
        //получаем текст
        String answerText = driver.findElement(answer).getText();
        // System.out.println("answerText " + answerText);
        return answerText;
    }

    // ИТОГОВЫЙ ШАГ
    //клик по вопросу, получение текста вопроса, сравнение с константой
    public void checkAnswer(String answerConstanta, int indexNum) {
        //Кликаем по кнопке
        clickQestion(indexNum);
        // Получаем ответ
        String answerText = getTextAnswer(indexNum);
        // Ожидание загрузки элемента
        System.out.println("answerConstanta   answerText " + answerConstanta + " " + answerText);
        // Сравнение результата
        assertEquals(answerConstanta, answerText);
    }
}