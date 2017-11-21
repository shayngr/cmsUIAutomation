package DotcmsFramework.framework2;

public class NewContent {
	
	public static void goTo(ContentType content) throws InterruptedException{
		
		switch(content){		
		case ContentGeneric:
			TopNavigation.ContentSearch.AddNewContent.ContentGeneric.select();	
			break;
			
		case PageAsset:
			TopNavigation.ContentSearch.AddNewContent.PageAsset.select();	
			break;
			
		case FileAsset:
		
	}
		
	}

}
