package steps;

import io.cucumber.java.en.*;
import pages.HomePage;

public class HomePageSteps {

    HomePage homePageLanding = new HomePage();
    
    @Given("I am on the Mercado Libre homepage")
    public void openingTheWebPage(){
        homePageLanding.navigateToWebPage();
    }

    @When("I navigate to {string} using the navigation bar")
    public void navigationToSection(String section){
        homePageLanding.clickOnASection(section);
    }

    @When("the user opens the Categorias section")
    public void openCategory(){
        homePageLanding.clickCategorySection();
    }


    
}
