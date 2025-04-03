package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultsTableComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderRadio = $("#genterWrapper"),
            mobileInput = $("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            hobbiesCheckbox = $("#hobbiesWrapper"),
            pictureUpload = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateDropdown = $("#state"),
            cityDropdown = $("#city"),
            submitButton = $("#submit");

    private final ResultsTableComponent resultsTable = new ResultsTableComponent();
    private final CalendarComponent calendar = new CalendarComponent();

    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        removeBanners();
        return this;
    }

    public PracticeFormPage removeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
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

    // Добавляем недостающий метод для email
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
        calendar.setDate(day, month, year);
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

    // Методы для проверки результатов
    public void verifyModalTitle(String expectedTitle) {
        resultsTable.verifyModalTitle(expectedTitle);
    }

    public void verifySubmittedData(String key, String value) {
        resultsTable.verifySubmittedData(key, value);
    }
}