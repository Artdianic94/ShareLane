import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignUpTest {
    @BeforeTest
    public void setProp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
    }

    @Test
    public void sendFiveDigitsToZipCodeFieldTest() {
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
        WebDriver driver = new ChromeDriver();
        //Open Zip code page
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        //Input 4 digits zip;
        driver.findElement(By.name("zip_code")).sendKeys("1234");
        //Click the "Continue"
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        //Check error message is shown
        boolean isErrorMessageShown = driver.findElement(By.className("error_message")).isDisplayed();
        driver.quit();
        Assert.assertTrue(isErrorMessageShown, "Error message isn't shown");
    }

    @Test
    public void sendSignUpFormTest() {
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
        WebDriver driver = new ChromeDriver();
        try {
            //Open Zip code page
            driver.get("https://www.sharelane.com/cgi-bin/register.py");
            //Input 6 digits zip;
            driver.findElement(By.name("zip_code")).sendKeys("123456");
            //Click the "Continue"
            driver.findElement(By.cssSelector("[value=Continue]")).click();
            //Check error message is shown
            boolean isErrorMessageShown = driver.findElement(By.className("error_message")).isDisplayed();
            driver.quit();
            Assert.assertTrue(isErrorMessageShown, "Error message isn't shown");
        } catch (NoSuchElementException e) {
            driver.quit();
            Assert.fail("Error message isn't shown");
        }
    }

    @Test
    public void sendRepeatedPasswordTest() {
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
    public void sendFirstNameWithDifferentLettersTest() {
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
    public void sendEmailWithInvalidCharactersTest() {
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
