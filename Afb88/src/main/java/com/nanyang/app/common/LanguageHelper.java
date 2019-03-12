package com.nanyang.app.common;

import android.app.Activity;

import com.nanyang.app.AfbUtils;

import java.util.HashMap;

/**
 * Created by ASUS on 2019/3/12.
 */

public class LanguageHelper {
    HashMap<String, String> map = new HashMap<>();
    Activity context;

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
    }

    public String getLanguage() {
        String lag = AfbUtils.getLanguage(context);
        String s = map.get(lag);
        if (s == null)
            s = "EN-US";
        return s;
    }
    public void switchLanguage(String language){
        AfbUtils.switchLanguage(language, context);
    }

}
