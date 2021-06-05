package app.testcase;

import app.page.APP;

import app.page.StockPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * @Description:
 * @Author test_yjk
 * @Date 2021/6/1
 */
public class TestStock {
    private static StockPage stockpage;
    @BeforeAll
    public static void beforeAll() throws MalformedURLException {
        APP.getInstance().start();
    }
    @BeforeEach
    public void beforeEach(){
        stockpage = APP.getInstance().toStocks();
    }
    @Test
    void addDefaultSelectedStocks1(){
        if(stockpage.getAllStock().size() >= 1){
            stockpage.deleteAll();
        }
        assertThat(stockpage.addDefaultSelectedStocks().getAllStock().size(),greaterThanOrEqualTo(6));
    }
    @Test
    void delete(){

    }
}
