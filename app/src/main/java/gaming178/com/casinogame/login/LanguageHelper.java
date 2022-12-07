package gaming178.com.casinogame.login;

import android.content.Context;
import android.text.TextUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Util.Gd88Utils;
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
            languageItems.add(new MenuItemInfo<>(Gd88Utils.isGd88AndLiga365AndJump() ? R.mipmap.gd_lang_en_flag_gd88 : R.mipmap.gd_lang_en_flag, "EN", "en", "EN-US"));
            languageItems.add(new MenuItemInfo<>(R.mipmap.gd_lang_zh_flag, "ZH", "zh", "ZH-CN"));
            languageItems.add(new MenuItemInfo<>(R.mipmap.gd_lang_hk_flag, "HK", "zh_TW", "ZH-TW"));
            languageItems.add(new MenuItemInfo<>(R.mipmap.gd_lang_vn_flag, "VN", "vi", "EN-IE"));
            languageItems.add(new MenuItemInfo<>(R.mipmap.gd_lang_th_flag, "TH", "th", "TH-TH"));
            languageItems.add(new MenuItemInfo<>(Gd88Utils.isGd88AndLiga365AndJump() ? R.mipmap.gd_lang_in_flag_gd88 : R.mipmap.gd_lang_in_flag, "IN", "my", "EN-AU"));
        } else if (BuildConfig.FLAVOR.equals("liga365")) {
            languageItems.add(new MenuItemInfo<>(Gd88Utils.isGd88AndLiga365AndJump() ? R.mipmap.gd_lang_en_flag_gd88 : R.mipmap.gd_lang_en_flag, "EN", "en", "EN-US"));
            languageItems.add(new MenuItemInfo<>(R.mipmap.gd_lang_zh_flag, "ZH", "zh", "ZH-CN"));
            languageItems.add(new MenuItemInfo<>(Gd88Utils.isGd88AndLiga365AndJump() ? R.mipmap.gd_lang_in_flag_gd88 : R.mipmap.gd_lang_in_flag, "IN", "my", "EN-AU"));
        } else {
            BaseActivity baseActivity = (BaseActivity) context;
            if (baseActivity instanceof LoginActivity) {
                languageItems.add(new MenuItemInfo<>(R.mipmap.gd_lang_in_flag, "INDONESIAN", "my", "EN-AU"));
                languageItems.add(new MenuItemInfo<>(R.mipmap.gd_lang_en_flag, "English", "en", "EN-US"));
            } else {
//                languageItems.add(new MenuItemInfo<>(R.mipmap.gd_deposit_home, context.getString(R.string.gd_deposit), "deposit", ""));
//                languageItems.add(new MenuItemInfo<>(R.mipmap.gd_withdraw_home, context.getString(R.string.gd_withdrawal), "withdraw", ""));
                languageItems.add(new MenuItemInfo<>(R.mipmap.gd_home_setting, BuildConfig.FLAVOR.equals("ahlicasino") ? "Music" : context.getString(R.string.setting), "setting", ""));
                languageItems.add(new MenuItemInfo<>(R.mipmap.gd_change_password_home, context.getString(R.string.katasandi), "katasandi", ""));
                languageItems.add(new MenuItemInfo<>(R.mipmap.gd_referrer_home, context.getString(R.string.gd_Referrer), "referrer", ""));
                if (!BuildConfig.FLAVOR.equals("glxcasino") && !BuildConfig.FLAVOR.equals("mejaemas") && !BuildConfig.FLAVOR.equals("royalkasino")) {
                    languageItems.add(new MenuItemInfo<>(R.mipmap.gd_referral_list, context.getString(R.string.referral_list), "Referral_List", ""));
                }
                languageItems.add(new MenuItemInfo<>(R.mipmap.gd_home_report, context.getString(R.string.report), "report", ""));
                if (!TextUtils.isEmpty(baseActivity.mAppViewModel.getLiveChatStr()) && !BuildConfig.FLAVOR.equals("ahlicasino")) {
                    languageItems.add(new MenuItemInfo<>(R.mipmap.gd_live_chat, context.getString(R.string.Live_Chat), "liveChat", ""));
                }
                languageItems.add(new MenuItemInfo<>(R.mipmap.gd_home_report, context.getString(R.string.transaction_record), "TransactionRecord", ""));
                languageItems.add(new MenuItemInfo<>(R.mipmap.gd_finger, context.getString(R.string.fingerprint), "Fingerprint", ""));
            }
        }
    }

    public MenuItemInfo<String> getLanguageItem() {
        for (MenuItemInfo<String> languageItem : languageItems) {
            if (isItemLanguageSelected(languageItem.getType()))
                return languageItem;
        }
        return new MenuItemInfo<>(Gd88Utils.isGd88AndLiga365AndJump() ? R.mipmap.gd_lang_en_flag_gd88 : R.mipmap.gd_lang_en_flag, "English", "en", "EN-US");
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
