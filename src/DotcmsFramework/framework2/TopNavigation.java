package DotcmsFramework.framework2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import smokeTest1.BrowserDriver;
import framework1.MenuSelector;

public class TopNavigation {

	//public static final String SiteBrowser = null;
	
	
	public static class SiteBrowser{
				
		public static class AddNewPage{
			
			public static void select() throws InterruptedException{
				
				MenuSelector.select("Site Browser", "/html/body/div[3]/div[1]/div[1]/ul/li[2]/ul/li[1]/a");
				
				//Find and click the dropdown arrow
				WebElement dropDownArrow = BrowserDriver.getDriver().findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div[2]/div[3]/table/tbody/tr/td[2]/div[1]"));
				dropDownArrow.click();
				
				Thread.sleep(2000);
				
				//List the elements in the dropdown and click the 2nd element
				WebElement table = BrowserDriver.getDriver().findElement(By.cssSelector("table[id='dijit_Menu_1']"));
				WebElement tableRow = table.findElement(By.id("dijit_MenuItem_6"));
				List<WebElement> tableData = tableRow.findElements(By.cssSelector(("td")));
				tableData.get(1).click();	
				Thread.sleep(2000);
				
				//Click "Select" button to choose Page Asset
				BrowserDriver.getDriver().findElement(By.id("selectedPageAssetButton_label")).click();
				
				
			}
			
		}
		
	}
	
	
	public static class ContentSearch{
		
		public static class AddNewContent {
			
			public static class ContentGeneric{
				
				public static void select() throws InterruptedException{			
						
					//Go to Content Search
					//Locate element by matching EXACT text value
					MenuSelector.select("Content", "//a[.='Content Search']");					
					

					WebElement NewContentDropdownTableData = BrowserDriver.getDriver().findElement(By.cssSelector("td[id='dijit_form_ComboButton_0_arrow']"));
					NewContentDropdownTableData.findElement(By.xpath("//*[@class='dijitReset dijitArrowButtonInner']")).click();
					Thread.sleep(2000);
					
					WebElement NewContentMenuTableData = BrowserDriver.getDriver().findElement(By.cssSelector("td[id='dijit_MenuItem_2_text']"));
					NewContentMenuTableData.findElement(By.xpath("//td[contains(text(),'Add New Content')]")).click();
					
					WebElement NewContentsTable = BrowserDriver.getDriver().findElement(By.cssSelector("table[class='sTypeTable']"));
					/*In this case is better to skip the tags "tr", "td" and the List (that I usually use for finding elements inside tables) 
					because the list elements are separated in 3 different "td" columns,
					so it'll be more complicated to search for all the link texts in a for loop. So is better to go directly to the link text
					*/
					//List<WebElement> NewContentsTableData = NewContentsTableRow.findElements(By.cssSelector("td[class='sTypeTd']"));
					WebElement NewContentsTableData = NewContentsTable.findElement(By.linkText("Content (Generic)"));					
					Thread.sleep(2000);
					NewContentsTableData.click();
					
					}						
				
				}
			
			public static class PageAsset{
				
				public static void select() throws InterruptedException{					


					WebElement NewContentDropdownTableData = BrowserDriver.getDriver().findElement(By.cssSelector("td[id='dijit_form_ComboButton_0_arrow']"));
					NewContentDropdownTableData.findElement(By.xpath("//*[@class='dijitReset dijitArrowButtonInner']")).click();
					Thread.sleep(2000);
					
					WebElement NewContentMenuTableData = BrowserDriver.getDriver().findElement(By.cssSelector("td[id='dijit_MenuItem_2_text']"));
					NewContentMenuTableData.findElement(By.xpath("//td[contains(text(),'Add New Content')]")).click();
							
					
					}						
				
				}
			
			}
		
		public static class TypeAllContent{
			
			public static void select() throws InterruptedException{
				
				//Go to Content Search
				//Locate element by matching EXACT text value
				MenuSelector.select("Content", "//a[text()='Content Search']");	
				
			}
			
		}
		
		public static class TypeGenericContent{
			
			public static void select() throws InterruptedException{

				//See if Element "CONTAINS" specified text
				MenuSelector.select("Content", "//a[contains(text(), 'Content Search')]");

				//Clear default value "All" from Type field
				BrowserDriver.getDriver().findElement(By.cssSelector("input[id='structure_inode']")).clear();
							
				//Click on the dropdown arrow
				WebElement typeFieldOutterDiv = BrowserDriver.getDriver().findElement(By.id("widget_structure_inode"));
				//Find element by tagName "div"
				List<WebElement> typeFieldInnerDiv = typeFieldOutterDiv.findElements(By.tagName("div"));
				//Click the position 0 from the list
				typeFieldInnerDiv.get(0).click();							
				Thread.sleep(2000);
				
				//Click on Generic Content option that gets visible after clicking the dropdown arrow
				BrowserDriver.getDriver().findElement(By.id("structure_inode_popup5")).click();

			}
		}
		
		public static class TypeFileAsset{

			public static void select() throws InterruptedException {

				BrowserDriver.getDriver().findElement(By.cssSelector("input[id='structure_inode']")).clear();
				
				//Click on the dropdown arrow
				WebElement typeFieldOutterOptions = BrowserDriver.getDriver().findElement(By.id("widget_structure_inode"));
				List<WebElement> typeFieldInnerOptions = typeFieldOutterOptions.findElements(By.tagName("div"));
				typeFieldInnerOptions.get(0).click();							
				Thread.sleep(2000);
				
				//Click on File Asset option
				BrowserDriver.getDriver().findElement(By.id("structure_inode_popup29")).click();
				
			}
			
		}
		
		public static class TypePageAsset{
			
			public static void select() throws InterruptedException{					
				
				//Go to Content Search NOT using full Xpath
				//Locate element by matching EXACT text value
				MenuSelector.select("Content", "//a[.='Content Search']");				

				//Clear default value "All" from Type field
				BrowserDriver.getDriver().findElement(By.cssSelector("input[id='structure_inode']")).clear();

				WebElement typeFieldOutterOptions = BrowserDriver.getDriver().findElement(By.id("widget_structure_inode"));
				List<WebElement> typeFieldInnerOptions = typeFieldOutterOptions.findElements(By.tagName("div"));
				typeFieldInnerOptions.get(0).click();							
				Thread.sleep(2000);
				
				BrowserDriver.getDriver().findElement(By.id("structure_inode_popup31")).click();
				

				
			}

			
			
		}

	}				

}
