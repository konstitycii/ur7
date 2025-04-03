package pages.components;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    private final SelenideElement
            dateInput = $("#dateOfBirthInput"),
            yearSelect = $(".react-datepicker__year-select"),
            monthSelect = $(".react-datepicker__month-select");

    public CalendarComponent setDate(String day, String month, String year) {
        dateInput.click();
        yearSelect.selectOption(year);
        monthSelect.selectOption(month);
        String dayLocator = String.format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", day);
        $(dayLocator).click();
        return this;
    }
}