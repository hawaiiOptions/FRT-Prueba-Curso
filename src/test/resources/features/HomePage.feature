@HomePage
Feature: Home Page Navigation
    In order to explore the elements of the Mercado Libre home page
    As a client, registered user, or anonymous user
    I want to interact with the elements on the page

    Background: Accessing the Mercado Libre homepage via URL
        Given I am on the Mercado Libre homepage

    @NavigationBar
    Scenario Outline: The user can click different sections
        When I navigate to "<section>" using the navigation bar
        Examples:
            | section        |
            | Ofertas        |
            | Cupones        |
            | Moda           |
            | Vender         |
            | Ayuda / PQR    |
            | Crea tu cuenta |
            | Ingresa        |
            | Mis compras    |

@NavigationBar @Category
Scenario: Categories are displayed correctly 
    When the user opens the Categorias section
    Then the user can see all the available categories
    And  
