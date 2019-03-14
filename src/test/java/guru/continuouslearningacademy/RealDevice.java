package guru.continuouslearningacademy;

import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$;

public class RealDevice {
    public AndroidDriver driver;
    public String sendtext = "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...";

    @BeforeSuite
    public void SetupTest() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2_XL_API_28");
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus_5_API_24");
        capabilities.setCapability("deviceName", "LGG5");
        capabilities.setCapability("udid", "LGH85031de544d");
        //capabilities.setCapability(MobileCapabilityType.APP, "D:\\IDEA\\AutomationTesting\\7.2 ApiDemos-debug.apk");
        //capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("appPackage", "com.vkontakte.android");
        capabilities.setCapability("appActivity", "com.vkontakte.android.MainActivity");
        capabilities.setCapability("noReset", "true");
        /*
        adb devices
        adb shell
        generic_x86:/$ dumpsys window windows |grep mCurrentFocus
        uiautomatorviewer
        */
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        WebDriverRunner.setWebDriver(driver);
    }

    @Test(priority = 0)
    public void SendMessage() {
        $(By.id("com.vkontakte.android:id/tab_messages")).click();
        $(By.xpath("//android.widget.TextView[@text='Anastasia Verebey']")).click();
        $(By.id("com.vkontakte.android:id/writebar_edit")).setValue(sendtext);
        $(By.id("com.vkontakte.android:id/writebar_send")).click();
    }

    @Test(priority = 1)
    public void DeleteMessage() {
        $(By.id("com.vkontakte.android:id/avatar_content")).click();
        $(By.id("com.vkontakte.android:id/tab_messages")).click();
        $(By.xpath("//android.widget.TextView[@text='Anastasia Verebey']")).click();
        $(By.xpath("//android.widget.TextView[@text='"+sendtext+"']")).click();
        $(By.xpath("//android.widget.TextView[@text='Delete']")).click();
        $(By.id("android:id/button1")).click();

    }

    @AfterSuite
    public void ShutdownTest() {
        driver.quit();
    }
}