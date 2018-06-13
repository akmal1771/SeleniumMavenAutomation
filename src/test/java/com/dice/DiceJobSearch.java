package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) {
// after this no nees system.setProperties stuff	
		WebDriverManager.chromedriver().setup();
		//invoke selenium webdriver
		WebDriver driver=new ChromeDriver();
		//fullscreen
		driver.manage().window().fullscreen();
		//set universal wait time in case page is slow
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		String url="https://dice.com";
		driver.get(url);
		String actualTitle=driver.getTitle();
		String expectedTitle="Job Search for Technology Professionals | Dice.com";
		
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Pass.you are good to go bro");
		}else {
			System.out.println("Fail.you messed up bro");
			throw new RuntimeException("Step Fialed");
		}
		
		String keyword="Java developer";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);

		String location="90020";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		
		driver.findElement(By.id("findTechJobs")).click();
		
		String count=driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		
		int countResult=Integer.parseInt(count.replace(",",""));
		if(countResult>0) {
			System.out.println("Keyword:"+keyword+"search returned"+ countResult+
					"result in "+location);
		}
		else {
			System.out.println("Step Failed");
		}
		driver.close();
		
	}

}
