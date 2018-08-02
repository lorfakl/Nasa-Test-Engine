package stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.java.nasaTestSuite.FrigiDriver;

public class StepDef_RACFanSpeed {
	//FrigiDriver frigi = new FrigiDriver();
	
	@Given("^App is updatedB$")
	public void app_is_updated() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Given("^RAC Control Panel is open$")
	public void rac_Control_Panel_is_open() throws Exception {
		//need to add "given app signed in updated 
		//System.out.println("result: " + frigi.checkScreen());
	}

	@When("^Right Fan Speed button is pressed$")
	public void right_Fan_Speed_button_is_pressed() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("^Eventually RAC will be set to high$")
	public void eventually_RAC_will_be_set_to_high() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("got to the end of rac stepdef");
	}
}
