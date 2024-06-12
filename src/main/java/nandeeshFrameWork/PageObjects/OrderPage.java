package nandeeshFrameWork.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nandeeshFrameWork.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	
	WebDriver driver;
	
//	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean verifiyOrderDisplay(String productName)
	{
//		String productName = null;
		boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	}
