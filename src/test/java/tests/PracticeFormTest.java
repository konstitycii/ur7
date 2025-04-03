package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PracticeFormTest {

    private final PracticeFormPage formPage = new PracticeFormPage();

    @BeforeEach
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    public void fillAndSubmitFullFormTest() {
        formPage.openPage()
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("john.doe@example.com")
                .selectGender("Male")
                .setMobile("1234567890")
                .setDateOfBirth("15", "May", "1990") // Теперь работает корректно
                .setSubjects("Maths", "Physics", "Chemistry")
                .selectHobbies("Sports", "Reading", "Music")
                .uploadPicture("i.webp")
                .setAddress("123 Main St, Springfield")
                .selectStateAndCity("NCR", "Delhi")
                .submitForm();

// Проверяем результат
        formPage.verifyModalTitle("Thanks for submitting the form");
        formPage.verifySubmittedData("Student Name", "John Doe");
        formPage.verifySubmittedData("Student Email", "john.doe@example.com");
        formPage.verifySubmittedData("Gender", "Male");
        formPage.verifySubmittedData("Mobile", "1234567890");
        formPage.verifySubmittedData("Date of Birth", "15 May,1990");
        formPage.verifySubmittedData("Subjects", "Maths, Physics, Chemistry");
        formPage.verifySubmittedData("Hobbies", "Sports, Reading, Music");
        formPage.verifySubmittedData("Picture", "i.webp");
        formPage.verifySubmittedData("Address", "123 Main St, Springfield");
        formPage.verifySubmittedData("State and City", "NCR Delhi");
    }
    @Test
    public void fillAndSubmitMinimalFormTest() {
        PracticeFormPage formPage = new PracticeFormPage();

        formPage.openPage()
                .setFirstName("John")
                .setLastName("Doe")
                .selectGender("Male")
                .setMobile("1234567890")
                .submitForm();

        // Проверяем результат
        formPage.verifyModalTitle("Thanks for submitting the form");
        formPage.verifySubmittedData("Student Name", "John Doe");
        formPage.verifySubmittedData("Gender", "Male");
        formPage.verifySubmittedData("Mobile", "1234567890");
    }
    @Test
    public void negativeTestWithoutRequiredFields() {
        PracticeFormPage formPage = new PracticeFormPage();

        formPage.openPage()
                .setLastName("Doe") // Без имени
                .selectGender("Male")
                .setMobile("1234567890")
                .submitForm();

        // Проверяем, что форма не отправлена
        $(".modal-dialog").shouldNotBe(visible); // Модальное окно не должно появиться
    }
}