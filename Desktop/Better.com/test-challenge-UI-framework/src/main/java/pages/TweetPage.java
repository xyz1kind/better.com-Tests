package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;

public class TweetPage {

    @FindBy(css="div[class='chat-post-message']")
    public List<WebElement> lstOfTweets;

    @FindBy(css="div[class='chat-meta-user']")
    public List<WebElement> lstOfUsers;

    @FindBy(css="div[class='chat-meta-timeAgo']")
    public static List<WebElement> lstOfTweetDates;

    @FindBy(css="div[class='chat-meta-timeAgo']")
    public List<WebElement> lstOfProfilePics;

    @FindBy(css="input[id='chatInput']")
    public WebElement chatInputField;

    @FindBy(css="button[id='chatSubmit']")
    public WebElement chatSubmitButton;

    public TweetPage() {
        PageFactory.initElements(Driver.get(), this);
    }
}
