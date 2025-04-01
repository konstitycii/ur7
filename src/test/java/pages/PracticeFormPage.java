package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.ResultsTableComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    // Локаторы элементов
    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderRadio = $("#genterWrapper"),
            mobileInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesCheckbox = $("#hobbiesWrapper"),
            pictureUpload = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateDropdown = $("#state"),
            cityDropdown = $("#city"),
            submitButton = $("#submit");

    private ResultsTableComponent resultsTable = new ResultsTableComponent();

    // Метод для открытия страницы
    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()"); // Убираем баннер
        executeJavaScript("$('footer').remove()");   // Убираем футер
        return this;
    }

    // Методы для заполнения полей
    public PracticeFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public PracticeFormPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public PracticeFormPage selectGender(String gender) {
        genderRadio.$(byText(gender)).click();
        return this;
    }

    public PracticeFormPage setMobile(String value) {
        mobileInput.setValue(value);
        return this;
    }

    public PracticeFormPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click(); // Открываем календарь
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__day.react-datepicker__day--%02d".formatted(Integer.parseInt(day))).click();
        return this;
    }

    public PracticeFormPage setSubjects(String... values) {
        for (String value : values) {
            subjectsInput.setValue(value).pressEnter();
        }
        return this;
    }

    public PracticeFormPage selectHobbies(String... hobbies) {
        for (String hobby : hobbies) {
            hobbiesCheckbox.$(byText(hobby)).click();
        }
        return this;
    }

    public PracticeFormPage uploadPicture(String fileName) {
        pictureUpload.uploadFromClasspath(fileName);
        return this;
    }

    public PracticeFormPage setAddress(String value) {
        addressInput.setValue(value);
        return this;
    }

    public PracticeFormPage selectStateAndCity(String state, String city) {
        stateDropdown.click();
        $(byText(state)).click();
        cityDropdown.click();
        $(byText(city)).click();
        return this;
    }

    public PracticeFormPage submitForm() {
        submitButton.click();
        return this;
    }

    // Делегирование методов для работы с таблицей результатов
    public void verifyModalTitle(String expectedTitle) {
        resultsTable.verifyModalTitle(expectedTitle);
    }

    public void verifySubmittedData(String key, String value) {
        resultsTable.verifySubmittedData(key, value);
    }
}