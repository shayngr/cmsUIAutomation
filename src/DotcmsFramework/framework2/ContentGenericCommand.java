package DotcmsFramework.framework2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import smokeTest1.BrowserDriver;

public class ContentGenericCommand {
	
	String title;
	String body;
	int language;
	
	public ContentGenericCommand (String title){
		this.title = title;
	}
	
	public ContentGenericCommand withBody (String body){
		this.body = body;
		return this;
	}
	
	public ContentGenericCommand withLanguage(int language){
		this.language = language;
		return this;
	}
	
	public void savePublish() throws InterruptedException{
		
		BrowserDriver.getDriver().findElement(By.cssSelector("input[id='title']")).sendKeys(title);
		Thread.sleep(2000);

		BrowserDriver.getDriver().switchTo().frame("body_ifr");
		BrowserDriver.getDriver().switchTo().activeElement().sendKeys(body);
		BrowserDriver.getDriver().switchTo().defaultContent();
		Thread.sleep(2000);
		WebElement languageOutterDiv = BrowserDriver.getDriver().findElement(By.id("widget_langcombo"));
		List<WebElement> languageInnerDiv = languageOutterDiv.findElements(By.tagName("div"));
		languageInnerDiv.get(0).click();
		Thread.sleep(2000);
		

		BrowserDriver.getDriver().findElement(By.id("langcombo_popup0")).click();
		Thread.sleep(2000);

		BrowserDriver.getDriver().findElement(By.partialLinkText("Publish")).click();
		
	}

}
