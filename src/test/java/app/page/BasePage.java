package app.page;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author test_yjk
 * @Date 2021/6/1
 */
public class BasePage {
    public static AndroidDriver<WebElement> driver;
    
    //封装findElement
    public static WebElement findElement(By by){
        System.out.println(by);
        try {
            return driver.findElement(by);
        }catch (Exception e){
            handleAlert();
            return driver.findElement(by);
        }
    }

    //封装click
    public static void click(By by){
        System.out.println(by);
        try {
            driver.findElement(by).click();
        }catch (Exception e){
            handleAlert();
            driver.findElement(by).click();
        }
    }

    public static List<WebElement> findElements(By by){
        System.out.println(by);
        return driver.findElements(by);
    }

    static void handleAlert() {

        By tips = By.id("com.xueqiu.android:id/snb_tip_text");

        List<By> alertBox = new ArrayList<>();//设置一个黑名单弹框
        alertBox.add(By.id("com.xueqiu.android:id/ib_close"));
        alertBox.add(tips);
        alertBox.add(By.id("com.xueqiu.android:id/image_cancel"));
        alertBox.add(By.id("com.xueqiu.android:id/md_buttonDefaultNegative"));
        alertBox.add(By.id("com.xueqiu.android:id/tv_agree"));


        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);//设置等待时间

        alertBox.forEach(alert ->{
            List<WebElement> handle = driver.findElements(alert);
            if(alert.equals(tips)){
                System.out.println("snb_tip_text found");
                Dimension size = driver.manage().window().getSize();
                try{
                    if(driver.findElements(tips).size() >= 1){
                        new TouchAction(driver).tap(PointOption.point(size.height/2,size.width/2)).perform();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("snb_tip_text click");
                }
            }else if(handle.size() >= 1){
                handle.get(0).click();
            }
        });
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

}
