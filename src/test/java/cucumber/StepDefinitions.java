package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.remote.CapabilityType;

/**
 * Step definitions for Cucumber tests.
 */
public class StepDefinitions {
	private static final String ROOT_URL = "https://localhost:8080/";
	
	ChromeOptions capability = new ChromeOptions();
	
	private WebDriver driver;	
	int i = 0;
	@Given("I am on the {string} page")
	public void i_am_on_the_page(String string) {
		if (i ==0){
			capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
			driver = new ChromeDriver(capability);
		}
		i++;

		if (string.equals("Login") || string.equals("Home")) {
			driver.get(ROOT_URL);
		}else if (string.equals("Sign Up")) {
			driver.get(ROOT_URL+"signup");
		}else if (string.equals("Settings")) {
			driver.get(ROOT_URL+"settings");
		}
		else if (string.equals("Propose")) {
			driver.get(ROOT_URL+"propose");
		}
		else if(string.equals("Inviter")) {
			driver.get(ROOT_URL+"inviter?groupdate_id=86");
		}
	}

	@Given("I reload the page")
	public void i_reload_the_page() throws InterruptedException{
		driver.navigate().refresh();
		Thread.sleep(2000);
	}

	@Given("I go to the Settings Page")
	public void i_go_to_the_Settings_Page() {
		driver.get(ROOT_URL+"settings");
	}

	@Given("I click Sign Up")
	public void i_click_Sign_Up() throws InterruptedException {
		WebElement link = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/form/p/a"));
		link.click();
		Thread.sleep(2000);
	}

	@Then("I should see Sign Up Header")
	public void i_should_see_Sign_Up_Header() {
		WebElement result = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/h1"));
		assertTrue(result.getAttribute("innerHTML").equals("Sign Up"));
	}

	@Then("I should see Login Header")
	public void i_should_see_Login_Header() {
		WebElement result = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/h1"));
		assertTrue(result.getAttribute("innerHTML").equals("Log In"));
	}

	@Given("I press the button Login")
	public void i_press_the_button_Login() throws InterruptedException {
		WebElement button = driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[3]/div/div/div/button"));
		button.click();
		Thread.sleep(2000);
	}

	@Given("I type in username textbox {string}")
	public void i_type_in_username_textbox(String string) {
		WebElement result = driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[1]/div[2]/div/div/input"));
		result.sendKeys(string);
	}

	@Given("I type in password textbox {string}")
	public void i_type_in_password_textbox(String string) {
		WebElement result = driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[2]/div[2]/div/div/span/input"));
		result.sendKeys(string);
	}

	@Given("I type in Confirm password textbox {string}")
	public void i_type_in_Confirm_password_textbox(String string) { 
		WebElement result = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/form/div[3]/div[2]/div/div/span/input"));
		result.sendKeys(string);
	}

	@Given("I press the button Create Account")
	public void i_press_the_button_Create_Account() throws InterruptedException {
		WebElement button = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/form/div[4]/div/div/div/button"));
		button.click();
		Thread.sleep(2000);
	}

	@Then("I should be directed to Home page")
	public void i_should_be_directed_to_home_page() {
		String result = driver.getCurrentUrl();
		assertEquals(ROOT_URL, result);	
	}

	@Given("I click the link {string}")
	public void i_click_the_link(String string) throws InterruptedException {
		WebElement link = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/form/p/a"));
		link.click();
		Thread.sleep(2000); 
	}

	@Then("I should still be on {string} Page")
	public void i_should_still_be_on_Page(String string) {
		String result = driver.getCurrentUrl();
		if (string.equals("Login")){
			assertEquals(ROOT_URL, result);
		} else if (string.equals("Sign Up")){
			assertEquals(ROOT_URL+"signup", result);
		}	
	}

	@Given("I am logged in")
	public void i_am_logged_in() throws InterruptedException {
		i_am_on_the_page("Login");
		i_type_in_username_textbox("testUsername");
		i_type_in_password_textbox("testPassword");
		i_press_the_button_Login();
  }

	@Given("I am logged in as alt user")
	public void i_am_logged_in_as_alt_user() throws InterruptedException {
		i_am_on_the_page("Login");
		i_type_in_username_textbox("altUser");
		i_type_in_password_textbox("altPassword");
		i_press_the_button_Login();
	}

	@Given("I type in {string}")
	public void i_type_in(String string) throws InterruptedException {
		Thread.sleep(5000); 
		WebElement result = driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/div/div[1]/div/div/span[1]/input"));
		result.sendKeys(string);
	}

	@Given("I click Block")
	public void i_click_Block() throws InterruptedException {
		WebElement link = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div/div[1]/button"));
		link.click();
		Thread.sleep(3000); 
	}
	
