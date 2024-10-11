package pages;


public class HomePage extends BasePage {

    private String sectionLink ="//a[normalize-space()='%s' and @href]";
    private String categoryLink ="//a[normalize-space()='Categorías']";

    public HomePage(){
        super(driver);
    }
    
    public void navigateToWebPage() { 
        navigateTo("https://listado.mercadolibre.com.co/tiendas-online-bogota"); 
    }

    public void clickOnASection(String section){
        String xpathSection = String.format(sectionLink, section);
        clickElement(xpathSection);

    }

    public void clickCategorySection(){
        clickElement(categoryLink);
    }


    

};

