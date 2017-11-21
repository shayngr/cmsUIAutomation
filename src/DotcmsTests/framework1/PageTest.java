package DotcmsTests.framework1;

import org.testng.Assert;
import org.testng.annotations.Test;


import DotcmsFramework.framework1.Page;
import DotcmsFramework.framework2.ContentList;
import DotcmsFramework.framework2.NewContent;
import DotcmsFramework.framework2.ContentType;


public class PageTest extends dotcmsBaseTest{

	public void CanAddPageThruSiteBrowser() throws InterruptedException{

		Assert.assertTrue(HomePage.isAt(), "---->Failed to Login<-----");	

		
		//Now create the page
		Page.addPageAsset("Page4").withTemplateNumber(1).showOnMenu(false).savePublish();
		ge
		Assert.assertTrue(Page.isInPreviewMode(), "---->Page is not in Preview Mode<-----");
		
	}
	
	@Test(dependsOnMethods = {"Init"})
	public void CanAddPageThruContentSearch() throws InterruptedException{
		
		//If I'm at the HomePage that means I passed the Login page
		Assert.assertTrue(HomePage.isAt(), "---->Failed to Login<-----");	
		Thread.sleep(2000);
		
		//Go Content Search> Select PageAsset> AddNewContent button> "Add/Edit Page Asset"
		NewContent.goTo(ContentType.PageAsset);		
		Thread.sleep(2000);
				
		//Now create the page
		Page.addPageAsset("Page5").withTemplateNumber(1).showOnMenu(true).savePublish();
		
		//If I'm at the HomePage that means the page was created successfully
		Assert.assertTrue(Page.isInPreviewMode(), "---->Page is not in Preview Mode<-----");
		
	}

	public void CanEditPageThruContentSearch() throws InterruptedException{
					
		//If I'm at the HomePage that means I passed the login page
		Assert.assertTrue(HomePage.isAt(), "---->Failed to Login<-----");	
		
		//Go to the Content Search
		ContentList.goTo(ContentType.PageAsset);

		ContentList.doesContentExist("Page1");
		
		Thread.sleep(2000);

		Assert.assertTrue(Page.isInEditMode(), "---->You are not in Add/Edit Page Asset<----");

	
	}

	public void Cleanup() throws InterruptedException{
		BrowserDriver.Close();
	}
	

}
