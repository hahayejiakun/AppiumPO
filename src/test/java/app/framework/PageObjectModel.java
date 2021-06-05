package app.framework;

import java.util.HashMap;

/**
 * @Description:
 * @Author test_yjk
 * @Date 2021/6/2
 */
public class PageObjectModel {

    public HashMap<String,PageObjectElement> elements = new HashMap<>();
    public HashMap<String,PageObjectMethod> methods = new HashMap<>();
    public PageObjectModel(){

    }
}
