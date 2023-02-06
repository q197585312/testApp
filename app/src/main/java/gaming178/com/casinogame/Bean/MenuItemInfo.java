package gaming178.com.casinogame.Bean;

import java.io.Serializable;

public class MenuItemInfo<P> implements Serializable {
    int res;
    String text;
    P parent;
    String type;

    public MenuItemInfo(int res, String text) {
        this.res = res;
        this.text = text;
    }

    public MenuItemInfo(int res, String text, String type) {
        this(res, text);
        this.type = type;
    }

    public MenuItemInfo(int res, String text, String type, P parent) {
        this(res, text);
        this.type = type;
        this.parent=parent;
    }

    public P getParent() {
        return parent;
    }

    public void setParent(P parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    /**0是正常 1是mix*/
    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

}
