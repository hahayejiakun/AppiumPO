package app.page;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;



/**
 * @Description:
 * @Author test_yjk
 * @Date 2021/6/1
 */
public class SearchPage extends BasePage{
    private By inputText = By.id("com.xueqiu.android:id/search_input_text");
    private By name = By.id("com.xueqiu.android:id/name");

    public SearchPage search(String keyword){
        APP.driver.findElement(inputText).sendKeys(keyword);
        click(name);
        return this;
    }

    public APP cancel(){
        click(By.id("com.xueqiu.android:id/action_close"));
        return new APP();
    }

    public  SearchPage select(){
        click(By.id("com.xueqiu.android:id/follow_btn"));
        return this;
    }

    public Float getCurrentPrice(){
        return Float.valueOf(findElement(By.id("com.xueqiu.android:id/current_price")).getText());
    }
}
