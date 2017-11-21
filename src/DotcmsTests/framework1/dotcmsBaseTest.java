package DotcmsTests.framework1;

import org.testng.annotations.Test;

import smokeTest1.BrowserDriver;
import smokeTest1.LoginPage;

public class dotcmsBaseTest {
	
	@Test
	public void Init() throws InterruptedException{
		BrowserDriver.Initialize();
		LoginPage.goTo();		
		LoginPage.loginAs("admin@dotcms.com").WithPassword("admin").Login();
	}

}
