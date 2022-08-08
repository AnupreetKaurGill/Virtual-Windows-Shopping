package locators;

import java.time.Duration;
import java.util.ArrayList;


import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CeraVe {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		
		//NAVIGATE TO "AMAZON" AND FIND PRICE OF CERAVE MOISTURIZING LOTION
		
				driver.navigate().to("https://www.amazon.ca/");
				WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));      //twotabsearchtextbox      nav-bb-search
				searchBox.sendKeys("cerave moisturizing cream");
				searchBox.submit();				
				driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[3]/div/div/div/div/div[3]/div[1]/h2/a")).click();
				WebElement priceAmazon = driver.findElement(By.xpath("//*[@id=\"corePriceDisplay_desktop_feature_div\"]/div[1]/span[1]/span[1]"));
				String amazonPrice = priceAmazon.getAttribute("innerHTML");
				System.out.println("Price of Cerave from Amazon is " + amazonPrice);
				
				
		
		
		//NAVIGATE TO "HEALTHSNAP" AND FIND PRICE OF CERAVE MOISTURIZING LOTION
		
		driver.navigate().to("https://healthsnap.ca/");
		WebElement searchBoxHealthSnap = driver.findElement(By.id("search_box"));
		searchBoxHealthSnap.sendKeys("cerave moisturizing cream");
		searchBoxHealthSnap.submit();
		WebElement priceHealthSnap = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[3]/ul/li[4]/div[2]/div[2]/span"));
		String healthSnapPrice = priceHealthSnap.getAttribute("innerHTML");
		System.out.println("Price of Cerave from HealthSnap is " + healthSnapPrice);
		
		Thread.sleep(3000);
		
		driver.close();
		
		// Comparison between Amazon price and HealthSnap price
		
		String amazonPrice1 = amazonPrice.replace("$", "");
        String healthSnapPrice1 = healthSnapPrice.replace("$", "");
	
		double amazonPriceDouble = Double.parseDouble(amazonPrice1);
		double healthSnapPriceDouble = Double.parseDouble(healthSnapPrice1); 
		
		ArrayList<Double> prices = new ArrayList<>();
		
		prices.add(amazonPriceDouble);
		prices.add(healthSnapPriceDouble);
		
		Collections.sort(prices);

		double cheapPrice = prices.get(0);
		
		
		if (amazonPriceDouble == healthSnapPriceDouble) {
			System.out.println("Price of Cerave is similar at Amazon and HealthSnap which is $ " + amazonPriceDouble+ ". As a result, "
					+ "you can buy either from Amazon or HealthSnap." );
		}
		else if(cheapPrice == healthSnapPriceDouble){
			System.out.println("Price of Cerave from HealthSnap is $ " + healthSnapPriceDouble + " and it is cheaper than Amazon price which is $ "+amazonPriceDouble
					+ ". Therefore, you should buy from HealthSnap.");
		}
		else if(cheapPrice == amazonPriceDouble) {
			System.out.println("Price of Cerave from Amazon is $ " + amazonPriceDouble + " and it is cheaper than HealthSnap price which is $ "+healthSnapPriceDouble
					+ ". Therefore, you should buy from Amazon.");
		}
			
	}

}
