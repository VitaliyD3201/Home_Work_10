import com.codeborne.selenide.CollectionCondition;
import data.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;



public class DuckWebTest {

    @BeforeEach
    void setUp() {
        open("https://duckduckgo.com/");
    }


    @Test
    @Tag("block")
    @DisplayName("для поискового запроса отображается не пустой список")
    void successfulSearchTest() {
        $("#searchbox_input").setValue("Steezy").pressEnter();
        $$("[data-testid=mainline] li[data-layout=organic]").shouldBe(
                CollectionCondition.sizeGreaterThan(0));

    }

    @ValueSource(strings = {
            "sunday","monday"
    })
    @ParameterizedTest(name = "Параметризация для поискового запроса {0} отображается не пустой список")
    @Tag("block")
    void successfulSearchTestParameterized(String searchQuery) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $$("[data-testid=mainline] li[data-layout=organic]").shouldBe(
                CollectionCondition.sizeGreaterThan(0));

    }

    @CsvSource(value = {
            "Selenide , https://selenide.org",
            "JUnit 5 , https://junit.org"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должна быть ссылка {1}")
    @Tag("BLOCKER")
    void searchResultsShouldContainExpectedUrl(String searchQuery, String expectedLink) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $("[data-testid='mainline'] li[data-layout='organic']")
                .shouldHave(text(expectedLink));
    }


}
