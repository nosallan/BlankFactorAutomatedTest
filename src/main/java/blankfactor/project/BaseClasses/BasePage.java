package blankfactor.project.BaseClasses;

import blankfactor.project.EnhancedDriver.Selenium;
//base configuration for all the pages
public class BasePage {

    private Selenium driverReference;

    public BasePage(Selenium driver) {
        this.driverReference = driver;
        driverReference.initElements(this);
    }

    public Selenium driver() {
        return driverReference;
    }
}
