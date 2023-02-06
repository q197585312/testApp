package nanyang.com.dig88.Lottery4D.Bean;

import java.util.List;

/**
 * Created by Administrator on 2019/1/24.
 */

public class PeriodNumberBean {

    /**
     * code : 1
     * data : ["20190126001#26/01","20190127001#27/01"]
     */

    private String code;
    private List<String> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
