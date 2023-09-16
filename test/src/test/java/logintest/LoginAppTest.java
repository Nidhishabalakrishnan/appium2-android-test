package logintest;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 *
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoginAppTest {
	private static AndroidDriver driver = null;
	private static int APPIUM_VERSION = 2; // ONLY 1 and 2 are valid options for major releases.
	private static final String APP_PACKAGE="com.test.loginapp";
	private static final String APP_ACTIVITY="com.test.loginapp.MainActivity";

	@BeforeAll
	 public static void beforeAll() throws Exception {
		System.out.println("Initializing Appium Connection...");
		    
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Mi A3");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");

		capabilities.setCapability("appPackage", APP_PACKAGE);
		capabilities.setCapability("appActivity", APP_ACTIVITY);
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("ignoreHiddenApiPolicyError", true);

		URL conn = null;
		if (APPIUM_VERSION == 2) {
			conn = new URL("http://0.0.0.0:4723/");
		} else {
			conn = new URL("http://0.0.0.0:4723/wd/hub");
		}
	    driver = new AndroidDriver(conn, capabilities);
	 }

	@AfterAll
	public static void afterAll() throws Exception {
		if (driver != null)
			driver.quit();
	}

	@Test
	@Order(1)
	@DisplayName("doRegistration Test")
	void launchLoginActivity() throws Exception {
		System.out.println("launchLoginActivity...test...");
		System.out.println("Presenting for 2 seconds...");
		TimeUnit.SECONDS.sleep(2);
		// This is a deliberate success to show on report.
		// This logo IS displayed on the target page.
//		driver.startActivity(strPackageName, strActivityName);
		driver.executeScript(
				"mobile: startActivity", ImmutableMap.of(
						"component", String.format("%s/%s", APP_PACKAGE, APP_ACTIVITY)));
		TimeUnit.SECONDS.sleep(2);
		boolean isDisplayed = driver.findElement(By.id("register")).isDisplayed();
		assertTrue(isDisplayed);
		System.out.println("Presenting for 1 seconds...");
		TimeUnit.SECONDS.sleep(1);
	}

	@Test
	@Order(2)
	@DisplayName("doRegistration Test")
	void clickLoginButton() throws Exception {
		System.out.println("doRegistration...test...");
		System.out.println("Presenting for 2 seconds...");
		TimeUnit.SECONDS.sleep(2);
		// This is a deliberate success to show on report.
		// This logo IS displayed on the target page.
		boolean isDisplayed = driver.findElement(By.id("button2")).isDisplayed();
		assertTrue(isDisplayed);
		driver.findElement(By.id("button2")).click();
		System.out.println("Presenting for 1 seconds...");
		TimeUnit.SECONDS.sleep(1);
	}

//	@Test
//	@Order(2)
//	@DisplayName("doRegistration Test")
//	void doRegistration() throws Exception {
//		System.out.println("doRegistration...test...");
//		System.out.println("Presenting for 2 seconds...");
//		TimeUnit.SECONDS.sleep(2);
//		// This is a deliberate success to show on report.
//		// This logo IS displayed on the target page.
//		boolean isDisplayed = driver.findElement(By.id("button2")).isDisplayed();
//		assertTrue(isDisplayed);
//        driver.findElement(By.id("register")).click();
//		System.out.println("Presenting for 1 seconds...");
//		TimeUnit.SECONDS.sleep(1);
//	}
}

