package gaming178.com.casinogame.login;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.AppTool;

/**
 * Created by ASUS on 2019/3/12.
 */

public class LanguageHelper {
    Context context;
    private List<MenuItemInfo<String>> languageItems;


    public LanguageHelper(Context context) {
        this.context = context;
        languageItems = new ArrayList<>();
        if (BuildConfig.FLAVOR.isEmpty() || BuildConfig.FLAVOR.equals("gd88")) {
            languageItems.add(new MenuItemInfo<>(R.mipmap.gd_lang_zh_flag, "简体中文", "zh", "ZH-CN"));
            languageItems.add(new MenuItemInfo<>(R.mipmap.gd_lang_en_flag, "English", "en", "EN-US"));
            languageItems.add(new MenuItemInfo<>(R.mipmap.gd_lang_th_flag, "ภาษาไทย", "th", "TH-TH"));
            languageItems.add(new MenuItemInfo<>(R.mipmap.gd_lang_vn_flag, "Tiếng Việt", "vi", "EN-IE"));
            languageItems.add(new MenuItemInfo<>(R.mipmap.gd_lang_in_flag, "INDONESIAN", "my", "EN-AU"));
            languageItems.add(new MenuItemInfo<>(R.mipmap.gd_lang_hk_flag, "繁軆中文", "zh_TW", "ZH-TW"));
        } else if (BuildConfig.FLAVOR.equals("liga365")) {
            languageItems.add(new MenuItemInfo<>(R.mipmap.gd_lang_zh_flag, "简体中文", "zh", "ZH-CN"));
            languageItems.add(new MenuItemInfo<>(R.mipmap.gd_lang_en_flag, "English", "en", "EN-US"));
            languageItems.add(new MenuItemInfo<>(R.mipmap.gd_lang_in_flag, "INDONESIAN", "my", "EN-AU"));
        } else {
            BaseActivity baseActivity = (BaseActivity) context;
            if (baseActivity instanceof LoginActivity) {
                languageItems.add(new MenuItemInfo<>(R.mipmap.gd_lang_in_flag, "INDONESIAN", "my", "EN-AU"));
                languageItems.add(new MenuItemInfo<>(R.mipmap.gd_lang_en_flag, "English", "en", "EN-US"));
            } else {
                languageItems.add(new MenuItemInfo<>(R.mipmap.gd_deposit_home, context.getString(R.string.gd_deposit), "deposit", ""));
                languageItems.add(new MenuItemInfo<>(R.mipmap.gd_withdraw_home, context.getString(R.string.gd_withdrawal), "withdraw", ""));
                languageItems.add(new MenuItemInfo<>(R.mipmap.gd_referrer_home, context.getString(R.string.gd_Referrer), "referrer", ""));
                languageItems.add(new MenuItemInfo<>(R.mipmap.gd_change_password_home, context.getString(R.string.katasandi), "katasandi", ""));
                if (!BuildConfig.FLAVOR.equals("glxcasino") && !BuildConfig.FLAVOR.equals("mejaemas") && !BuildConfig.FLAVOR.equals("royalkasino")) {
                    languageItems.add(new MenuItemInfo<>(R.mipmap.gd_referral_list, context.getString(R.string.referral_list), "Referral_List", ""));
                }
            }
        }
    }

    public MenuItemInfo<String> getLanguageItem() {
        for (MenuItemInfo<String> languageItem : languageItems) {
            if (isItemLanguageSelected(languageItem.getType()))
                return languageItem;
        }
        return new MenuItemInfo<>(R.mipmap.gd_lang_en_flag, "English", "en", "EN-US");
    }

    public boolean isItemLanguageSelected(String type) {
        String lag = AppTool.getAppLanguage(context);
        if (lag.equals(type))
            return true;
        return false;
    }

    @NotNull
    public List<MenuItemInfo<String>> getLanguageItems() {
        return languageItems;
    }
}
