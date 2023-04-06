import java.io.IOException;
import java.time.Duration;
import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import graphql.Assert;

public class FileUpload {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		String DownloadPath= System.getProperty("user.dir");

		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
		//for deafult downloading in project structure
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
	    chromePrefs.put("profile.default_content_settings.popups", 0);
	    chromePrefs.put("download.default_directory", DownloadPath);

		ChromeOptions chromeoption = new ChromeOptions();
		chromeoption.addArguments("--remote-allow-origins=*");
		//downloading file in project itself
		chromeoption.setExperimentalOption("prefs",chromePrefs );

		WebDriver driver = new ChromeDriver(chromeoption);
		
		//uploading file
		driver.get("https://www.ilovepdf.com/jpg_to_pdf");
		driver.findElement(By.cssSelector("[id='pickfiles']")).click();
		Thread.sleep(3000L);
		//Running .exe file by using java
		Runtime.getRuntime().exec("E:\\My documents\\fileupload.exe");
		
		//downloading file
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("processTask")));
		driver.findElement(By.id("processTask")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pickfiles")));
		driver.findElement(By.id("pickfiles")).click();
		Thread.sleep(5000L);
		//Checking of file
		File f= new File(DownloadPath+"/ankit_photu.pdf");
		if(f.exists()) {
			Assert.assertTrue(f.exists());
			if(f.delete()) 
				System.out.println("File is deleted");
			
		}
		
		
		
		
	}
	

}
