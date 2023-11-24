//: https://testpages.herokuapp.com/styled/tag/dynamic-table.html

package My_SDET_Caw;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.validator.PublicClassValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.github.bonigarcia.wdm.online.Parser;

public class dynamic_Table
{
	public static WebDriver cDriver;
    private static String readJsonFileAsString(String filePath) throws IOException 
    {
    	// create object of object mapper class
        ObjectMapper objectMapper = new ObjectMapper();
        // return file content value as string
        return objectMapper.writeValueAsString(objectMapper.readTree(Paths.get(filePath).toFile()));
    }
	@Test
	public  void main() throws InterruptedException, FileNotFoundException, IOException, ParseException {
//Lunch Chrome Browser
		cDriver = new ChromeDriver();
//Get the URL
		cDriver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
		
//Maximize the browser
		cDriver.manage().window().maximize();
		Thread.sleep(4000);
// click on Table Data Button
		cDriver.findElement(By.xpath("//*[text()='Table Data']")).click();
		Thread.sleep(5000);

// Click on refresh button
	    cDriver.findElement(By.xpath("//*[@id='jsondata']")).clear();
	    Thread.sleep(5000);
//Path of the file
	    String filePath = "D:\\data.json";
	   
	    String jsonContent = readJsonFileAsString(filePath);
    
	  //  String jsonText = "[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, {\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, {\"name\":\"Sara\", \"age\" : 42, \"gender\": \"female\"}, {\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, {\"name\": \"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]";
	 //System.out.println(jsonContent);
	 //System.out.println(jsonText);
	   Thread.sleep(5000);
	   cDriver.findElement(By.xpath("//*[@id='jsondata']")).sendKeys(jsonContent);
	   Thread.sleep(5000);
//click on refresh button
	   cDriver.findElement(By.xpath("//*[text()='Refresh Table']")).click();
	
       String storedData = getStoredData();
       System.out.println(storedData);
       String uiTableData = getUITableData(cDriver);
       
 //Assert.assertEquals(storedData, uiTableData);
	
}
	@AfterClass
    public void tearDown() throws InterruptedException 
	{
	  Thread.sleep(5000);
	  cDriver.quit();
	
}
	
	  private String getUITableData(WebDriver cDriver) 
	   {
		  System.out.println("inside Table ----");
		  //fetch table from UI
		  WebElement table = cDriver.findElement(By.xpath("//*[@id='dynamictable']"));
		  System.out.println("Table is ----"+table);
		  //fetch table rows from table
		  List<WebElement> rows = table.findElements(By.tagName("tr"));
		  System.out.println(rows);
		  //iterate rows and get data 
		  for (WebElement row : rows) 
		  {
	            List<WebElement> cells = row.findElements(By.tagName("td"));
	            for (WebElement cell : cells) 
	            {
	                String cellText = cell.getText();
	                System.out.print(cellText + "\t");
                }     
		  }
	            System.out.println();
		  return null;
	}
	  
	private String getStoredData() throws IOException 
	{
		
		String filePath = "D:\\data.json";
		String jsonContent = readJsonFileAsString(filePath);
		return jsonContent;
	 
	}

}

