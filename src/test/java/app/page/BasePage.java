package app.page;

import app.framework.PageObjectMethod;
import app.framework.PageObjectModel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author test_yjk
 * @Date 2021/6/1
 */
public class BasePage {

    public static AndroidDriver<WebElement> driver;

    private PageObjectModel model = new PageObjectModel();

    private static HashMap<String,Object> params = new HashMap<>();

    public static HashMap<String, Object> getParams() {
        return params;
    }

    public static void setParams(HashMap<String, Object> params) {
        BasePage.params = params;
    }

    private static HashMap<String,Object> results;

    public static HashMap<String, Object> getResults() {
        return results;
    }

    public static void setResults(HashMap<String, Object> results) {
        BasePage.results = results;
    }

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

    public void parseSteps(){
        String method = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println(method);
        parseSteps(method);
    }
    public void parseSteps(String method){
        //拼接YAML文件路径
        String path = "/"+ this.getClass().getCanonicalName().replace(".","/") + ".yaml";
        System.out.println(path);
        System.out.println(method);
        parseSteps( path , method );
    }
    public void parseSteps(String path,String method){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        System.out.println(BasePage.class.getResourceAsStream(path));
        try{
            model = mapper.readValue(
                    BasePage.class.getResourceAsStream(path),PageObjectModel.class
            );
            System.out.println(model.methods.get(method));
            parseSteps(model.methods.get(method));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void parseSteps(PageObjectMethod steps){
        steps.getSteps().forEach(step->{
            WebElement element = null;

            String id = step.get("id").toString();
            if(id != null){
                element = findElement(By.id(id));
            }else if(step.get("aid") != null){
                element = findElement(MobileBy.AccessibilityId(step.get("aid").toString()));
            }else if(step.get("xpath") != null){
                element = findElement(By.xpath(step.get("xpath").toString()));
            }else if(step.get("element") != null){
                element = findElement(model.elements.get(step.get("element")).getLocator());
            }

            //将${keyword}替换
            if(step.get("send") != null){
                String send = step.get("send").toString();
                System.out.println(send);
                for(Map.Entry<String,Object> entry : params.entrySet()){
                    String keyword = "${" + entry.getKey() + "}";
                    if(send.contains(keyword)){
                        System.out.println(entry);
                        send = send.replace(keyword , entry.getValue().toString());
                    }
                }

                element.sendKeys(send);

            }else if(step.get("get") != null){
                System.out.println(step.get("get"));
                String attribute = element.getAttribute(step.get("get").toString());
                System.out.println(attribute);
                System.out.println(step.get("dump"));
                getResults().put(step.get("dump").toString(),attribute);
                System.out.println("over");
            }else{
                element.click();
            }
        });
    }
}
