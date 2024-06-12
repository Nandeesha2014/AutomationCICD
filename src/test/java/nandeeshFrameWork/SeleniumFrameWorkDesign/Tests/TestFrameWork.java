package nandeeshFrameWork.SeleniumFrameWorkDesign.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.module.Input;
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
import nandeeshFrameWork.SeleniumFrameWorkDesign.TestComponents.BaseTest;


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
//	public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException {
		public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {	
		
		
		//move to base test
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		driver.manage().window().maximize();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		LandingPage landingPage = new LandingPage(driver);
//		landingPage.goTo();
		
//		LandingPage landingPage = launchApplication();
		ProductCatalog productCataloge = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCataloge.getProductList();
		productCataloge.addProductToCart(input.get("product"));
		CartPage cartPage = productCataloge.goToCartPage();
		
		boolean match = cartPage.verifiyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartPage.goToCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		String confirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//		driver.close();
		
		}
	
	
	
	
	//Extent Reports
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() {
	//Verify ZARA COAT 3 is displayed in orders page 
		ProductCatalog productCataloge = landingPage.loginApplication("synny@email.com", "Sunnysun@1");
		OrderPage orderspage =	productCataloge.goToOrderPage();
		Assert.assertTrue(orderspage.verifiyOrderDisplay(productName));
		
	}
	
	
	@DataProvider()
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data =  getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//nandeeshFrameWork//SeleniumFrameWorkDesign//Data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	

	
	
//	@DataProvider()
//	public Object[][] getData() throws IOException
//	{
//		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "synny@email.com");
//		map.put("password", "Sunnysun@1");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "nand@email.com");
//		map1.put("password", "Nand@1234");
//		map1.put("product", "ADIDAS ORIGINAL");
//		
//		List<HashMap<String, String>> data =  getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java\nandeeshFrameWork//SeleniumFrameWorkDesign//Data//PurchaseOrder.json");
		//		below will return the string
		//		return new Object[][] {{map},{map1}};
		//   below will return the json
//		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		
		
	
	
	
//	@DataProvider()
//	public Object[][] getData()
//	{
//		return new Object[] [] {{"synny@email.com", "Sunnysun@1", "ZARA COAT 3"} , {"nand@email.com", "Nand@1234", "ADIDAS ORIGINAL"}};
//	}


}

