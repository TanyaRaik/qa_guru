package tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideHomeworkGitHub {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void successfulSubmitFormTest() {
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
    }
}