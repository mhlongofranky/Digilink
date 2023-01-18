package createAoservice.createAowebservice;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class uitesting {


    public static void main(String[] args) {
        // declaration and instantiation of objects/variables
    	System.setProperty("webdriver.chrome.driver","C:\\Users\\frankm\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
    	
        String baseUrl = "http://demo.guru99.com/test/newtours/";
        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = "";

        
        driver.get(baseUrl);

        
        actualTitle = driver.getTitle();

        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }
       
        
        driver.close();
    }

}