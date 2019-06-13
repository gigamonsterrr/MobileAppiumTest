package guru.continuouslearningacademy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$;

public class EmulatorApp {
    private AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
    private AndroidDriver driver;

    @BeforeTest
    public void SetupTest() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_3_XL_API_28");
        //capabilities.setCapability("deviceName", "LGG5");
        //capabilities.setCapability("udid", "LGH85031de544d");
        //capabilities.setCapability(MobileCapabilityType.APP, "D:\\IDEA\\AutomationTesting\\7.2 ApiDemos-debug.apk");
        //capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("appPackage", "com.android.calculator2");
        capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        capabilities.setCapability("noReset", "true");
        /*
        adb devices
        adb shell
        dumpsys window windows |grep mCurrentFocus
        uiautomatorviewer
        adb kill-server&&adb start-server
        */
        //mvn clean test -Dsurefire.suiteXmlFiles=testng.xml
        System.out.println("Staring Appium");
        service.start();
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        WebDriverRunner.setWebDriver(driver);
    }

    @Test(priority = 0)
    public void SendMessage() {
        $(By.id("com.android.calculator2:id/digit_2")).click();
        $(By.id("com.android.calculator2:id/op_add")).click();
        $(By.id("com.android.calculator2:id/digit_2")).click();
        $(By.id("com.android.calculator2:id/op_mul")).click();
        $(By.id("com.android.calculator2:id/digit_2")).click();
        $(By.id("com.android.calculator2:id/eq")).click();
        $(By.id("com.android.calculator2:id/result")).shouldHave(Condition.text("6"));
    }

    @AfterTest
    private void stopAppium() {
        System.out.println("Terminating Appium");
        service.stop();
    }
}