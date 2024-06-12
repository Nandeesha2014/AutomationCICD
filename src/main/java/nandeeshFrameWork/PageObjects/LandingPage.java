package nandeeshFrameWork.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nandeeshFrameWork.AbstractComponents.AbstractComponents;


public class LandingPage extends AbstractComponents{

	WebDriver driver;
	
		
		public LandingPage(WebDriver driver)
		{
			super(driver);
			//initialization
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
//		WebElement userEmail = driver.findElement(By.id("userEmail"));
		
		//PageFactory Design Pattern
		@FindBy(id = "userEmail")
		WebElement userEmail;
		
		@FindBy(id = "userPassword")
		WebElement passwordele;
		
		@FindBy(id = "login")
		WebElement submit;
		
		@FindBy(css = "[class*='flyInOut']")
		WebElement errorMessage;
		
		//action method
		public ProductCatalog loginApplication(String email, String password)
		{
			userEmail.sendKeys(email);
			passwordele.sendKeys(password);
			submit.click();
			ProductCatalog productCatalog = new ProductCatalog(driver);
			return productCatalog;
			
		}
		
		public String getErrorMessage()
		{
			waitForWebElementToAppear(errorMessage);
			return errorMessage.getText();
			
			
		}
		
		public void goTo()
		{
			driver.get("https://rahulshettyacademy.com/client/");
		}
	
	}


