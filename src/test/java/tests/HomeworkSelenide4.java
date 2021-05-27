package tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HomeworkSelenide4 {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void GitHubTest() {
        //Open git hub
        open("https://github.com/");

        //print "selenide" and click Enter
        $(byName("q")).setValue("selenide").pressEnter();

        //open first Repository
        $("[href='/selenide/selenide']").click();

        //Verify that correct repository is opened
        $("h1").shouldHave(text("selenide / selenide"));
        $(".BorderGrid-cell").shouldHave(text("Concise UI Tests with Java!"));


        //Move to wiki tab
        $(byText("Wiki")).click();

        //Filter "SoftAssertions"
        $("#wiki-pages-filter").setValue("SoftAssertions").pressEnter();

        // Make sure SoftAssertions is visible and click the link
        $(byText("SoftAssertions")).should(visible).click();


        //Asserts for JUnit5
        $("#wiki-body")
                .shouldHave(text("JUnit5 extension - com.codeborne.selenide.junit5.SoftAssertsExtension"));
        $("#wiki-body")
                .shouldHave(text("Using JUnit5 extend test class:"));
        $("#wiki-body").$(byText("Using JUnit5 extend test class:")).closest("div").should(visible);
    }


    @Test
    void DragAndDropTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));

        $("#column-a").dragAndDropTo($("#column-b"));

        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }


    /*
    Прошу помощи с заданием номер 1.

    Судя принятым ответам ребят, правильный ответ - "Разницы нет".
    У меня на пркатике получается, что это не так для сайтов со сложной структурой:

    если запустить тест, то он упадет на четвертой строке с ошибкой 'Element not found {div/h1}'

    Получается, $("div h1") ищет в элемент с помошью селектора {div h1}
    Тогда как второй способ {div/h1} сначала найдет первый попавшийся div и внутри него будет искать первый попавшийся h1,
     и если в первом div заголовка h1 нет, то тест упадет (как это видно для https://github.com/qa-guru)

    Таким образом, элемент может быть не найден без указания какого-нить дополнительного параметра, скажем, value класса,
     чтобы заведомо точно папость в нужный нам div.


     */

    @Test
    void LocatorsTest() {
        open("https://github.com/qa-guru");

        String first = $("div[class='flex-1'] h1").getText();
        String second = $("div[class='flex-1']").$("h1").getText();

        String third = $("div h1").getText();

        //Test will fail HERE
        String forth = $("div").$("h1").getText();
    }
}