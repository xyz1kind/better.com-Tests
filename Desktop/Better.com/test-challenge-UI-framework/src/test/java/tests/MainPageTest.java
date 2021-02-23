package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.TweetPage;
import utils.Driver;

public class MainPageTest extends BaseTest {

    TweetPage tweetPage = new TweetPage();
    String EXPECTED_TITLE = "React App";

    @Test(description = "Launch The App")
    public void verifyUserCanLaunchTheApp() {
        extentTest = extentReports.createTest("Launch The App");
        Assert.assertTrue(Driver.get().getTitle().equals(EXPECTED_TITLE),
                "The app is not launched and title is not displayed");
    }

    @Test(description = "Display the latest tweets")
    public void verifyTheFeedDisplaysTheLatestTweets () throws InterruptedException {
        extentTest = extentReports.createTest("Create a car by reading test data from excel file");
        BasePage.waitForVisibilityOfElements(tweetPage.lstOfTweets);
        Assert.assertTrue(tweetPage.lstOfTweets.size() != 0, "Zero tweets are displayed");
    }
}