	@Then("I should see blocked user {string}")
	public void i_should_see_blocked_user(String string) throws InterruptedException {
		WebElement result = driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/div/div[2]/div/p"));
		assertTrue(result.getAttribute("innerHTML").equals(string));
	}

	// Propose

	@Given("I type in a title")
	public void i_type_in_a_title() {
		WebElement title = driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/div[1]/form/div[1]/div[2]/div/div/input"));
		title.sendKeys("Title");
	}

	@Given("I type in a keyword")
	public void i_type_in_a_keyword() {
		WebElement keyword = driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/div[1]/form/div[2]/div[2]/div/div/input"));
		keyword.sendKeys("suns");
	}

	@Given("I enter a time")
	public void i_enter_a_time() throws InterruptedException {
		WebElement start = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[1]/form/div[3]/div[2]/div/div/div/div[1]/input"));
		start.sendKeys("2021-11-01 17:02:22");

		WebElement end = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[1]/form/div[3]/div[2]/div/div/div/div[3]/input"));
		end.sendKeys("2021-12-01 17:02:22");

	    Thread.sleep(2000); // Wait for ticketmaster api
	}

	@Given("I add some events")
	public void i_add_some_events() {
		WebElement checkbox = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div/ul/li[1]/label/span/input"));
		checkbox.click();
	}

	@Given("I click next")
	public void i_click_next() {
		WebElement next = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/button"));
		next.click();
	}

