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
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "nidhioneplus");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13");
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
		System.out.println("***launchLoginActivity***");
		System.out.println("Click on Login Flow");
		TimeUnit.SECONDS.sleep(2);
		driver.executeScript(
				"mobile: startActivity", ImmutableMap.of(
						"component", String.format("%s/%s", APP_PACKAGE, APP_ACTIVITY)));
		TimeUnit.SECONDS.sleep(2);
		boolean isDisplayed = driver.findElement(By.id("register")).isDisplayed();
		assertTrue(isDisplayed);
		System.out.println("click on signin");
		TimeUnit.SECONDS.sleep(1);
	}

	@Test
	@Order(2)
	@DisplayName("Negative flow Test")
	void LoginFailed() throws Exception {
		System.out.println("Negative login flow test case");
		TimeUnit.SECONDS.sleep(2);
		Thread.sleep(5000);

		boolean isDisplayed = driver.findElement(By.id("button2")).isDisplayed();
		assertTrue(isDisplayed);
		driver.findElement(By.id("button2")).click();
		System.out.println("***************Negative flow***************");
		TimeUnit.SECONDS.sleep(1);
		driver.findElement(By.id("com.test.loginapp:id/editText")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("com.test.loginapp:id/editText")).sendKeys("test@tet1.com");
		driver.findElement(By.id("com.test.loginapp:id/editText2")).click();
		driver.findElement(By.id("com.test.loginapp:id/editText2")).sendKeys("tet");
		Thread.sleep(4000);
		driver.findElement(By.id("com.test.loginapp:id/button")).click();
		Thread.sleep(5000);
		boolean loginUnSuccess =driver.findElement(By.id("com.test.loginapp:id/button")).isDisplayed();
		assertTrue(loginUnSuccess);
	}
	@Test
	@Order(3)
	@DisplayName("login success Test")
	void loginSuccess() throws Exception {
		System.out.println("Positive login flow testcase ");
		TimeUnit.SECONDS.sleep(2);
		TimeUnit.SECONDS.sleep(1);
		System.out.println("***************Positive flow***************");
		driver.findElement(By.id("com.test.loginapp:id/editText")).click();
		driver.findElement(By.id("com.test.loginapp:id/editText")).clear();
		Thread.sleep(5000);
		driver.findElement(By.id("com.test.loginapp:id/editText")).sendKeys("test@test.com");
		driver.findElement(By.id("com.test.loginapp:id/editText2")).click();
		driver.findElement(By.id("com.test.loginapp:id/editText2")).clear();
		driver.findElement(By.id("com.test.loginapp:id/editText2")).sendKeys("test");
		driver.findElement(By.id("com.test.loginapp:id/button")).click();
		Thread.sleep(5000);
		String loginStatus= driver.findElement(By.id("com.test.loginapp:id/tv_login_status")).getText();
		System.out.println("Status of login: "+ loginStatus);
	}

}

