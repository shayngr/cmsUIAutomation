package DotcmsFramework.framework2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import smokeTest1.BrowserDriver;
import selenium.webdriven.commands.GetAttribute;

public class ContentList {
	
	private static int lastCount;
	public static List<WebElement> innerContentTd, contentTr;
	
	public static void goTo(ContentType content) throws InterruptedException { 

		switch (content) {
		case AllContent:
			TopNavigation.ContentSearch.TypeAllContent.select();
			break;	
			
		case ContentGeneric: 
			TopNavigation.ContentSearch.TypeGenericContent.select();
			break;
			
		case FileAsset:
			TopNavigation.ContentSearch.TypeFileAsset.select();
			break;
			
		case PageAsset: 
			TopNavigation.ContentSearch.TypePageAsset.select();
			break;
			
		default:
			break;
		}
		
		
		
	}
	
	//Type the content generic in the search field
	public static boolean doesContentExist(String contentName) throws InterruptedException {
		
		//Clear default value "All" from Type field
		BrowserDriver.getDriver().findElement(By.cssSelector("input[id='allFieldTB']")).clear();

		//tag with attribute value ---> Evernote Notebook: WebDriver Element Locations: Note #6 CSS Selectors(3) 
		BrowserDriver.getDriver().findElement(By.cssSelector("input[id='allFieldTB']")).sendKeys(contentName);
		//Thread.sleep(2000);

		//Expand Advanced menu
		BrowserDriver.getDriver().findElement(By.xpath("//div[contains(text(),'Advanced')]")).click();
		Thread.sleep(2000);
		
		//---> Show all languages
		WebElement languageDropdown = BrowserDriver.getDriver().findElement(By.id("widget_language_id"));

		//tag which has attribute ---> Evernote Notebook: WebDriver Element Locators. Note #6: CSS Selectors(3) 
		List<WebElement> languageValues = languageDropdown.findElements(By.cssSelector("div[class]"));
		languageValues.get(0).click();
		Thread.sleep(2000);

		//Click on "All" languages option which gets visible after clicking the dropdown arrow
		BrowserDriver.getDriver().findElement(By.id("language_id_popup0")).click();
		Thread.sleep(2000);
		//-----
		
		//--> Click on Search dropdown arrow
		WebElement searchButtonTable = BrowserDriver.getDriver().findElement(By.cssSelector("table[id='searchButton']"));
		//Find element by CSS Selector "td"
		List<WebElement> searchButtonTableData = searchButtonTable.findElements(By.cssSelector("td"));		
		//Click the element #2 from the list
		searchButtonTableData.get(1).click();
		Thread.sleep(2000);

		//Click on "Search" option that gets visible after clicking the dropdown arrow
		BrowserDriver.getDriver().findElement(By.id("dijit_MenuItem_0")).click();

		WebElement contentTable = BrowserDriver.getDriver().findElement(By.cssSelector("table[id='results_table']"));


		List<WebElement> contentTableRow = contentTable.findElements(By.cssSelector("tr[id]"));
		for(int i = 0; i < contentTableRow.size(); i++){
			System.out.println("**************");
			System.out.println("# de fila (tr): " + i);
			System.out.println("**************");

					List<WebElement> contentTableData = contentTableRow.get(i).findElements(By.cssSelector("td[style], td[nowrap], td[class]"));

			System.out.println("Cantidad de columnas (contentTableData.size): " + contentTableData.size());
			System.out.println();

			for(int j = 0; j < contentTableData.size(); j++){
				System.out.println("# de columna (td): " + j);
				System.out.println();

				//Iterate thru each "td" and check to see if any td.getText is equals to contentName
				if(contentTableData.get(j).getText().equals(contentName)){
					System.out.println(contentName + " se encuentra en la fila: " + i + " - columna: " + j);
					System.out.println();				
					return true;
				}

			}
								
		}

		return false;
				
						
	}
	

	public static void unpublishContent(String contentName) throws InterruptedException{

		WebElement contentTable = BrowserDriver.getDriver().findElement(By.cssSelector("table[id='results_table']"));

		//Create a List of "tr"s
		//tag which has attribute ---> Evernote Notebook: WebDriver Element Locators. Note #6: CSS Selectors(3)
		List<WebElement> contentTableRow = contentTable.findElements(By.cssSelector("tr[id]"));
		for(int i = 0; i < contentTableRow.size(); i++){

			//Iterate thru each "tr" and create a List of "td"s
			//Find only td elements which css selector is style, nowrap and class
			//The ',' operator is the CSS selector 'or' operator. --> http://stackoverflow.com/questions/16089492/selenium-webdriver-w-java-locating-elements-with-multiple-class-names-with-one
			List<WebElement> contentTableData = contentTableRow.get(i).findElements(By.cssSelector("td[style], td[nowrap], td[class]"));

			for(int j = 0; j < 3; j++){ //reduced iteration to only 3 to skip the other columns

				//---> Need this string to do a comparison
				String spanClassName;
				
				/*I was using --> contentTableData.get(0).findElement(By.className("liveIcon")) 
				instead of 
				findElement(By.xpath("//span[@class='liveIcon']"))
				The first line of code did NOT WORK properly because it was not finding the span className, but the td className which doesn't exist
				*/
				
				//show me what's the span className
				System.out.println("classname: " + contentTableData.get(0).findElement(By.xpath("//span[@class='liveIcon']")).getAttribute("class"));	
				
				//Assign to spanClassName the result getting from above
				spanClassName = contentTableData.get(0).findElement(By.xpath("//span[@class='liveIcon']")).getAttribute("class");
				//Thanks to the line of code above, I can know what's the status of each content(green, yellow, red) 
				//without having to create a List of span elements on index 0 such as before
				
				//Show me if I'm getting what I expect
				System.out.println("span className: "  + spanClassName);
				System.out.println();
				
				//Iterate thru each "td" and check to see if any td.getText is equals to contentName
				//AND if span className is equals to "liveIcon"
				if((contentTableData.get(j).getText().equals(contentName)) 
						&& (spanClassName.equals("liveIcon"))){
				//-----					
					System.out.println("icono en Verde!!!");		
					System.out.println();

					contentTableData.get(2).findElement(By.xpath("//input[contains(@id,\"checkbox"+ i +"\")]")).click();
					Thread.sleep(2000);

					BrowserDriver.getDriver().findElement(By.xpath("//span[@widgetid='unPublishButton']")).click();
					BrowserDriver.getDriver().findElement(By.cssSelector("span[widgetid='unPublishButton']")).click();
					


				}					

			}

		}

	}

