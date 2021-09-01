package week4.day1.assignments;

import java.util.concurrent.TimeUnit;
import java.util.Set;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNowFrames {

	public static void main(String[] args) throws IOException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		// Step1: Load ServiceNow application URL given above
		driver.get("https://dev113545.service-now.com/");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Switch to frame
		driver.switchTo().frame("gsft_main");

		// Step2: Enter username as admin
		driver.findElement(By.id("user_name")).sendKeys("admin");

		// Step3: Enter password as w6hnF2FRhwLC
		driver.findElement(By.id("user_password")).sendKeys("w6hnF2FRhwLC");

		// Step4: Click Login
		driver.findElement(By.id("sysverb_login")).click();

		// Step5: Search “incident “ Filter Navigator
		driver.findElement(By.id("filter")).sendKeys("incident");

		// Step6: Click “All”
		driver.findElement(By.xpath("(//div[text()=\"All\"])[2]")).click();

		// Thread.sleep(3000);
		// driver.switchTo().parentFrame();
		driver.switchTo().frame("gsft_main");

		// Step7: Click New button
		driver.findElement(By.id("sysverb_new")).click();

		// Step8: Select a value for Caller and Enter value for short_description
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		Set<String> winHandsSet = driver.getWindowHandles();
		List<String> winHandsList = new ArrayList<String>(winHandsSet);
		// System.out.println(winHandsList.size());
		driver.switchTo().window(winHandsList.get(1));
		driver.findElement(By.xpath("//a[@class=\"glide_ref_item_link\"]")).click();
		driver.switchTo().window(winHandsList.get(0));
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//input[@id=\"incident.short_description\"]")).sendKeys("P1 Issue");

		// Step9: Read the incident number and save it a variable
		String incidentNumber = driver.findElement(By.id("sys_original.incident.number")).getAttribute("value");
		System.out.println(incidentNumber);

		// Step10: Click on Submit button
		driver.findElement(By.id("sysverb_insert")).click();

		// Step 11: Search the same incident number in the next search screen as below
		driver.findElement(By.xpath("//input[@placeholder=\"Search\"]")).sendKeys(incidentNumber, Keys.ENTER);

		// Step12: Verify the incident is created successful and take snapshot of the
		// created incident.
		String incidentVerification = driver.findElement(By.xpath("//a[@class=\"linked formlink\"]")).getText();
		if (incidentVerification.equals(incidentNumber))
			System.out.println("Incident " + incidentNumber + " created successfully");
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./snaps/item2.png");
		FileUtils.copyFile(source, destination);

	}

}
