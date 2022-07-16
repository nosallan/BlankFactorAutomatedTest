package pages.blankFactorHomePage;

import blankfactor.project.BaseClasses.BasePage;
import blankfactor.project.EnhancedDriver.Selenium;
import blankfactor.project.Reporting.ExtentTestManager;
import clientProperties.ClientProperties;
import pages.blankFactorBlogPage.BlankFactorBlogPage;

import java.util.List;
/**
 * Page to execute different methods inside the blank factor site
 */

import static pages.blankFactorHomePage.Bys.BlankFactorHomeBys.*;

public class BlankFactorHomePage extends BasePage {

    //Start the Selenium class extending the Base Page
    public BlankFactorHomePage(Selenium driver) {
        super(driver);
    }
    //close the cookie modal
    public boolean cookieModal() {
        driver().waitForLoad();
        if(driver().isElementDisplayed(COOKIE_MODAL_ID)){
            driver().click(ACCEPT_COOKIE_BUTTON);
            ExtentTestManager.logInfo("Cookies Modal","Cookies has been accepted");
            if(!driver().isElementDisplayed(COOKIE_MODAL_ID))
                ExtentTestManager.logInfo("Cookies Modal","Cookies modal is not present more after accept cookies");
            else{
                ExtentTestManager.logError("Cookies Modal","Cookies modal is present after accept the cookies, " +
                        "probably exist and issue when try to save the cooke");
                ExtentTestManager.logAssertFail();
                return false;
            }
        }
        else{
            ExtentTestManager.logInfo("Cookies Modal","Cookies modal is not displayed");
        }
        return true;
    }

    public BlankFactorBlogPage openBlogPage(){
        driver().clickOver(HEADER_INSIGHTS,HEADER_INSIGHTS_BLOG);
        return new BlankFactorBlogPage(driver());
    }






}