	public static void archiveContent (String contentName) throws InterruptedException {

		WebElement contentTable = BrowserDriver.getDriver().findElement(By.cssSelector("table[id='results_table']"));


		List<WebElement> contentTableRow = contentTable.findElements(By.cssSelector("tr[id]"));
		for(int i = 0; i < contentTableRow.size(); i++){


			List<WebElement> contentTableData = contentTableRow.get(i).findElements(By.cssSelector("td[style], td[nowrap], td[class]"));

			for(int j = 0; j < 3; j++){ //reduced iteration to only 3 to skip the other columns

				//---> Need this string to do a comparison
				String spanClassName;


				//show me what's the span className
				System.out.println("classname: " + contentTableData.get(0).findElement(By.xpath("//span[@class='workingIcon']")).getAttribute("class"));	

				//Assign to spanClassName the result below
				spanClassName = contentTableData.get(0).findElement(By.xpath("//span[@class='workingIcon']")).getAttribute("class");
		 			 
				//Show me if I'm getting what I expect
				System.out.println("span className: "  + spanClassName);
				System.out.println();

				//Iterate thru each "td" and check to see if any td.getText is equals to contentName
				//AND if span className is equals to "liveIcon"
				if((contentTableData.get(j).getText().equals(contentName)) 
						&& (spanClassName.equals("workingIcon"))){
				//-----
					
					System.out.println("icono en Amarillo!!!");		
					System.out.println();

					//Concatenate attribute text (checkbox) with array position (i)
					contentTableData.get(2).findElement(By.xpath("//input[contains(@id,\"checkbox"+ i +"\")]")).click();
					Thread.sleep(2000);

					//tag with attribute value ---> Evernote Notebook: WebDriver Element Locators. Note #7: Xpath(2) 
					BrowserDriver.getDriver().findElement(By.xpath("//span[@widgetid='archiveButton']")).click();

				}					

			}

		}

	}
	
	public static void deleteContent(String contentName) throws InterruptedException {
		
		
		WebElement showingSelectDropdown = BrowserDriver.getDriver().findElement(By.id("widget_showingSelect"));
		
		//tag which has attribute ---> Evernote Notebook: WebDriver Element Locators. Note #6: CSS Selectors(3) 
		List<WebElement> showingSelectValues = showingSelectDropdown.findElements(By.cssSelector("div[class]"));
		showingSelectValues.get(0).click();
		Thread.sleep(2000);

		//Click on "Archived" option which gets visible after clicking the dropdown arrow
		BrowserDriver.getDriver().findElement(By.id("showingSelect_popup3")).click();
		Thread.sleep(2000);

		WebElement contentTable = BrowserDriver.getDriver().findElement(By.cssSelector("table[id='results_table']"));
		
		//tag which has attribute ---> Evernote Notebook: WebDriver Element Locators. Note #6: CSS Selectors(3) 
		List<WebElement> contentTableRow = contentTable.findElements(By.cssSelector("tr[id]"));
		for(int i = 0; i < contentTableRow.size(); i++){
			List<WebElement> contentTableData = contentTableRow.get(i).findElements(By.cssSelector("td[style], td[nowrap], td[class]"));

			for(int j = 0; j < 3; j++){

				if(contentTableData.get(j).getText().equals(contentName)){				
					contentTableData.get(2).findElement(By.xpath("//input[contains(@id,\"checkbox"+ i +"\")]")).click();
					Thread.sleep(2000);
					
					//tag with attribute value ---> Evernote Notebook: WebDriver Element Locators. Note #7: Xpath(2) 
					BrowserDriver.getDriver().findElement(By.xpath("//span[@id='deleteButton']")).click();
					
				}
				
			}
			
		}			
		
	}
					

	public static void storeContentsCount() {
		
		lastCount = getFileAssetsCount();
		System.out.println("----> El numero actual de contenidos es: "+ lastCount);
		System.out.println();
		
	}
	
	public static int getFileAssetsCount(){

		WebElement countText = BrowserDriver.getDriver().findElement(By.xpath("//*[@class='yui-u first']")); 
				String [] countFileAssets = countText.getText().split(" ");
		return Integer.valueOf(countFileAssets[3]);
	}

	
	public static int previousContentGenericCount(){
		
		return lastCount;
		
	}
	
	public static int currentContentGenericCount(){
		
		return getFileAssetsCount();
	}
	
	public static boolean isAt(){
		WebDriverWait wait = new WebDriverWait(BrowserDriver.getDriver(), 7);
		//Locate element by matching EXACT text value 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Content Search']")));
		
		if(BrowserDriver.getDriver().findElement(By.xpath("//span[text()='Content Search']")) != null){
			return true;
		}
		return false;
	}

		

}
