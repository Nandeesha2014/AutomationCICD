package nandeeshFrameWork.SeleniumFrameWorkDesign.TestComponents;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import nandeeshFrameWork.PageObjects.CartPage;
import nandeeshFrameWork.PageObjects.CheckoutPage;
import nandeeshFrameWork.PageObjects.ConfirmationPage;
import nandeeshFrameWork.PageObjects.LandingPage;
import nandeeshFrameWork.PageObjects.OrderPage;
import nandeeshFrameWork.PageObjects.ProductCatalog;


//	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
/*		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize(); */
//		driver.get("https://rahulshettyacademy.com/client/");
// 		driver.findElement(By.id("userEmail")).sendKeys("synny@email.com");
//		driver.findElement(By.id("userPassword")).sendKeys("Sunnysun@1");
//		driver.findElement(By.id("login")).click();

/*		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginApplication("synny@email.com", "Sunnysun@1");
		ProductCatalog productCataloge = landingPage.loginApplication("synny@email.com", "Sunnysun@1"); */
		
		
//		List<WebElement> products = driver.findElements(By.cssSelector("col-lg-4"));
//		ProductCatalog productCataloge = new ProductCatalog(driver);
	/*	List<WebElement> products = productCataloge.getProductList();*/
		
		
//		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b"))
//					.getText().equals(productName)).findFirst().orElse(null);
//		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	/*	productCataloge.addProductToCart(productName);
		CartPage cartPage = productCataloge.goToCartPage();*/
		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
//		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	/*	productCataloge.goToCartPage();*/
				
//		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
//		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
//		Assert.assertTrue(match);
//		driver.findElement(By.cssSelector(".totalRow button")).click();
//		CartPage cartpage = new CartPage(driver);
		/*	boolean match = cartPage.verifiyProductDisplay(productName);
		Assert.assertTrue(match); */
//		cartPage.goToCheckout();
				
//		Actions a = new Actions(driver);
//		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	/*	CheckoutPage checkoutpage = cartPage.goToCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();*/

	/*	String confirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();*/


public class TestFrameWork extends BaseTest {
	String productName = "ZARA COAT 3";
	@Test(dataProvider ="getData", groups= {"Purchase"}) 
	public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException {
		
		
		//move to base test
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		driver.manage().window().maximize();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		LandingPage landingPage = new LandingPage(driver);
//		landingPage.goTo();
		
//		LandingPage landingPage = launchApplication();
//		ProductCatalog productCataloge = landingPage.loginApplication("synny@email.com", "Sunnysun@1");
		ProductCatalog productCataloge = landingPage.loginApplication(email, password);
		
		List<WebElement> products = productCataloge.getProductList();
		productCataloge.addProductToCart(productName);
		
		CartPage cartPage = productCataloge.goToCartPage();
		boolean match = cartPage.verifiyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartPage.goToCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		String confirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//		driver.close();
		
		}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() {
	//Verify ZARA COAT 3 is displayed in orders page 
		ProductCatalog productCataloge = landingPage.loginApplication("synny@email.com", "Sunnysun@1");
		OrderPage orderspage =	productCataloge.goToOrderPage();
		Assert.assertTrue(orderspage.verifiyOrderDisplay(productName));
		
	}
	
	@DataProvider()
	public Object[][] getData()
	{
		return new Object[] [] {{"synny@email.com", "Sunnysun@1", "ZARA COAT 3"} , {"nand@email.com", "nand@1234", "ADIDAS ORIGINAL"}};
	}


}
