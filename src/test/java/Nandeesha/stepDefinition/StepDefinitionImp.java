package Nandeesha.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nandeeshFrameWork.PageObjects.CartPage;
import nandeeshFrameWork.PageObjects.CheckoutPage;
import nandeeshFrameWork.PageObjects.ConfirmationPage;
import nandeeshFrameWork.PageObjects.LandingPage;
import nandeeshFrameWork.PageObjects.ProductCatalog;
import nandeeshFrameWork.SeleniumFrameWorkDesign.TestComponents.BaseTest;

public class StepDefinitionImp extends BaseTest {

		public LandingPage landingPage;
		public ProductCatalog productCataloge;
		public ConfirmationPage confirmationPage;
	  @Given("I landed on Ecommerce page")
	  public void I_landed_on_Ecommerce_page() throws IOException
	  {
		  landingPage = launchApplication();
	  }
	
	  @Given("^Logged in with username (.+) and password (.+)$")
	  public void Logged_in_with_username_and_password(String name, String password)
	  {
		  productCataloge = landingPage.loginApplication(name , password);  
	  }
	  
	  @When("^I add product (.+) to cart$")
	  public void I_add_product_to_cart(String productName) throws InterruptedException
	  {
		  List<WebElement> products = productCataloge.getProductList();
			productCataloge.addProductToCart(productName);
	  }
	  
	  @When("^Checkout (.+) and submit the order$")
	  public void Checkout_and_submit_the_order(String productName) 
	  {
		  	CartPage cartPage = productCataloge.goToCartPage();
			boolean match = cartPage.verifiyProductDisplay(productName);
			Assert.assertTrue(match);
			CheckoutPage checkoutpage = cartPage.goToCheckout();
			checkoutpage.selectCountry("india");
			confirmationPage = checkoutpage.submitOrder();
	  }
	  
	  @Then("{string} message is displayed on Confirmation Page")
	  public void message_displayed_confirmationPage(String string)
	  {
		  String confirmMessage = confirmationPage.getConfirmationMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase(string)); 
			driver.close();
	  }
	  
	  @Then("^\"([^\"]*)\" message is displayed$")
	  public void something_message_displayed(String strArg1) throws Throwable
	  {
		Assert.assertEquals(strArg1 , landingPage.getErrorMessage());	
		driver.close();
	  }
	  
}
