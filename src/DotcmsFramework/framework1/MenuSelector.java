package DotcmsFramework.framework1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import smokeTest1.BrowserDriver;

public class MenuSelector {
	
	public static void select(String topMenuPartialLinkText, String subMenuXpath) throws InterruptedException{
		
		WebElement topMenu = BrowserDriver.getDriver().findElement(By.partialLinkText(topMenuPartialLinkText));
		
		WebElement subMenu = BrowserDriver.getDriver().findElement(By.xpath(subMenuXpath));
		
		Actions actions = new Actions(BrowserDriver.getDriver());
		
		actions.moveToElement(topMenu).perform();
		
		Thread.sleep(2000);
		
		actions.click(subMenu).perform();
		
		Thread.sleep(2000);

	}
	
	
	

}
