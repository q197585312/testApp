package nanyang.com.dig88.NewKeno;

/**
 * Created by Administrator on 2018/9/13.
 */

public class NewKenoLastGameBean {
    private String content;
    private String result;

    public NewKenoLastGameBean(String content, String result) {
        this.content = content;
        this.result = result;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
