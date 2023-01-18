package createAoservice.createAowebservice;

import static org.junit.Assert.assertEquals;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class VbmStepDefinitions {
	
	WebDriver driver = null;
	
	//Browser is open
	@Given("Browser is Open")
	public void getBroswer() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Automation\\API\\src\\test\\resources\\WebDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
	}
	//Loading of the page
	@Given("a valid loaded page")
	public void getLoadedPage()
	{

		String baseUrl = "https://www.saucedemo.com/";
		driver.get(baseUrl);
		driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	///Login
		@Given("a valid details submitted to login")
		
		public void Login(){
		
	        
	        driver.findElement(By.id("user-name")).sendKeys("standard_user");
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.findElement(By.id("password")).sendKeys("secret_sauce");
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.findElement(By.xpath("//input[@id='login-button']")).click();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		}

		
		
	@Given("a User adds item in the cart")
	public void Verify() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.name("checkout")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	@Given("a user enters details to clear cart")
	public void getClearcart()
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("first-name")).sendKeys("Frank");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("last-name")).sendKeys("Mhlongo");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.name("postalCode")).sendKeys("1320");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("continue")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("finish")).click();
	
	}
	@Then("a user validate text to complete the test")
	public void verifyText()
	{
		String expectedText = "CHECKOUT: COMPLETE!";
		WebElement actual =driver.findElement(By.xpath("//span[@class='title']"));
		String actualText = actual.getText();
		System.out.println("actual text is :"+actualText);
		if(actualText.contentEquals(expectedText))
		{
			System.out.println("Test Passed");
		}else
		{
			System.out.println("Test Failed");
		}
		driver.close();
	}
	
}