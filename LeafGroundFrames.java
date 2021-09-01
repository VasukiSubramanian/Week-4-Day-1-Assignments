package week4.day1.assignments;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafGroundFrames {

	public static void main(String[] args) throws IOException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// 1.Take the the screenshot of the click me button of first frame
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@src=\"default.html\"]"));
		driver.switchTo().frame(frame1);
		WebElement button = driver.findElement(By.id("Click"));
		File source = button.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snaps/item3.png");
		FileUtils.copyFile(source, dest);

		// 2.Find the number of frames
		driver.switchTo().defaultContent();
		List<WebElement> framesList = driver.findElements(By.tagName("iframe"));
		System.out.println("Number of Frames in the page : " + framesList.size());
	}

}
