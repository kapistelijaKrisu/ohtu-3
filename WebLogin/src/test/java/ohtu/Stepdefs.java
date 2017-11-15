package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {

    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:4567";

    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @Given("^command new user is selected$")
    public void register_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    
    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created$")
    public void weird(String use, String pass) throws Throwable {
        register_selected();
        registerWith(use, pass);
    }
    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void strange(String use, String pass) throws Throwable {
        register_selected();
        registerWith(use, pass);
   
    }

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^registering with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void register_with_username_and_password_are_given(String username, String password) throws Throwable {
        registerWith(username, password);
    }

    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered")
    public void register_with_valid_username_and_matched_password_are_given(String username, String password) throws Throwable {
        registerWith(username, password);
    }

    @When("^clicked to home page")
    public void toHomePage() throws Throwable {
        WebElement element = driver.findElement(By.partialLinkText("mainpage"));
        element.click();
    }

    @When("^clicked logout$")
    public void loggingout() throws Throwable {
        WebElement element = driver.findElement(By.partialLinkText("logout"));
        element.click();
    }

    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        driver.getCurrentUrl().contains("ohtu");
    }

    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in() throws Throwable {
        pageHasContent("Give your credentials to login");
        pageHasContent("invalid username or password");
    }

    @Then("^a new user is created$")
    public void user_is_welcomed_in() throws Throwable {
        driver.getCurrentUrl().contains("welcome");
    }

    @Then("^user is not created and error \"username should have at least 3 characters\" is reported$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @Then("^user is not registered and error message is given$")
    public void user_is_not_registered_and_error_message_is_given() throws Throwable {
        pageHasContent("username should have at least 3 characters");
    }
    @Then("^user is not created and error \"password should have at least 8 characters\" is reported$")
    public void user_is_not_registered_and_error_message_is_given_pass_short() throws Throwable {
        pageHasContent("password should have at least 8 characters");
    }

    @Then("^user is not created and error \"username is already taken\" is reported$")
    public void user_is_not_registered_and_error_message_is_given_in_use() throws Throwable {
        pageHasContent("username is already taken");
    }

    @Then("^user is not created and error \"password and password confirmation do not match\" is reported$")
    public void user_is_not_registered_pass_conf_dont_match_pass() throws Throwable {
        pageHasContent("password and password confirmation do not match");
        pageHasContent("Give your credentials to login");
    }

    @Then("^user is logged out$")
    public void logged_out() {
        pageHasContent("login");
        pageHasContent("register new user");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void registerWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
}
