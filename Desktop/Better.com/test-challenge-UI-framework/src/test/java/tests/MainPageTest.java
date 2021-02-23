package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TweetPage;
import utils.Driver;

import static pages.BasePage.*;

public class MainPageTest extends BaseTest {

    TweetPage tweetPage = new TweetPage();

    String EXPECTED_TITLE = "React App";

    String RANDOM_USER;
    String FULL_NAME;
    String FIRST_NAME;
    String LAST_NAME;
    String USERNAME;

    String FIRST_DATE;
    String LAST_DATE;

    String CHAT_MESSAGE = "what's happening?";
    String CHARACTER_COUNT = "140";
    String SENT_MESSAGE;

    @Test(description = "Launch The App")
    public void verifyUserCanLaunchTheApp() {
        extentTest = extentReports.createTest("Launch The App");

        Assert.assertTrue(Driver.get().getTitle().equals(EXPECTED_TITLE),
                "The app is not launched and title is not displayed");
    }

    @Test(description = "Display the latest tweets")
    public void verifyTheFeedDisplaysTheLatestTweets() {
        extentTest = extentReports.createTest("Display the latest tweets");

        waitForVisibilityOfElements(tweetPage.lstOfTweets);
        int number = 4;
        waitForVisibilityOfElements(tweetPage.lstOfTweets);
        Assert.assertTrue(tweetPage.lstOfTweets.size() != 0, "Zero tweets are displayed");
        Assert.assertTrue(number == tweetPage.lstOfTweets.size(), "Number of tweets are not matching");
    }

    @Test(description = "Verify the list of users and tweets are matching")
    public void verifyTheFeedDisplaysTheListOfUsers() {
        extentTest = extentReports.createTest("Display the latest tweets and users");
        waitForVisibilityOfElements(tweetPage.lstOfTweets);
        Assert.assertTrue(tweetPage.lstOfTweets.size() == tweetPage.lstOfUsers.size(),
                "Number of tweets and users are not displayed");
    }

    @Test(description = "Every tweet has first name, last name and username")
    public void verifyEveryTweetHasFirstNameLastNameAndUsername() {
        extentTest = extentReports.createTest("Every tweet has first name, last name and username");

        waitForVisibilityOfElements(tweetPage.lstOfTweets);
        //I am using hardcore user here to verify user details
        RANDOM_USER = getText(tweetPage.lstOfUsers.get(0));

        FULL_NAME = RANDOM_USER.split("-")[0].trim();
        FIRST_NAME = FULL_NAME.split(" ")[0].trim();
        LAST_NAME = FULL_NAME.split(" ")[1].trim();
        USERNAME = RANDOM_USER.split("-")[1].trim();

        Assert.assertTrue(FULL_NAME.equals("Mary Meeker"), "Full name is not matching");
        Assert.assertTrue(FIRST_NAME.equals("Mary"), "First name is not matching");
        Assert.assertTrue(LAST_NAME.equals("Meeker"), "Last name is not matching");
        Assert.assertTrue(USERNAME.contains("@marymeeker"), "Username is not matching");
    }

    @Test(description = "Every tweet displays the date when the tweet was sent")
    public void The_Feed_Displays_The_Latest_Tweets() {
        extentTest = extentReports.createTest("Every tweet displays the date when the tweet was sent");

        waitForVisibilityOfElements(tweetPage.lstOfTweets);

        //I am using hardcore user details here to verify user details
        FIRST_DATE = getText(tweetPage.lstOfTweetDates.get(0)).split("\n")[1].trim();
        LAST_DATE = getText(tweetPage.lstOfTweetDates.get(3)).split("\n")[1].trim();
        Assert.assertTrue(FIRST_DATE.equals("8 years ago"), "Date is not matching");
        Assert.assertTrue(LAST_DATE.equals("4 years ago"), "Date is not matching");

        Integer NewestTweetDate = Integer.parseInt(FIRST_DATE.replaceAll("[^0-9]", ""));
        Integer OldestTweetDate = Integer.parseInt(LAST_DATE.replaceAll("[^0-9]", ""));
        Assert.assertTrue(OldestTweetDate > NewestTweetDate,
                "Tweets are not displayed correctly. Newest tweets should be on the top");
    }

    @Test(description = "Verify Profile Pics are displayed correctly")
    public void everyUserHasProfilePictureIsDisplayed() {
        extentTest = extentReports.createTest("Every user has profile picture is displayed");

        //I am using random user to see if they have a profile pic displayed
        WebElement img = pickRandomElement(tweetPage.lstOfProfilePics);
        Assert.assertTrue(img.isDisplayed(), "Profile Pic is not displayed");
    }


    @Test(description = "The chat input field has 'what's happening?' message displayed " +
            "and The chat input field only accepts '140' symbols" +
            "and after clicking the submit button tweet appears on the Feed")
    public void verifyUserIsAbleToSendTweets() {
        extentTest = extentReports.createTest("The chat input field has 'what's happening?' message displayed " +
                "and The chat input field only accepts '140' symbols" +
                "and after clicking the submit button tweet appears on the Feed");

        String CHAT_TEXT = tweetPage.chatInputField.getAttribute("placeholder").trim();
        Assert.assertTrue(CHAT_TEXT.equals(CHAT_MESSAGE), "Chat message is not displayed");

        SENT_MESSAGE = tweetPage.chatInputField.getAttribute("value");
        String chatMessageLength = String.valueOf(SENT_MESSAGE.length()).trim();
        Assert.assertTrue(CHARACTER_COUNT.equals(chatMessageLength), "Chat message is accepting more than 140 characters");

        click(tweetPage.chatSubmitButton);

        String actualMessage = getText(tweetPage.lstOfTweets.get(4));
        Assert.assertTrue(SENT_MESSAGE.equals(actualMessage), "Recently sent chat message is not displayed");
    }

}