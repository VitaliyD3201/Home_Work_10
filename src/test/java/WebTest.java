import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WebTest {
    @Test
    void successfulSearchTest() {
        open("https://www.rambler.ru/");
        $("[name=query]").setValue("Steezy").pressEnter();
        $$("[li.serp-item]").shouldBe(CollectionCondition.sizeGreaterThan(0));
    }
}
