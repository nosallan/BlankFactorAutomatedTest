package pages.blankFactorBlogPage;

import blankfactor.project.BaseClasses.BasePage;
import blankfactor.project.DriverUtilities.Utilities;
import blankfactor.project.EnhancedDriver.Selenium;
import blankfactor.project.Reporting.ExtentTestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static pages.blankFactorBlogPage.Bys.BlankFactorBlogBys.*;

public class BlankFactorBlogPage extends BasePage {

    //Start the Selenium class extending the Base Page
    public BlankFactorBlogPage(Selenium driver) {
        super(driver);
    }
    //close the cookie modal

    public void openAllPosts(){
        boolean morePosts=driver().isElementDisplayed(LOAD_MORE_BUTTON);
        while (morePosts){
            morePosts=driver().isElementDisplayed(LOAD_MORE_BUTTON);
            if(morePosts){
                driver().click(LOAD_MORE_BUTTON);
                while( driver().getElement(LOAD_MORE_BUTTON).getAttribute("class").contains("wait disable"))
                    driver().waitFor(1);
            }
        }
    }
    public boolean searchPost(String post){
        openAllPosts();
        boolean founded=driver().isElementDisplayed(By.linkText(post));
        if(founded){
            ExtentTestManager.logInfo("Blog notice status","Notice: "+post+" founded!");
            driver().click(BLOG_NOTICE);
        }
        else{
            ExtentTestManager.logError("Blog notice status","Automation can not found the blog: "+post);
            ExtentTestManager.logAssertFail();
            return false;
        }
        return true;
    }
    public boolean validateURL(){
        driver().waitForLoad();
        return driver().getCurrentPage().contains("blankfactor.com/insights/blog");
    }

    public boolean postVerify(String urlOfPost, String postTitle, String author ){
        driver().waitForLoad();
        if(urlOfPost.contains(driver().getCurrentPage()))
            ExtentTestManager.logInfo("Notice url","Current notice URL was verify: "+ driver().getCurrentPage());
        else{
            ExtentTestManager.logError("Notice url","Current notice URL: "+ driver().getCurrentPage() +" is not equals to provided: "
                    +urlOfPost);
            ExtentTestManager.logAssertFail();
            return false;
        }
        if(driver().getTitle().contains(postTitle))
            ExtentTestManager.logInfo("Notice url","Current notice title was verify: "+ driver().getCurrentPage());
        else{
            ExtentTestManager.logError("Notice url","Current notice title: "+ driver().getTitle() +" not contains the title: "
                    +postTitle);
            ExtentTestManager.logAssertFail();
            return false;
        }
        if(driver().getElement(POST_AUTHOR).getText().equals(author))
            ExtentTestManager.logInfo("Notice url","Notice author was verify: "+ author);
        else{
            ExtentTestManager.logError("Notice url","Author: "+ author
                    +" of the post is not according to provided");
            ExtentTestManager.logAssertFail();
            return false;
        }
        return true;
    }

    public boolean subscribeNewsletter(){
        driver().sendKeys(SUBSCRIBE_EMAIL,"test-allan"+ Utilities.getRandomNumber(10,1000)+"@gmail.com");
        driver().click(SUBSCRIBE_BUTTON);
        while( driver().getElement(SUBSCRIBE_BUTTON).getAttribute("class").contains("disable wait"))
            driver().waitFor(2);
        if(driver().isElementDisplayed(SUBSCRIBE_SUCCESS))
            ExtentTestManager.logInfo("Subscribe status","User: has been subscribed");
        else{
            ExtentTestManager.logInfo("Subscribe status","User can not be subscribed");
            return false;
        }
        return true;
    }
    public boolean printAllPosts(){
        openAllPosts();
        List<WebElement> listOfPosts=driver().getElements(ALL_POSTS);
        int numPost=1;
        if(listOfPosts.isEmpty()){
            ExtentTestManager.logError("All posts","We can not print all the posts");
            ExtentTestManager.logAssertFail();
            return false;
        }
        else{
            ExtentTestManager.logInfo("Number of posts found",Integer.toString(listOfPosts.size()));
            for(WebElement e : listOfPosts) {
                ExtentTestManager.logInfo("Title of the post #"+numPost,e.findElement(By.tagName("h2")).getText());
                numPost++;
            }
        }
        return true;
    }





}
