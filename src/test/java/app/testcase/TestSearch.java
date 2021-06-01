package app.testcase;

import app.page.APP;
import app.page.SearchPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * @Description:
 * @Author test_yjk
 * @Date 2021/6/1
 */
public class TestSearch {
    private static SearchPage searchpage;
    @BeforeAll
    public static void beforeAll() throws MalformedURLException {
        APP.getInstance().start();
    }
    @BeforeEach
    public void beforeEach(){
        searchpage = APP.getInstance().toSearch();
    }
//    @AfterAll
//    public static void afterAll(){
//        searchpage.cancel();
//    }

    @Test
    void search(){
        assertThat(searchpage.search("贵州茅台").getCurrentPrice(),greaterThanOrEqualTo(2000f));
    }

}
