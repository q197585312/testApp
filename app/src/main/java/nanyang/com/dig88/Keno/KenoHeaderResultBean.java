package nanyang.com.dig88.Keno;

import java.io.Serializable;
import java.util.ArrayList;

import nanyang.com.dig88.Entity.ResultListBean;

/**
 * Created by Administrator on 2016/6/17.
 */
public class KenoHeaderResultBean implements Serializable{
    ArrayList<String> headerStr;
    ArrayList<String> resultStr;
    ResultListBean bean;
    private String total;
    public KenoHeaderResultBean(ResultListBean bean,String total, ArrayList<String> headerStr,ArrayList<String> resultStr) {
            this.bean=bean;
        this.total = total;
        this.headerStr=headerStr;
        this.resultStr=resultStr;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<String> getHeaderStr() {
        return headerStr;
    }

    public void setHeaderStr(ArrayList<String> headerStr) {
        this.headerStr = headerStr;
    }

    public ArrayList<String> getResultStr() {
        return resultStr;
    }

    public void setResultStr(ArrayList<String> resultStr) {
        this.resultStr = resultStr;
    }

    public ResultListBean getBean() {
        return bean;
    }

    public void setBean(ResultListBean bean) {
        this.bean = bean;
    }
}
