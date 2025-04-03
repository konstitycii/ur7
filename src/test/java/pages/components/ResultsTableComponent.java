package pages.components;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ResultsTableComponent {

    private final SelenideElement modalTitle = $(".modal-title"),
            modalContent = $(".table-responsive");

    public void verifyModalTitle(String expectedTitle) {
        modalTitle.shouldHave(text(expectedTitle));
    }

    public void verifySubmittedData(String key, String value) {
        modalContent.shouldHave(text(key), text(value));
    }
}