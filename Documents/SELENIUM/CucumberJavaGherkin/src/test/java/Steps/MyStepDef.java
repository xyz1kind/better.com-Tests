package Steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.ArrayList;
import java.util.List;

public class MyStepDef {
    @Given("^I navigate to login page$")
    public void iNavigateToLoginPage() throws Throwable {
        System.out.println("I navigate to login page");

    }

    @And("^I click login button$")
    public void iClickLoginButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^I should see the userform page$")
    public void iShouldSeeTheUserformPage() throws Throwable {
        // Write code here that turns the phrase above into concrete action
    }


    @And("^I enter the following details$")
    public void iEnterTheFollowingDetails(DataTable table) throws Throwable {

        //List<List<String>> data = table.raw();

     /*   System.out.println("The value is " + data.get(0).get(0).toString());
        System.out.println("The value is " + data.get(0).get(1).toString());*/


     //Create an ArrayList
     List<User> users = new ArrayList<User>();

     //Store all the users
        users = table.asList(User.class);

        for (User user: users) {

            System.out.println("The email is " + user.email);
            System.out.println("The password " + user.password);

        }

    }

    public class User
    {
        private String email;
        private String password;


        public User(String eMail, String passWord) {
            email = eMail;
            password = passWord;
        }
    }
}
