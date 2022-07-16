package tests.BlogSection;


import clientProperties.ClientProperties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ExecutionBaseTestCase;
import pages.blankFactorBlogPage.BlankFactorBlogPage;
import pages.blankFactorHomePage.BlankFactorHomePage;

import static pages.blankFactorHomePage.Bys.BlankFactorHomeBys.COOKIE_MODAL_ID;

/**
 * Class to validate the information for an specific post on the blog,
 * subscribe to the newsletter and display all the posts inside the blog page
 */
public class BlogSection   extends ExecutionBaseTestCase {

    public BlogSection(){
    }

    @BeforeMethod
    public void beforeMethod(){
        //Close the modal
        BlankFactorHomePage reportPage=new BlankFactorHomePage(driver);
        //Check if the modal is not displayed
        reportPage.cookieModal();
    }

    @Test(priority=1,description = "Open the 'blog' section, scroll to a specific post and click it")
    public void openBlogTest()  {
        //Access to the blog page
        BlankFactorHomePage reportPage=new BlankFactorHomePage(driver);
        BlankFactorBlogPage blogPage= reportPage.openBlogPage();
        //Searching and open the post: Why Fintech in Latin America Is Having a Boom
        assertTrue(blogPage.searchPost(ClientProperties.POST_TO_SEARCH));
    }

    @Test(priority=2,description = "Open a specific post and validate the content: title, URL and author")
    public void validatePostContentTest() {
        //Access to the blog page
        BlankFactorHomePage reportPage=new BlankFactorHomePage(driver);
        BlankFactorBlogPage blogPage= reportPage.openBlogPage();
        //Searching and open the post: Why Fintech in Latin America Is Having a Boom
        //Check the content of the post validating the URL and the title of the blog
        assertTrue(blogPage.searchPost(ClientProperties.POST_TO_SEARCH) &&
                blogPage.postVerify(ClientProperties.POST_URL, ClientProperties.POST_TO_SEARCH,
                        ClientProperties.POST_AUTHOR));
    }

    @Test(priority=3,description = "Subscribe to the newsletter using the subscribe form.")
    public void subscribeToNewsletterTest() {
        //Access to the blog page
        BlankFactorHomePage reportPage=new BlankFactorHomePage(driver);
        BlankFactorBlogPage blogPage= reportPage.openBlogPage();
        //Subscribe to the newsletter
        assertTrue(blogPage.subscribeNewsletter());
    }

    @Test(priority=4,description = "Go to the blog section and print a list of all post titles.")
    public void printAllPostsOnTheBlogTest()  {
        //Access to the blog page
        BlankFactorHomePage reportPage=new BlankFactorHomePage(driver);
        BlankFactorBlogPage blogPage= reportPage.openBlogPage();
        //Print all the posts in the blog page
        assertTrue(blogPage.printAllPosts());
    }
}
