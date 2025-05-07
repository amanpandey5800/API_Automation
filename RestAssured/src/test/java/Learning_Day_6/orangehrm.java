package Learning_Day_6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class orangehrm {

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub

		WebDriver dr=new ChromeDriver();
		dr.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
		dr.findElement(By.xpath("ul[@class='oxd-main-menu']//li[1]")).click();
		dr.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[3]")).click();
	}

}
