package DotcmsFramework.framework2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import smokeTest1.BrowserDriver;

public class ContentGenericPage {
	
	public static ContentGenericCommand addContentGeneric(String title){

		WebDriverWait wait = new WebDriverWait(BrowserDriver.getDriver(),7);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id='title']")));
		
		return new ContentGenericCommand(title);
	}
	


}
