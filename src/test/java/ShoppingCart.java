import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    WebDriver driver;

    @BeforeMethod
    public void setProp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        //Open Zip code page
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        //Input 5 digits zip;
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        //Click the "Continue"
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        //Input data into fields
        driver.findElement(By.name("first_name")).sendKeys("Art");
        driver.findElement(By.name("email")).sendKeys("adf@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String userEmail = driver.findElement(By.xpath("//td/b")).getText();
        driver.findElement(By.xpath("//p/a")).click();
        driver.findElement(By.name("email")).sendKeys(userEmail);
        driver.findElement(By.name("password")).sendKeys("1111");
        driver.findElement(By.xpath("//input[@type='submit' and @value='Login']")).click();
    }

    @Test
    public void addingTheBookToShoppingCartTest() throws InterruptedException {
        Thread.sleep(3000);
        //Click on the name of the book
        driver.findElement(By.xpath("(//table[@align='center']//tbody//tr[@align='center']/td/a)[2]")).click();
        //Add to Shopping Cart
        driver.findElement(By.xpath("//p/a/img")).click();
        String actualSuccessMessage = driver.findElement(By.xpath("//span[@class='confirmation_message']")).getText();
        String expectedSuccessMessage = "Book was added to the Shopping Cart";
        Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);
        driver.quit();
    }

    @Test
    public void addingDifferentBooksToShoppingCartTest() throws InterruptedException {
        Thread.sleep(3000);
        List<String> listOfAddingBookNames = new ArrayList<>();
        List<String> listOfBookNamesInShoppingCart = new ArrayList<>();
        List<WebElement> listOfBooksCatalog = driver.findElements(By.xpath(".//table[@align='center']//tbody//tr[@align='center']/td/a"));
        for (int i = 0; i < listOfBooksCatalog.size(); i++) {
            if (i == 0) {
                //Click on the name of the book
                driver.findElement(By.xpath("(//table[@align='center']//tbody//tr[@align='center']/td/a)[2]")).click();
            } else {
                //Click on the name of the book
                driver.findElement(By.xpath("(//table[@align='center']//tbody//tr[@align='center']/td/a)[1]")).click();
            }
            //Save book name
            listOfAddingBookNames.add(driver.findElement(By.xpath("//td/p[2]")).getText());
            //Add to Shopping Cart
            driver.findElement(By.xpath("//p/a/img")).click();
            //Click on logo ShareLane
            driver.findElement(By.xpath("//tr/td/a [@href='./main.py']/img")).click();
        }

        //Click on Shopping Cart
        driver.findElement(By.xpath("//td[@ align='right']/a/img")).click();
        String booksInShoppingCart = driver.findElement(By.xpath("//td/table/tbody/tr[2]/td[2]")).getText();
        for (int l = 0; l <= listOfAddingBookNames.size(); l++) {
            if (!listOfBookNamesInShoppingCart.contains(booksInShoppingCart)) {
                listOfBookNamesInShoppingCart.add(booksInShoppingCart);
            }
        }
        int numberOfMatches = 0;
        for (String listOfAddingBookName : listOfAddingBookNames) {
            if (listOfBookNamesInShoppingCart.contains(listOfAddingBookName)) {
                numberOfMatches++;
            }
        }
        boolean areListsTheSame = numberOfMatches == listOfAddingBookNames.size();
        Assert.assertTrue(areListsTheSame, "All selected books haven't been added");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}