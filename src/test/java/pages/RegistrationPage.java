package pages;

import TestDataSets.TestData;
import Utils.ReusableMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
	private final WebDriver driver;
	WebDriverWait wait = ReusableMethods.getWait();

	String Url;

	@FindBy(xpath = "//a[normalize-space()='Signup / Login']")
	WebElement signup_login_button;

	@FindBy(xpath = "//*[@data-qa='signup-name']")
	WebElement signUp_name;

	@FindBy(xpath = "//*[@data-qa='signup-email']")
	WebElement email_element;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isOnHomePage() {
		wait.until(ExpectedConditions.urlContains(TestData.BASE_URL));
		Url = driver.getCurrentUrl();
		System.out.println(" --> currentUrl : " + Url + " Base_Url : " + TestData.BASE_URL);
		return Url.contains(TestData.BASE_URL);
	}

	public void clickSignUp_button() {
		try {
			ReusableMethods.clickConsentIfPresent();
			ReusableMethods.handleButton(signup_login_button);
			System.out.println(" --> signup_login_button text: " + signup_login_button.getText());
		} catch (Exception e) {
			System.out.println("Exception in clickSignUp_button: " + e.getMessage());
			throw e;
		}
	}

	public void enterMailAndName(String name, String email) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(signUp_name));
			wait.until(ExpectedConditions.elementToBeClickable(email_element));
			signUp_name.clear();
			email_element.clear();
			signUp_name.sendKeys(name);
			email_element.sendKeys(email);
		} catch (Exception e) {
			System.out.println("Error while entering name and email: " + e.getMessage());
			throw e;
		}
	}
}
