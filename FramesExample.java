package week4.day1.assignments;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesExample {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Frame 1
		driver.switchTo().frame("frame1");
		driver.findElement(By.xpath("//input[@type=\"text\"]")).sendKeys("Not a Friendly Topic");

		// Inner Frame of Frame 1 (Frame 3)
		driver.switchTo().frame("frame3");
		driver.findElement(By.id("a")).click();

		// Switch to Main Frame
		driver.switchTo().defaultContent();

		// Frame 2
		driver.switchTo().frame("frame2");
		WebElement drop = driver.findElement(By.id("animals"));
		Select select = new Select(drop);
		select.selectByValue("babycat");
	}

}
