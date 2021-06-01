package app.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author test_yjk
 * @Date 2021/6/1
 */
public class APP extends BasePage{
    private static APP app;

    public static APP getInstance() {
        if(app == null){
            return new APP();
        }
        return app;
    }

    public void start() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName","android");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("noReset", false);
        desiredCapabilities.setCapability("autoGrantPermissions", true);
        desiredCapabilities.setCapability("udid", System.getenv("UDID"));

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl,desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        handleAlert();
        long startTime = System.currentTimeMillis();
        new WebDriverWait(driver,50)
                .until(n->{
                    String xml = driver.getPageSource();
                    Boolean exist = xml.contains("home_search") || xml.contains("image_cancel");
                    System.out.println((System.currentTimeMillis()-startTime)/1000);
                    return exist;
                });
    }

    public SearchPage toSearch(){
        click(By.id("com.xueqiu.android:id/home_search"));
        return new SearchPage();
    }

    public StockPage toStocks(){
        click(By.xpath("//*[contains(@resource-id, 'tab_name') and @text='行情']"));
        return new StockPage();
    }
}
