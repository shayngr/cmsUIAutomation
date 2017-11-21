package building.out.framework2;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.dotcms.master36.smokeTest1.BrowserDriver;
import com.dotcms.master36.smokeTest1.HomePage;
import com.dotcms.master36.smokeTest1.LoginPage;

import building.out.framework1.dotcmsBaseTest;
import master36.building.out.framework1.Page;
import master36.building.out.framework2.ContentGenericPage;
import master36.building.out.framework2.ContentType;
import master36.building.out.framework2.ContentTypeCreator;
import master36.building.out.framework2.ContentList;
import master36.building.out.framework2.NewContent;

public class ContentSearchTests extends dotcmsBaseTest{
	
	//@Test(dependsOnMethods = {"Init"})
	public void canAddPage() throws InterruptedException{
		
		//If I'm at the HomePage that means I passed the login page
		Assert.assertTrue(HomePage.isAt(), "---->Failed to Login<-----");		
		
		/*
		 * ContentType.PageAsset is a way to control the GoTo to make it go just to
		the PageAsset directly so we can use this as a general purpose class that 
		will work for all types of content
		*/
		NewContent.goTo(ContentType.PageAsset);		
		Thread.sleep(2000);		
		
		Page.addPageAsset("Page4").withTemplateNumber(1).showOnMenu(true).savePublish();		
		Thread.sleep(2000);
		
		//If I'm at the HomePage that means the page was created successfully
		Assert.assertTrue(Page.isInPreviewMode(), "---->Page is not in Preview Mode<-----");
		
	}
	
	//@Test (dependsOnMethods = {"Init"})
	public void showUpPageAssets() throws InterruptedException{
		
		//Go to Content Search
		//Click Type dropdown and select File Asset
		ContentList.goTo(ContentType.PageAsset);;
		Thread.sleep(2000);
		
		//Store Contents Count
		ContentList.storeContentsCount();		
		//The console message below was moved to ContentSearch.StoreFileAssetsCount();
		//System.out.println("----> El numero actual de assets es: "+ ContentSearch.getFileAssetsCount());
		
	}
	
	//@Test (dependsOnMethods = {"Init"})
	public void showUpGenericContents() throws InterruptedException{
		
		//Go to Content Search
		//Click Type dropdown and select File Asset
		ContentList.goTo(ContentType.ContentGeneric);
		Thread.sleep(2000);
		
		//Store Contents Count
		ContentList.storeContentsCount();		
		//The console message below was moved to ContentSearch.StoreFileAssetsCount();
		//System.out.println("----> El numero actual de assets es: "+ ContentSearch.getFileAssetsCount());
		
	}
	
	public void showUpAddedGenericContents() throws InterruptedException{
		
		ContentList.goTo(ContentType.ContentGeneric);
		ContentList.storeContentsCount();
		ContentGenericPage.addContentGeneric("Test generic content 2").withBody("test gc 2").withLanguage(0).savePublish();		
		Assert.assertTrue(ContentList.isAt(), "---->Failed to Login<-----");
		Assert.assertEquals(ContentList.previousContentGenericCount() + 1, ContentList.currentContentGenericCount(), "--->Count of Content Generic<---");	
		
	}
	
	//@Test (dependsOnMethods = {"Init"})
	public void canAddContentGeneric() throws InterruptedException{
		//Need to implement that if I'm at the Content Search, don't need to go there again
		
		//Go Content Search> Select PageAsset> AddNewContent button> "Add/Edit Page Asset"
		NewContent.goTo(ContentType.ContentGeneric);
		ContentGenericPage.addContentGeneric("Content Generic 1").withBody("This is a Content Generic Test 1").withLanguage(0).savePublish();		
		Thread.sleep(2000);
		
		//If I'm at the Content Search that means I successfully created/edited the generic content
		Assert.assertTrue(ContentList.isAt(), "---->Failed to Login<-----");
		
		//ContentGenericPage.addContentGeneric("Content Generic 2").withBody("This is a Content Generic Test 2").withLanguage(0).savePublish();		
		Thread.sleep(2000);
		
		//If I'm at the Content Search that means I successfully created/edited the generic content
		Assert.assertTrue(ContentList.isAt(), "---->Failed to Login<-----");
		
	}
	
	@Test (dependsOnMethods = {"Init"})
	public void canDeleteGenericContents() throws InterruptedException{
		//Go to Content Search
		ContentList.goTo(ContentType.ContentGeneric);

		//Type the content generic in the search field
		//Find the content generic in the list
		Assert.assertTrue(ContentList.doesContentExist("Content Generic 2"), "---->Content does NOT exist<-----");	
		
		//Click to select the content generic
		//Hit Unpublish, Archive, Delete or by doing right click
		ContentList.unpublishContent("Content Generic 2");
		Thread.sleep(2000);
		ContentList.archiveContent("Content Generic 2");
		Thread.sleep(2000);
		ContentList.deleteContent("Content Generic 2");
		
	}
		
	//@Test
	public void canEditFolder() throws InterruptedException{
		
		LoginPage.goTo();		
		LoginPage.loginAs("admin@dotcms.com").WithPassword("admin").Login();		
		
		//Assert will tell me if I'm at the Home page
		Assert.assertTrue(HomePage.isAt(), "---->Failed to Login<-----");	
	
	}


	//@Test
	public void cleanup() throws InterruptedException{
		Thread.sleep(10000);
		BrowserDriver.Close();
	}


}

