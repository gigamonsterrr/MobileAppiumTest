package guru.continuouslearningacademy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class EmulatorWeb {
    private AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
    private AndroidDriver driver;

    @BeforeTest
    public void SetupTest() throws MalformedURLException {
        Configuration.timeout = 3000;
        Configuration.reportsFolder = "./target/surefire-reports/screenshots";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_3_XL_API_29");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
        capabilities.setCapability(MobileCapabilityType.HAS_TOUCHSCREEN, true);
        //Configuration.timeout = 10000;
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus_5_API_24");
        //capabilities.setCapability("deviceName", "LGG5");
        //capabilities.setCapability("udid", "LGH85031de544d");
        //capabilities.setCapability(MobileCapabilityType.APP, "D:\\IDEA\\AutomationTesting\\7.2 ApiDemos-debug.apk");
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
        System.out.println("Staring Appium");
        service.start();
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        WebDriverRunner.setWebDriver(driver);
    }

    @Test
    private void browserTestSelenide() {
        open("http://dev.haldexcommerce.ddcloud.se");
        $("#cookiebar").shouldBe(Condition.visible);
        $("#cookiebar > div > button").click();
        System.out.println("check again");
        $("#cookiebar").shouldBe(Condition.visible);
    }

    @AfterTest
    private void stopAppium() {
        System.out.println("Terminating Appium");
        service.stop();
    }
}