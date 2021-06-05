package app.testcase;

import app.page.APP;
import app.page.BasePage;
import app.page.SearchPage;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;



import java.net.MalformedURLException;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.params.provider.Arguments.arguments;

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
    @AfterEach
    public void afterEach(){
        searchpage.cancel();
    }
//    @AfterAll
//    public static void afterAll(){
//        searchpage.cancel();
//    }

    @ParameterizedTest
    @MethodSource("data")
    void search(String name , Float price){

        assertThat(searchpage.search(name).getCurrentPrices(price),greaterThanOrEqualTo(price));
    }

    public static Stream<Arguments> data(){
        return Stream.of(
                arguments("贵州茅台",2000f),
                arguments("中远海控",100f)
        );
    }
}
