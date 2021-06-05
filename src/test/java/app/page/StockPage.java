package app.page;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author test_yjk
 * @Date 2021/6/1
 */
public class StockPage extends BasePage{
    public List<String> getAllStock(){
        List<String> stocks = new ArrayList<>();
        findElements(By.id("com.xueqiu.android:id/portfolio_stockName")).forEach(stock->{
            stocks.add(stock.getText());
        });
        System.out.println(stocks);
        return stocks;
    }
    public StockPage addDefaultSelectedStocks(){
        click(By.id("com.xueqiu.android:id/subscribe"));
        return this;
    }
    public SearchPage toSearch(){
        click(By.id("com.xueqiu.android:id/action_search"));
        return new SearchPage();
    }
    public StockPage deleteAll(){
//        click(By.id("com.xueqiu.android:id/edit_group"));
//        click(By.id("com.xueqiu.android:id/check_all"));
//        click(By.id("com.xueqiu.android:id/cancel_follow"));
//        click(By.id("com.xueqiu.android:id/tv_right"));
//        click(By.id("com.xueqiu.android:id/action_close"));
        parseSteps();
        return this;
    }
}
