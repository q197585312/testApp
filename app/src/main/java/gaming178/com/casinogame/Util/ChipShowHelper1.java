package gaming178.com.casinogame.Util;

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
import gaming178.com.casinogame.Bean.ChipBean;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;
import gaming178.com.mylibrary.myview.View.AutofitTextView;

/**
 * Created by Administrator on 2016/4/18.
 */
public class ChipShowHelper1 {
    List<ChipBean> chips;
    FrameLayout framelayout;
    Context context;
    TextView cancelText;
    TextView sureText;
    int moneyCount = 0;
    private boolean isButtonShow;
    private int operationWidth;
    private int operationHeight;
    private int textGravity = Gravity.CENTER;
    private int bottomMargin = Gravity.CENTER;
    private int chipTop = 0;
    private int chipGravity = Gravity.CENTER;
    private int firstIndex = 0;
    private int topMargin = 5;
    private int moneyTipsTextSize = 7;

    public ChipShowHelper1(Context ctx, FrameLayout framelayout, List<ChipBean> chips) {
        this.chips = chips;
        this.framelayout = framelayout;
        this.context = ctx;
        this.firstIndex = framelayout.getChildCount();
        this.topMargin =  AutoUtils.getPercentHeightSize(5);
    }

    public void setTextGravity(int textGravity) {
        this.textGravity = textGravity;
    }

    public void setChipGravity(int chipGravity) {
        this.chipGravity = chipGravity;
    }

    public int getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(int moneyCount) {
        this.moneyCount = moneyCount;
    }

    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }

    public void setTopMargin(int topMargin) {
//        this.topMargin = topMargin;

    }

    public void setMoneyTipsTextSize(int size) {
        moneyTipsTextSize = size;
    }

    public void showChipView(int x, int y, int with, int high, int chipIndex, int pos) {
        if (with > framelayout.getWidth())
            with = framelayout.getWidth();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(with, high);
        ImageView imageView = new ImageView(context);
        params.leftMargin = x;

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
        tipsX = 0;
        x = 0;
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
        paramsText.leftMargin = 0;
        paramsText.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        textView.setBackgroundResource(R.drawable.rectangle_blue_table_corners5);
        textView.setTextColor(context.getResources().getColor(R.color.white));
        textView.setTextSize(moneyTipsTextSize);
        textView.setTypeface(Typeface.SERIF);
        textView.setGravity(Gravity.CENTER);
        //	textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        TextPaint paint = textView.getPaint();
        paint.setFakeBoldText(true);
        textView.setText("" + money);
        paramsText.bottomMargin = AutoUtils.getPercentHeightSize(20);

        if (textGravity == Gravity.TOP)
            paramsText.topMargin = chipTop - high;
        textView.setLayoutParams(paramsText);
        framelayout.addView(textView);
        if (cancelText != null && sureText != null && operationHeight < 1 && operationWidth < 1) {

            FrameLayout.LayoutParams paramsCancel = (FrameLayout.LayoutParams) cancelText.getLayoutParams();
            FrameLayout.LayoutParams paramsSure = (FrameLayout.LayoutParams) sureText.getLayoutParams();
            paramsSure.topMargin = chipTop - high;
            paramsCancel.topMargin = chipTop - high;
            if (textGravity == Gravity.TOP) {
                paramsSure.topMargin = chipTop - high - high - AutoUtils.getPercentHeightSize(5);
                paramsCancel.topMargin = chipTop - high - high - AutoUtils.getPercentHeightSize(5);
            }

            sureText.setLayoutParams(paramsSure);
            cancelText.setLayoutParams(paramsCancel);
        }
    }

    public void setOperationButton(View.OnClickListener clickSure, View.OnClickListener clickCancel) {
        setOperationButton(0, clickSure, clickCancel);
    }

    public void setOperationWH(int width, int height) {
        this.operationWidth = width;
        this.operationHeight = height;

    }

    public void setOperationButton(int x, View.OnClickListener clickSure, View.OnClickListener clickCancel) {
        int w = AutoUtils.getPercentHeightSize(30);
        int h = AutoUtils.getPercentHeightSize(30);
        if (operationWidth > 0 && operationHeight > 0) {
            w = AutoUtils.getPercentHeightSize(operationWidth);
            h = AutoUtils.getPercentHeightSize(operationHeight);
        }
        if (x == 0)
            x = AutoUtils.getPercentHeightSize(25);
        int y = 0;
        firstIndex = firstIndex + 2;

        cancelText = new TextView(context);
        sureText = new TextView(context);
        FrameLayout.LayoutParams paramsTextCancel = new FrameLayout.LayoutParams(w, h);
        FrameLayout.LayoutParams paramsTextSure = new FrameLayout.LayoutParams(w, h);
        if (operationWidth > 0 && operationHeight > 0) {
            paramsTextCancel.gravity = Gravity.LEFT;
            paramsTextSure.gravity = Gravity.RIGHT;
        } else {
            paramsTextCancel.rightMargin = x - 4;
            paramsTextCancel.gravity = chipGravity;
            paramsTextSure.leftMargin = x + 4;
            paramsTextSure.gravity = chipGravity;
            paramsTextCancel.bottomMargin = AutoUtils.getPercentHeightSize(10);
            paramsTextSure.bottomMargin = AutoUtils.getPercentHeightSize(10);
        }


        cancelText.setLayoutParams(paramsTextCancel);
        sureText.setLayoutParams(paramsTextSure);
        sureText.setBackgroundResource(R.mipmap.gd_tick_white_oval);
        cancelText.setBackgroundResource(R.mipmap.gd_cross_red_oval);
        framelayout.setClipChildren(false);
        framelayout.addView(cancelText);
        framelayout.addView(sureText);
        cancelText.setOnClickListener(clickCancel);
        sureText.setOnClickListener(clickSure);
        cancelText.setVisibility(View.GONE);
        sureText.setVisibility(View.GONE);
    }

    public void setOperationButton(int x, View.OnClickListener clickSure, View.OnClickListener clickCancel
            , FrameLayout.LayoutParams sureParams, FrameLayout.LayoutParams cancelParams
    ) {

        if (x == 0)
            x = AutoUtils.getPercentHeightSize(25);
        int y = 0;
        firstIndex = firstIndex + 2;

        cancelText = new TextView(context);
        sureText = new TextView(context);

        cancelText.setLayoutParams(cancelParams);
        sureText.setLayoutParams(sureParams);
        sureText.setBackgroundResource(R.mipmap.gd_tick_white_oval);
        cancelText.setBackgroundResource(R.mipmap.gd_cross_red_oval);
        framelayout.setClipChildren(false);
        framelayout.addView(cancelText);
        framelayout.addView(sureText);
        cancelText.setOnClickListener(clickCancel);
        sureText.setOnClickListener(clickSure);
        cancelText.setVisibility(View.GONE);
        sureText.setVisibility(View.GONE);
    }

    public void setOperationButtonDisplay(boolean show) {
        isButtonShow = show;
        if (cancelText != null) {
            if (show) {
                cancelText.setVisibility(View.VISIBLE);
                sureText.setVisibility(View.VISIBLE);
            } else {
                cancelText.setVisibility(View.GONE);
                sureText.setVisibility(View.GONE);
            }
        }
    }


    public void setBottomMargin(int bottomMargin) {
        this.bottomMargin = bottomMargin;
    }
}
