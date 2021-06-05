package app.framework;

import app.page.BasePage;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Author test_yjk
 * @Date 2021/6/2
 */
public class PageObjectElement {

    public List<HashMap<String,String>> element;

    public By getLocator(){
        String os = BasePage.driver.getCapabilities().getPlatform().toString().toLowerCase();
        return By.id("com.xueqiu.android:id/search_input_text");
    }
    public By getLocator(String os,String version){
        for(HashMap<String,String> map : element){
            if(map.get("os") == os && map.get("version") == version){
                if(map.get("id") != null){
                    return By.id(map.get("id"));
                }else if(map.get("xpath") != null);{
                    return By.id(map.get("xpath"));
                }
            }
            break;
        }
        return null;
    }

}
