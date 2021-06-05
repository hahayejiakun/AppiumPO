package app.framework;

import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Author test_yjk
 * @Date 2021/6/2
 */
public class PageObjectMethod {

    private List<HashMap<String,Object>> steps;

    public List<HashMap<String, Object>> getSteps() {
        return steps;
    }

    public void setSteps(List<HashMap<String, Object>> steps) {
        this.steps = steps;
    }
}
