package test;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenEMR {

@Test(priority=1)
//(enable=false) if we dont want it to execute only other test cases
//if we WANT TO EXECUTE TEST CASE IN A PARTICULAR ORDER PRIORITY = 1/2/3/4/ CAN BE GIVEN.
//test cases will be executed in the order given otherwise it will execute in alphabet order
public void createPatient() throws InterruptedException
{
	
WebDriverManager.chromedriver().setup();

WebDriver d = new ChromeDriver();
WebDriverWait wait=new WebDriverWait(d,Duration.ofSeconds(30));

Actions act=new Actions(d);

d.manage().window().maximize();
d.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//WebDriverWait wait=new WebDriverWait(d,Duration.ofSeconds(30));
d.get("https://demo.openemr.io/a/openemr");

d.findElement(By.id("authUser")).sendKeys("admin");
d.findElement(By.id("clearPass")).sendKeys("pass");
d.findElement(By.xpath("//*[@id=\"login_form\"]/div[1]/div[1]/div[4]/button")).click();
////*[@id="login_form"]/div[1]/div[1]/div[4]
////*[@id="login_form"]/div[1]/div[1]/div[4]/button

act.moveToElement(d.findElement(By.xpath("//*[@id=\"mainMenu\"]/div/div[5]/div/div"))).perform();
d.findElement(By.xpath("//*[@id=\"mainMenu\"]/div/div[5]/div/ul/li[2]/div")).click();

d.switchTo().frame(d.findElement(By.xpath("//*[@id=\"framesDisplay\"]/div[3]/iframe")));

Select c1=new Select(d.findElement(By.id("form_title")));
c1.selectByIndex(2);

d.findElement(By.name("form_fname")).sendKeys("anu");
d.findElement(By.name("form_lname")).sendKeys("Bhu");

d.findElement(By.name("form_DOB")).click();
//Thread.sleep(3000);
//d.findElement(By.className("xdsoft_today")).click();
//if we user class by default first occurence will be selected,class name is not unique

d.findElement(By.name("form_DOB")).sendKeys("2022-01-12");
d.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/table/tbody/tr[3]/td[4]"));
Thread.sleep(5000);

Select c2=new Select(d.findElement(By.name("form_sex")));
c2.selectByIndex(1);

d.findElement(By.name("form_ss")).sendKeys("787878");

d.findElement(By.name("form_drivers_license")).sendKeys("121212");

Select c3 = new Select(d.findElement(By.name("form_status")));
c3.selectByValue("Mrs.");

d.findElement(By.name("create")).click();

d.switchTo().defaultContent();

d.switchTo().frame(d.findElement(By.xpath("//*[@id=\"modalframe\"]")));
Thread.sleep(3000);
d.findElement(By.xpath("//*[@id=\"searchResultsHeader\"]/center/input")).click();

d.switchTo().defaultContent();
Thread.sleep(5000);
wait.until(ExpectedConditions.alertIsPresent());
d.switchTo().alert().accept();

Thread.sleep(4000);

//d.switchTo().defaultContent();

//Thread.sleep(4000);

//d.switchTo().alert();
//wait.until(ExpectedConditions.alertIsPresent());
d.findElement(By.xpath("//*[@id=\"bdayreminder\"]/div/div/div[1]")).click();

d.switchTo().defaultContent();

Thread.sleep(4000);

Actions a1 = new Actions(d);
a1.moveToElement(d.findElement(By.id("username"))).perform();
d.findElement(By.xpath("//*[@id=\"userdropdown\"]/li[4]")).click();
}

//@Test(priority=5,invocationCount=5,dependsOnMethods="physcianRgister")
//invocationCount tell the test should be executed 5 times
//depends/on/methods tells the test can be run only if physician test id=s executed first
//public void physcianLogin()
//{
//}

}

