import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest {

    @Test
    public void sendFiveDigitsToZipCodeFieldTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        //Open Zip code page
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        //Input 5 digits zip;
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        //Click the "Continue"
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        //Check that 'Register' button is shown
        boolean isRegisterButtonDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        driver.quit();
        Assert.assertTrue(isRegisterButtonDisplayed, "Register button isn't shown");
    }

    @Test
    public void sendFourDigitsToZipCodeTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        try {
            //Open Zip code page
            driver.get("https://www.sharelane.com/cgi-bin/register.py");
            //Input 4 digits zip;
            driver.findElement(By.name("zip_code")).sendKeys("12345");
            //Click the "Continue"
            driver.findElement(By.cssSelector("[value=Continue]")).click();
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            //Check error message is shown
            boolean isErrorMessageShown = driver.findElement(By.className("error_message")).isDisplayed();
            driver.quit();
            Assert.assertTrue(isErrorMessageShown, "Error message isn't shown");

        }
    }

    @Test
    public void sendSignUpFormTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
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
        boolean isSuccessfulMessageShown = driver.findElement(By.className("confirmation_message")).isDisplayed();
        driver.quit();
        Assert.assertTrue(isSuccessfulMessageShown, "Success message isn't shown");
    }

    @Test
    public void sendMoreThanFiveDigitsToZipCodeTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        try {
            //Open Zip code page
            driver.get("https://www.sharelane.com/cgi-bin/register.py");
            //Input 6 digits zip;
            driver.findElement(By.name("zip_code")).sendKeys("123456");
            //Click the "Continue"
            driver.findElement(By.cssSelector("[value=Continue]")).click();
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            //Check error message is shown
            boolean isErrorMessageShown = driver.findElement(By.className("error_message")).isDisplayed();
            Assert.fail();
            driver.quit();
            Assert.assertTrue(isErrorMessageShown, "Error message isn't shown");
        }
    }

    @Test
    public void sendRepeatedPassword() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        //Open Zip code page
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        //Input 5 digits zip;
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        //Click the "Continue"
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        //Input data into fields
        driver.findElement(By.name("first_name")).sendKeys("Art");
        driver.findElement(By.name("email")).sendKeys("adf@mail.ru");
        //Fill in the field "Password*" with numbers, the password must have at least 4 characters
        driver.findElement(By.name("password1")).sendKeys("12345");
        //Fill in the field "Confirm Password*" with numbers other than those entered in the field "Password*"
        driver.findElement(By.name("password2")).sendKeys("1234567");
        //Click 'Register' button
        driver.findElement(By.cssSelector("[value=Register]")).click();
        boolean isSuccessfulMessageShown = driver.findElement(By.className("confirmation_message")).isDisplayed();
        driver.quit();
        Assert.assertTrue(isSuccessfulMessageShown, "Success message isn't shown");
    }
    @Test
    public void sendFirstNameWithDifferentLetters() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        //Open Zip code page
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        //Input 5 digits zip;
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        //Click the "Continue"
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        //Input data into fields
        driver.findElement(By.name("first_name")).sendKeys("Artф");
        driver.findElement(By.name("email")).sendKeys("adf@mail.ru");
        //Fill in the field "Password*" with numbers, the password must have at least 4 characters
        driver.findElement(By.name("password1")).sendKeys("12345");
        //Fill in the field "Confirm Password*" with numbers other than those entered in the field "Password*"
        driver.findElement(By.name("password2")).sendKeys("12345");
        //Click 'Register' button
        driver.findElement(By.cssSelector("[value=Register]")).click();
        boolean isSuccessfulMessageShown = driver.findElement(By.className("error_message")).isDisplayed();
        driver.quit();
        Assert.assertTrue(isSuccessfulMessageShown, "Error message isn't shown");
    }
    @Test
    public void sendEmailWithInvalidCharacters() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        //Open Zip code page
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        //Input 5 digits zip;
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        //Click the "Continue"
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        //Input data into fields
        driver.findElement(By.name("first_name")).sendKeys("Art");
        driver.findElement(By.name("email")).sendKeys("adf@mail.ru,/;");
        //Fill in the field "Password*" with numbers, the password must have at least 4 characters
        driver.findElement(By.name("password1")).sendKeys("12345");
        //Fill in the field "Confirm Password*" with numbers other than those entered in the field "Password*"
        driver.findElement(By.name("password2")).sendKeys("12345");
        //Click 'Register' button
        driver.findElement(By.cssSelector("[value=Register]")).click();
        boolean isSuccessfulMessageShown = driver.findElement(By.className("confirmation_message")).isDisplayed();
        driver.quit();
        Assert.assertTrue(isSuccessfulMessageShown, "Success message isn't shown");
    }
}
