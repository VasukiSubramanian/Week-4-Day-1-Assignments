package week4.day1.assignments;

import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContactWindowHandling {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		// 1. Launch URL "http://leaftaps.com/opentaps/control/login"
		driver.get("http://leaftaps.com/opentaps/control/login");
		String title = driver.getTitle();
		System.out.println(title);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// 2. Enter UserName and Password Using Id Locator
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		// 3. Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();
		// 4. Click on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();
		// 5. Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();
		// 6. Click on Merge Contacts using Xpath Locator
		driver.findElement(By.linkText("Merge Contacts")).click();
		// 7. Click on Widget of From Contact
		driver.findElement(By.xpath("//img[@alt='Lookup']")).click();

		// Handle the new window
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new LinkedList<String>(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));
		// System.out.println(windowHandlesList.size());

		// 8. Click on First Resulting Contact
		driver.findElement(By.xpath("(//a[@class=\"linktext\"])")).click();

		// Get back to main window
		driver.switchTo().window(windowHandlesList.get(0));

		// 9. Click on Widget of To Contact
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();

		// Handle the new window
		Set<String> windowHandlesSet1 = driver.getWindowHandles();
		List<String> windowHandlesList1 = new LinkedList<String>(windowHandlesSet1);
		driver.switchTo().window(windowHandlesList1.get(1));

		// Thread.sleep(1000);

		// 10. Click on Second Resulting Contact
		driver.findElement(By.xpath("(//a[@class=\"linktext\"])[6]")).click();

		// Get back to main window
		driver.switchTo().window(windowHandlesList.get(0));

		// 11. Click on Merge button using Xpath Locator
		driver.findElement(By.linkText("Merge")).click();

		// 12. Accept the Alert
		driver.switchTo().alert().accept();

		// 13. Verify the title of the page
		System.out.println(driver.getTitle());

	}

}
