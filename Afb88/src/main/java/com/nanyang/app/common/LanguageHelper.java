package com.nanyang.app.common;

import android.app.Activity;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ASUS on 2019/3/12.
 */

public class LanguageHelper {
    HashMap<String, String> map = new HashMap<>();
    Activity context;
    private List<MenuItemInfo<String>> languageItems;

    /*  case "zh":
                lang = "ZH-CN";
                break;
            case "en":
                lang = "EN-US";
                break;
            case "th":
                lang = "TH-TH";
                break;
            case "ko":
                lang = "EN-TT";
                break;
            case "vi":
                lang = "EN-IE";
                break;
            case "tr":
                lang = "UR-PK";
                break;
            default:
                lang = "EN-US";
                break;*/
    public LanguageHelper(Activity context) {
        this.context = context;
        map.put("zh", "ZH-CN");
        map.put("en", "EN-US");
        map.put("th", "TH-TH");
        map.put("ko", "EN-TT");
        map.put("vi", "EN-IE");
        map.put("tr", "UR-PK");
        map.put("my", "EN-AU");
        languageItems = new ArrayList<>();
        languageItems.add(new MenuItemInfo<>(R.mipmap.lang_zh_flag, (R.string.language_zh), "zh", "ZH-CN"));
        languageItems.add(new MenuItemInfo<>(R.mipmap.lang_en_flag, (R.string.language_en), "en", "EN-US"));
        languageItems.add(new MenuItemInfo<>(R.mipmap.lang_th_flag, (R.string.language_th), "th", "TH-TH"));
        languageItems.add(new MenuItemInfo<>(R.mipmap.lang_ko_flag, (R.string.language_ko), "ko", "EN-TT"));
        languageItems.add(new MenuItemInfo<>(R.mipmap.lang_vi_flag, (R.string.language_vi), "vi", "EN-IE"));
        languageItems.add(new MenuItemInfo<>(R.mipmap.lang_tr_flag, (R.string.language_tr), "tr", "UR-PK"));
        languageItems.add(new MenuItemInfo<>(R.mipmap.lang_tr_flag, (R.string.language_my), "my", "EN-AU"));

    }

    public String getLanguage() {
        String lag = AfbUtils.getLanguage(context);
        String s = map.get(lag);
        if (s == null)
            s = "EN-US";
        return s;
    }

    public MenuItemInfo<String> getLanguageItem() {
        String lag = AfbUtils.getLanguage(context);
        for (MenuItemInfo<String> languageItem : languageItems) {
            if (lag.equals(languageItem.getType()))
                return languageItem;
        }
        return  new MenuItemInfo<>(R.mipmap.lang_zh_flag, R.string.language_zh, "zh", "ZH-CN");
    }

    public String getLanguage(String lag) {
        String s = map.get(lag);
        if (s == null)
            s = "EN-US";
        return s;
    }

    public void switchLanguage(String language) {
        AfbUtils.switchLanguage(language, context);
    }

    public List<MenuItemInfo<String>> getLanguageItems() {
        return languageItems;
    }
}
