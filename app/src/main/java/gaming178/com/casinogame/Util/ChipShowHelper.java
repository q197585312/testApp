package gaming178.com.casinogame.Util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.DragonTigerActivity;
import gaming178.com.casinogame.Bean.ChipBean;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;
import gaming178.com.mylibrary.myview.View.AutofitTextView;

/**
 * Created by Administrator on 2016/4/18.
 */
public class ChipShowHelper {
    List<ChipBean> chips;
    FrameLayout framelayout;
    Context context;
    TextView cancelText;
    TextView repeatText;
    TextView sureText;
    BaseActivity baseActivity;

    private boolean isButtonShow;
    private int operationWidth;
    private int operationHeight;

    public void setTextGravity(int textGravity) {
        this.textGravity = textGravity;
    }

    private int textGravity = Gravity.CENTER;
    private int bottomMargin = Gravity.CENTER;
    private int chipTop = 0;

    public void setChipGravity(int chipGravity) {
        this.chipGravity = chipGravity;
    }

    private int chipGravity = Gravity.CENTER;

    public int getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(int moneyCount) {
        this.moneyCount = moneyCount;
    }

    int moneyCount = 0;

    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }

    private int firstIndex = 0;

    public void setTopMargin(int topMargin) {
        this.topMargin = topMargin;
    }

    private int topMargin = 5;
    private int moneyTipsTextSize = 7;

    public void setMoneyTipsTextSize(int size) {
        moneyTipsTextSize = size;
    }

    public ChipShowHelper(Context ctx, FrameLayout framelayout, List<ChipBean> chips) {
        this.chips = chips;
        this.framelayout = framelayout;
        this.context = ctx;
        baseActivity = (BaseActivity) ctx;
        this.firstIndex = framelayout.getChildCount();
    }

    public void showChipView(int x, int y, int with, int high, int chipIndex, int pos) {
        if (with > framelayout.getWidth())
            with = framelayout.getWidth();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(with, high);
        ImageView imageView = new ImageView(context);
//        params.leftMargin = x;

        params.topMargin = y - (pos * topMargin);
        chipTop = params.topMargin;
        params.gravity = chipGravity;
        for (ChipBean chip : chips) {
            if (chip.getValue() == chipIndex) {
                imageView.setBackgroundResource(chip.getDrawableRes());
                break;
            }
        }
        imageView.setLayoutParams(params);
        framelayout.addView(imageView);
    }

    public void clearAllChips()//删除所有的路子，但是不删除显示路子的背景图片
    {

        int iCount = framelayout.getChildCount();

        View imageView = null;
        for (int i = firstIndex; i < iCount; i++) {
            imageView = (View) framelayout.getChildAt(firstIndex);//始终删除最上面那个
            if (imageView != null)
                framelayout.removeViewAt(firstIndex);

        }

    }

    public int showChip(int moneyChip, int x, int y, int with, int high, int tipsX, int tipsY, int tipsWith
            , int tipsHigh) {
        this.moneyCount = moneyChip;
        clearAllChips();
        int iCount = 0;
        int iPosCount = 0;
        int moneyTips = moneyChip;  //传入金额
        int betMoney = 0;

        for (int i = chips.size() - 1; i >= 0; i--) {
            ChipBean chip = chips.get(i);
            int iChip = moneyChip / chip.getValue();
            while (iCount < iChip) {

                //   [self showChipPic:x :y :iPosCount :region_chip:1000000:width:high];
                showChipView(x, y, with, high, chip.getValue(), iPosCount);
                iCount++;
                iPosCount++;
            }
            betMoney = moneyChip % chip.getValue();
            if (betMoney == 0 && tipsWith > 0 && tipsHigh > 0) {
                showChipMoneyTips(tipsX, tipsY, tipsWith, tipsHigh, moneyTips, iPosCount);
                return moneyTips;
            }
            iCount = 0;
            moneyChip = betMoney;
        }
        return moneyTips;
    }

    public void showChipMoneyTips(int x, int y, int with, int high, int money, int pos) {
        if (money <= 0)
            return;
        FrameLayout.LayoutParams paramsText = new FrameLayout.LayoutParams(with, high);
        AutofitTextView textView = new AutofitTextView(context);
        textView.setMaxTextSize(ScreenUtil.dip2px(context, 10));
//        paramsText.leftMargin = x;
        paramsText.gravity = chipGravity;
//        textView.setBackgroundResource(R.drawable.rectangle_blue_table_corners5);
        textView.setBackgroundResource(R.mipmap.show_chip_bg);
        textView.setTextColor(context.getResources().getColor(R.color.white));
        textView.setTextSize(moneyTipsTextSize);
        textView.setTypeface(Typeface.SERIF);
        textView.setGravity(Gravity.CENTER);
        //	textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        TextPaint paint = textView.getPaint();
        paint.setFakeBoldText(true);
        textView.setText("" + money);
        paramsText.topMargin = y/2;
        paramsText.gravity = Gravity.CENTER;
        if (textGravity == Gravity.TOP)
            paramsText.topMargin = chipTop - high - 10;
        textView.setLayoutParams(paramsText);
        framelayout.addView(textView);
        if (cancelText != null && sureText != null && repeatText != null && operationHeight < 1 && operationWidth < 1) {

            FrameLayout.LayoutParams paramsCancel = (FrameLayout.LayoutParams) cancelText.getLayoutParams();
            FrameLayout.LayoutParams paramsSure = (FrameLayout.LayoutParams) sureText.getLayoutParams();
            FrameLayout.LayoutParams paramsRepeat = (FrameLayout.LayoutParams) repeatText.getLayoutParams();
            paramsSure.topMargin = chipTop - high;
            paramsCancel.topMargin = chipTop - high;
            paramsRepeat.topMargin = chipTop - high;
            if (textGravity == Gravity.TOP) {
                paramsSure.topMargin = chipTop - high - high - AutoUtils.getPercentHeightSize(5);
                paramsCancel.topMargin = chipTop - high - high - AutoUtils.getPercentHeightSize(5);
                paramsRepeat.topMargin = chipTop - high - high - AutoUtils.getPercentHeightSize(5);
            }

            sureText.setLayoutParams(paramsSure);
            repeatText.setLayoutParams(paramsRepeat);
            cancelText.setLayoutParams(paramsCancel);
        }
    }

    public void setOperationButton(View.OnClickListener clickSure, View.OnClickListener clickCancel, View.OnClickListener clickRepeat) {
        setOperationButton(0, clickSure, clickCancel, clickRepeat);
    }

    public void setOperationWH(int width, int height) {
        this.operationWidth = width;
        this.operationHeight = height;

    }

    @SuppressLint("RtlHardcoded")
    public void setOperationButton(int x, View.OnClickListener clickSure, View.OnClickListener clickCancel, View.OnClickListener clickRepaet) {
        int w = AutoUtils.getPercentHeightSize(52);
        int h = AutoUtils.getPercentHeightSize(52);
        if (baseActivity instanceof DragonTigerActivity){
             w = AutoUtils.getPercentHeightSize(40);
             h = AutoUtils.getPercentHeightSize(40);
        }
        if (operationWidth > 0 && operationHeight > 0) {
            w = AutoUtils.getPercentHeightSize(operationWidth);
            h = AutoUtils.getPercentHeightSize(operationHeight);
        }
        if (x == 0)
            x = AutoUtils.getPercentHeightSize(25);
        int y = 0;
        firstIndex = firstIndex + 3;

        cancelText = new TextView(context);
        repeatText = new TextView(context);
        sureText = new TextView(context);
        FrameLayout.LayoutParams paramsTextCancel = new FrameLayout.LayoutParams(w, h);
        FrameLayout.LayoutParams paramsTextRepeat = new FrameLayout.LayoutParams(w, h);
        FrameLayout.LayoutParams paramsTextSure = new FrameLayout.LayoutParams(w, h);
        if (operationWidth > 0 && operationHeight > 0) {
            paramsTextCancel.gravity = Gravity.LEFT;
            paramsTextRepeat.gravity = Gravity.CENTER;
            paramsTextSure.gravity = Gravity.RIGHT;
        } else {
            int margin = 52;
            if (baseActivity instanceof DragonTigerActivity){
                margin = 35;
            }
            paramsTextCancel.rightMargin = x + margin;
            paramsTextCancel.gravity = chipGravity;
            paramsTextRepeat.rightMargin = 0;
            paramsTextRepeat.gravity = chipGravity;
            paramsTextSure.leftMargin = x + margin;
            paramsTextSure.gravity = chipGravity;
            paramsTextCancel.bottomMargin = AutoUtils.getPercentHeightSize(15);
            paramsTextRepeat.bottomMargin = AutoUtils.getPercentHeightSize(15);
            paramsTextSure.bottomMargin = AutoUtils.getPercentHeightSize(15);
        }

        cancelText.setLayoutParams(paramsTextCancel);
        repeatText.setLayoutParams(paramsTextRepeat);
        sureText.setLayoutParams(paramsTextSure);
        sureText.setBackgroundResource(R.mipmap.tick_white_oval);
        repeatText.setBackgroundResource(R.mipmap.bg_repeat);
        cancelText.setBackgroundResource(R.mipmap.cross_red_oval);
        framelayout.setClipChildren(false);
        framelayout.addView(cancelText);
        framelayout.addView(repeatText);
        framelayout.addView(sureText);
        cancelText.setOnClickListener(clickCancel);
        repeatText.setOnClickListener(clickRepaet);
        sureText.setOnClickListener(clickSure);
        cancelText.setVisibility(View.GONE);
        repeatText.setVisibility(View.GONE);
        sureText.setVisibility(View.GONE);
    }

    public void setOperationButtonDisplay(boolean show) {
        isButtonShow = show;
        if (cancelText != null) {
            if (show) {
                cancelText.setVisibility(View.VISIBLE);
                repeatText.setVisibility(View.VISIBLE);
                sureText.setVisibility(View.VISIBLE);
            } else {
                cancelText.setVisibility(View.GONE);
                repeatText.setVisibility(View.GONE);
                sureText.setVisibility(View.GONE);
            }
        }
    }
}
