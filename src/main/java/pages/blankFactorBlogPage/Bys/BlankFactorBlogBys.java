package pages.blankFactorBlogPage.Bys;

import org.openqa.selenium.By;

/**
 * Locator for the blank factor page
 */
public class BlankFactorBlogBys {

    public static final By BLOG_NOTICE=By.xpath("//a[contains(text(),'Why Fintech in Latin America Is Having a Boom')]");
    public static final By LOAD_MORE_BUTTON=By.xpath("//body/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/button[1]");
    public static final By SUBSCRIBE_EMAIL=By.xpath("//body/div[1]/main[1]/div[1]/div[1]/div[2]/aside[1]/div[1]/div[1]/div[3]/form[1]/div[1]/label[1]/input[1]");
    public static final By SUBSCRIBE_BUTTON=By.id("form-newsletter-blog-submit-btn");
    public static final By SUBSCRIBE_SUCCESS=By.xpath("//div[contains(text(),'Thank you for subscribing! Stay tuned.')]");
    public static final By ALL_POSTS=By.className("post-template__info");
    public static final By POST_AUTHOR=By.xpath("//body/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/a[1]");
}