	@Given("I invite a valid user")
	public void i_invite_a_valid_user() throws InterruptedException {
		WebElement autocomplete = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div[1]/div/div/span[1]/input"));
		autocomplete.click();
		Thread.sleep(2000);

		WebElement user = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]"));
		user.click();
	}

	@Given("I invite a blocked user")
	public void i_invite_a_blocked_user() throws InterruptedException { // altUser blocked testUsername
		WebElement autocomplete = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div[1]/div/div/span[1]/input"));
		autocomplete.click();
		Thread.sleep(2000);

		WebElement user = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[1]/div/div/div[4]"));
		user.click();
		Thread.sleep(2000);
	}

	@Given("I invite an unavailable user")
	public void i_invite_an_unavailable_user() throws InterruptedException { // busyUser is not available 
	    WebElement autocomplete = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div[1]/div/div/span[1]/input"));
		autocomplete.click();
		Thread.sleep(2000);

		WebElement user = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[1]/div/div/div[5]"));
		user.click();
		Thread.sleep(2000);
	}

	@Then("I should see a notification saying the user is unavailable")
	public void i_should_see_a_notification_saying_the_user_is_unavailable() {
	    Boolean notifExists = driver.findElements(By.xpath("/html/body/div[3]/div/div/div/div/div/div[1]")).size() != 0;
	    assertTrue(notifExists);

	    String notifText = driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div/div[1]")).getAttribute("innerText");
	    assertTrue(notifText.equals("This user cannot be added as they are blocked or have a scheduling conflict"));
	}

	@Given("I click send")
	public void i_click_send() {
		WebElement send = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/button[1]"));
		send.click();
	}

	@Then("I should be directed to invite page")
	public void i_should_be_directed_to_invite_page() {
		String result = driver.getCurrentUrl();
		assertEquals(ROOT_URL+"propose", result);
	}

	@Given("next to {string}, I click Unblock")
	public void next_to_I_click_Unblock(String string) throws InterruptedException {
		WebElement link = driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/div/div[2]/div/button"));
		link.click();
		Thread.sleep(3000); 
	}

	@Then("I should see no users are blocked")
	public void i_should_see_no_users_are_blocked() {
		Boolean doesNotExist = driver.findElements(By.xpath("/html/body/div/div/div/div[3]/div/div/div[2]/div/p")).size() == 0;
		assertTrue(doesNotExist == true);
	}

	@Then("I should see Settings Header")
	public void i_should_see_Settings_Header() throws InterruptedException {
		WebElement result = driver.findElement(By.xpath("//*[@id='root']/div/div/h1"));
		assertTrue(result.getAttribute("innerHTML").equals("Settings"));
	}

	// Unavail

	@Given("I add unavailability")
	public void i_add_unavailability() throws InterruptedException {
		WebElement dragStart = driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div[2]/div[2]/div[2]/div[2]/div/div[5]"));
		WebElement dragEnd = driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div[2]/div[2]/div[2]/div[2]/div/div[6]"));

		Actions builder = new Actions(driver);

		Action drag = builder.clickAndHold(dragStart)
		.moveToElement(dragEnd)
		.release(dragEnd)
		.build();

		drag.perform();
		Thread.sleep(2000);
	}

	@Then("I can see the unavailability event on the calendar")
	public void i_can_see_the_unavailability_event_on_the_calendar() {
		Boolean unavailEventExists = driver.findElements(By.xpath("/html/body/div/div/div/div[3]/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]")).size() != 0;
		assertTrue(unavailEventExists == true);
	}

	@Given("I click the created unavailability event on the calendar")
	public void i_click_the_created_unavailability_event_on_the_calendar() {
		WebElement unavailEvent = driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]"));
		unavailEvent.click();
	}

	@Then("I can no longer see the unavailability event on the calendar")
	public void i_can_no_longer_see_the_unavailability_event_on_the_calendar() {
		Boolean unavailEventExists = driver.findElements(By.xpath("/html/body/div/div/div/div[3]/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]")).size() != 0;
		assertTrue(unavailEventExists == false);
	}
	// Activity acceptance tests

	@Given("I click on unfinalized")
	public void i_click_on_unfinalized() throws InterruptedException {
    	WebElement link = driver.findElement(By.xpath("/html/body/div/div/div/div/div[1]/button[2]"));
		link.click();
		Thread.sleep(3000); 
    	
	}
	@Given("I click on an unfinalized event")
	public void i_click_on_an_unfinalized_event() throws InterruptedException {
    	WebElement link = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div[2]"));
		link.click();
		Thread.sleep(3000); 
    	
	}

	@Given("I click on finalized")
	public void i_click_on_finalized() throws InterruptedException {
    	WebElement link = driver.findElement(By.xpath("/html/body/div/div/div/div/div[1]/button[1]"));
		link.click();
		Thread.sleep(3000); 
    	
	}

	@Then("I can only see the unfinalized events")
	public void i_can_only_see_the_unfinalized_events() {
		Boolean unfinalizedEventExists = driver.findElements(By.xpath("/html/body/div/div/div/div/div[2]/div/p[2]")).size() != 0;
		// Only check matching if it exists
		int i = 2;

		while(unfinalizedEventExists) {
			WebElement result = driver.findElement(By.xpath("/html/body/div/div/div/div/div["+i+"]/div/p[2]"));
			assertTrue(result.getAttribute("innerHTML").equals("Unfinalized"));
			i++;
			unfinalizedEventExists = driver.findElements(By.xpath("/html/body/div/div/div/div/div["+i+"]/div/p[2]")).size() != 0;
		}
	}
	@Then("I can only see the finalized events")
	public void i_can_only_see_the_finalized_events() {
		Boolean respondedEventExists = driver.findElements(By.xpath("/html/body/div/div/div/div/div[2]/div/p[2]")).size() != 0;
		assertTrue(respondedEventExists == false);
	}

	@Given("I click on responded")
	public void i_click_on_responded() throws InterruptedException {
    	WebElement link = driver.findElement(By.xpath("/html/body/div/div/div/div/div[1]/button[3]"));
		link.click();
		Thread.sleep(3000); 
    	
	}

	@Given("I click on unresponded")
	public void i_click_on_unresponded() throws InterruptedException {
    	WebElement link = driver.findElement(By.xpath("/html/body/div/div/div/div/div[1]/button[4]"));
		link.click();
		Thread.sleep(3000); 
    	
	}

	@Given("I click on the activity feed")
	public void i_click_on_the_activity_feed() throws InterruptedException {
    	WebElement link = driver.findElement(By.xpath("//*[@id='root']/div/div/div[1]/ul/li[3]/a"));
		link.click();
		Thread.sleep(3000); 
    
	}

	@Then("I can only see the responded events")
	public void i_can_only_see_the_responded_events() {
		Boolean respondedEventExists = driver.findElements(By.xpath("/html/body/div/div/div/div/div[2]/div/p[2]")).size() != 0;
		assertTrue(respondedEventExists == false);
	
	}

	@Then("I can only see the unresponded events")
	public void i_can_only_see_the_unresponded_events() {
		Boolean respondedEventExists = driver.findElements(By.xpath("/html/body/div/div/div/div/div[2]/div/p[2]")).size() != 0;
		assertTrue(respondedEventExists == true);
	
	}
	
	@Given("I am inactive for 60 seconds")
	public void i_am_inactive_for_60_seconds() throws InterruptedException {
		Thread.sleep(60000); // 60 seconds inactivity
		Thread.sleep(10000); // to account for website loading time
	}

	@Given("I am inactive for 45 seconds")
	public void i_am_inactive_for_45_seconds() throws InterruptedException {
		Thread.sleep(45000); 
	}

	@Given("I am inactive for 40 seconds")
	public void i_am_inactive_for_40_seconds() throws InterruptedException {
		Thread.sleep(40000); // 60 seconds inactivity
	}

	@Then("I should be locked out for 60 seconds")
	public void i_should_be_locked_out_for_60_seconds() throws InterruptedException {
		WebElement result = driver.findElement(By.xpath("/html/body/div[5]/div/div/span"));
		assertTrue(result.getAttribute("innerHTML").equals("You are currently locked out, please wait a minute before trying to log in again"));
		Thread.sleep(60000); // 60 seconds inactivity
		Thread.sleep(5000); // to account for website loading time
		driver.get(ROOT_URL); // refresh
		Thread.sleep(2000); // to account for website loading time
		Boolean doesNotExist = driver.findElements(By.xpath("/html/body/div[5]/div/div")).size() == 0;
		assertTrue(doesNotExist == true);
	}

	@Then("I should see error notification that says {string}")
	public void i_should_see_error_notifcation_that_says(String string) throws InterruptedException{
		Thread.sleep(3000); // extra time to load
		WebElement result = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div[1]"));
		assertTrue(result.getAttribute("innerHTML").equals(string));
	}

	@Then("I should see notification that says {string}")
	public void i_should_see_notifcation_that_says(String string) throws InterruptedException{
		Thread.sleep(3000); // extra time to load
		WebElement result = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div[1]"));
		assertTrue(result.getAttribute("innerHTML").equals(string));
	}
	
	@Then("I should be logged out")
	public void i_should_be_logged_out() throws InterruptedException{
		Thread.sleep(3000); // extra time to load
		String result = driver.getCurrentUrl();
		assertEquals(ROOT_URL, result);	
		WebElement notif = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div[1]"));
		assertTrue(notif.getAttribute("innerHTML").equals("You've been logged out"));
	}

	@Given("I click the event name for the first event")
	public void i_click_the_event_name_for_the_first_event() throws InterruptedException {
		WebElement link = driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div/div[2]/div/div/ul/li[1]/div[1]/div/h4"));
		link.click();
		Thread.sleep(3000); 
	}

	@Then("I should see a popup with more info")
	public void i_should_see_a_popup_with_more_info() throws InterruptedException {
		Boolean exists = driver.findElements(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div/ul/li[1]/div[2]/div")).size() != 0;
		assertTrue(exists == true);
	}
	// inviter features
	@Then("I should see responses to the PGD")
	public void i_should_see_responses_to_the_pgd() throws InterruptedException {
		Boolean responseExists = driver.findElements(By.xpath("html/body/div/div/div/div[3]/div[1]/div/div/div/div/div/table/tbody/tr/td[2]")).size() != 0;
	    assertTrue(responseExists);
	}
	@Then("I should see system picked event")
	public void i_should_see_system_picked_event() throws InterruptedException {
		Boolean systemeventExists = driver.findElements(By.xpath("/html/body/div/div/div/div[3]/h4[4]")).size() != 0;
	    assertTrue(systemeventExists);
	}
	@Then("I should see events in the drop down")
	public void i_should_see_events_in_the_drop_down() throws InterruptedException {
		Boolean systemeventExists = driver.findElements(By.xpath("/html/body/div/div/div/div[3]/h4[4]")).size() != 0;
	    assertTrue(systemeventExists);
	}
	@Given("I click on submit")
	public void i_click_on_submit() throws InterruptedException {
		WebElement link = driver.findElement(By.xpath("/html/body/div/div/div/div[3]/button"));
		link.click();
		Thread.sleep(3000); 
	}
	@Given("I click on page")
	public void i_click_on_page() throws InterruptedException {
		WebElement link = driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div[1]/div/div/ul/li[2]/a"));
		link.click();
		Thread.sleep(3000); 
	}

	@Given("I am logged in as choco")
	public void i_am_logged_in_as_choco() throws InterruptedException {
		i_am_on_the_page("Login");
		i_type_in_username_textbox("choco");
		i_type_in_password_textbox("choco");
		i_press_the_button_Login();
	}

	@Given("I click on the first event")
	public void i_click_on_the_first_event() throws InterruptedException {
    	WebElement link = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div[2]"));
		link.click();
		Thread.sleep(3000); 
	}

	@Given("I click Save Draft button")
	public void i_click_save_draft_button() throws InterruptedException {
		WebElement link = driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/button[2]"));
		link.click();
		Thread.sleep(5000); 
	}

	@Then("the excitement should be 3")
	public void the_excitement_should_be_3() throws InterruptedException {
    	WebElement result = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[1]/div[1]/div/div/div/div/div/table/tbody/tr/td[3]"));
		assertTrue(result.getAttribute("innerHTML").equals("3"));
	}

	@Given("I click Submit button")
	public void i_click_submit_button() throws InterruptedException {
		WebElement link = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/button[1]"));
		link.click();
		Thread.sleep(5000); 
	}
	
	@Given("I am logged in as chorong")
	public void i_am_logged_in_as_chorong() throws InterruptedException {
		i_am_on_the_page("Login");
		i_type_in_username_textbox("chorong");
		i_type_in_password_textbox("chorong");
		i_press_the_button_Login();
	}

	@After()
	public void after() {
		driver.quit();
	}
}
