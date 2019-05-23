package guru.continuouslearningacademy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.*;

public class EmulatorWeb {
    private AndroidDriver driver;

    @BeforeSuite
    public void SetupTest() throws MalformedURLException {
        Configuration.reportsFolder = "./target/surefire-reports/screenshots";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_3_XL_API_28");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        //Configuration.timeout = 10000;
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus_5_API_24");
        //capabilities.setCapability("deviceName", "LGG5");
        //capabilities.setCapability("udid", "LGH85031de544d");
        //capabilities.setCapability(MobileCapabilityType.APP, "D:\\IDEA\\AutomationTesting\\7.2 ApiDemos-debug.apk");
        //cmd -> npm install appium --chromedriver_version="2.44"
        //capabilities.setCapability("appPackage", "com.android.calculator2");
        //capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        //capabilities.setCapability("noReset", "true");
        /*
        adb devices
        adb shell
        dumpsys window windows |grep mCurrentFocus
        uiautomatorviewer
        adb kill-server&&adb start-server
        */
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        WebDriverRunner.setWebDriver(driver);
    }

    /*@Test(priority = 0)
    public void SendMessage() {
        $(By.id("com.android.calculator2:id/digit_2")).click();
        $(By.id("com.android.calculator2:id/op_add")).click();
        $(By.id("com.android.calculator2:id/digit_2")).click();
        $(By.id("com.android.calculator2:id/op_mul")).click();
        $(By.id("com.android.calculator2:id/digit_2")).click();
        $(By.id("com.android.calculator2:id/eq")).click();
        //$(By.id("com.android.calculator2:id/result")).shouldHave(Condition.text("6"));
        Assert.assertEquals($(By.id("com.android.calculator2:id/result")).getText(), "6");
    }*/

    @Test
    private void browserTest() {
        open("http://dev.haldexcommerce.ddcloud.se");
        $("#cookiebar").shouldBe(Condition.visible);
        $("#cookiebar > div > button").click();
        sleep(3000);
        $("#cookiebar").shouldBe(Condition.visible);
    }

    @AfterSuite
    public void ShutdownTest() {
        driver.quit();
    }
}