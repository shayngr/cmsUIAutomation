package DotcmsFramework.framework1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import smokeTest1.BrowserDriver;
import smokeTest3.PageCommand;
import smokeTest3.EditPageCommand;

public class Page {

	
	public static PageCommand addPageAsset(String title) {
		//Explicit wait for 5 sec until you see Title field
		WebDriverWait wait = new WebDriverWait(BrowserDriver.getDriver(), 7);	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id='titleBox']")));
		
		return new PageCommand(title);
	}

	public static boolean isInPreviewMode() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(BrowserDriver.getDriver(), 7);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("iframe[id='frameMenu']")));

		BrowserDriver.getDriver().switchTo().frame("frameMenu");
		WebElement AdminScreenButton = BrowserDriver.getDriver().findElement(By.cssSelector("span[id='dijit_form_Button_0']"));
		if (AdminScreenButton != null){
			Thread.sleep(2000);
			AdminScreenButton.click();
			return true;
			
		}
		
		return false;
		
	}
	
	public static boolean isInEditMode(){
		
		if (BrowserDriver.getDriver().findElement(By.xpath("//div[contains(.,'Add/Edit Page Asset')]")) != null){
			WebElement title = BrowserDriver.getDriver().findElement(By.cssSelector("input[id='titleBox']"));
			title.clear();
			title.sendKeys("THIS IS WORKING!!");		
			
			return true;
		}
			
			return false;
				
	}

}
