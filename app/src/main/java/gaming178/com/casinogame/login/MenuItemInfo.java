package gaming178.com.casinogame.login;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class MenuItemInfo<P> implements Serializable, IString {
    int res;
    String text;
    String SimpleText;
    String day;
    String dateParam;
    @NotNull
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
        this.parent = parent;
    }

    public MenuItemInfo(int res, String text, String SimpleText, String type, P parent) {
        this(res, text);
        this.SimpleText = SimpleText;
        this.type = type;
        this.parent = parent;
    }

    public MenuItemInfo(int res, String text, String type, P parent, String day, String dateParam) {
        this(res, text);
        this.type = type;
        this.parent = parent;
        this.day = day;
        this.dateParam = dateParam;
    }

    public MenuItemInfo() {
        super();
    }

    public String getDateParam() {
        return dateParam;
    }

    public void setDateParam(String dateParam) {
        this.dateParam = dateParam;
    }

    @NotNull
    public P getParent() {
        return parent;
    }

    public void setParent(@NotNull P parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * 0是正常 1是mix
     */
    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSimpleText() {
        return SimpleText;
    }

    public void setSimpleText(String simpleText) {
        SimpleText = simpleText;
    }
}
