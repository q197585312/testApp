package gaming178.com.casinogame.Activity.entity;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SicboTableContentBean {
    private int tableId;
    private View contentView;
    private FrameLayout flBig;
    private FrameLayout flAny;
    private FrameLayout flSmall;
    private ImageView imgBig;
    private ImageView imgAny;
    private ImageView imgSmall;
    private ImageView imgSingle1;
    private ImageView imgSingle2;
    private ImageView imgSingle3;
    private ImageView imgSingle4;
    private ImageView imgSingle5;
    private ImageView imgSingle6;
    private FrameLayout flSingle1;
    private FrameLayout flSingle2;
    private FrameLayout flSingle3;
    private FrameLayout flSingle4;
    private FrameLayout flSingle5;
    private FrameLayout flSingle6;
    private FrameLayout flResult;
    private ImageView imgResult1;
    private ImageView imgResult2;
    private ImageView imgResult3;
    private TextView tvResultPoint;
    private TextView tvResultBigSmall;
    private TextView tvResultOddEven;
    private FrameLayout flBetButton;
    private String sicboGameNumber;
    private boolean isSicboOpenResult;
    private boolean isSicboGetResult;
    private boolean isCanBet;
    private AnimationDrawable AnimationBig;
    private AnimationDrawable AnimationAny;
    private AnimationDrawable AnimationSmall;
    private AnimationDrawable Animation1;
    private AnimationDrawable Animation2;
    private AnimationDrawable Animation3;
    private AnimationDrawable Animation4;
    private AnimationDrawable Animation5;
    private AnimationDrawable Animation6;
    private List<AnimationDrawable> animationList = new ArrayList<>();
    private TextView tvTableNumber;
    private TextView tvBetHint;

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public View getContentView() {
        return contentView;
    }

    public void setContentView(View contentView) {
        this.contentView = contentView;
    }

    public FrameLayout getFlBig() {
        return flBig;
    }

    public void setFlBig(FrameLayout flBig) {
        this.flBig = flBig;
    }

    public FrameLayout getFlAny() {
        return flAny;
    }

    public void setFlAny(FrameLayout flAny) {
        this.flAny = flAny;
    }

    public FrameLayout getFlSmall() {
        return flSmall;
    }

    public void setFlSmall(FrameLayout flSmall) {
        this.flSmall = flSmall;
    }

    public ImageView getImgSingle1() {
        return imgSingle1;
    }

    public void setImgSingle1(ImageView imgSingle1) {
        this.imgSingle1 = imgSingle1;
    }

    public ImageView getImgSingle2() {
        return imgSingle2;
    }

    public void setImgSingle2(ImageView imgSingle2) {
        this.imgSingle2 = imgSingle2;
    }

    public ImageView getImgSingle3() {
        return imgSingle3;
    }

    public void setImgSingle3(ImageView imgSingle3) {
        this.imgSingle3 = imgSingle3;
    }

    public ImageView getImgSingle4() {
        return imgSingle4;
    }

    public void setImgSingle4(ImageView imgSingle4) {
        this.imgSingle4 = imgSingle4;
    }

    public ImageView getImgSingle5() {
        return imgSingle5;
    }

    public void setImgSingle5(ImageView imgSingle5) {
        this.imgSingle5 = imgSingle5;
    }

    public ImageView getImgSingle6() {
        return imgSingle6;
    }

    public void setImgSingle6(ImageView imgSingle6) {
        this.imgSingle6 = imgSingle6;
    }

    public FrameLayout getFlSingle1() {
        return flSingle1;
    }

    public void setFlSingle1(FrameLayout flSingle1) {
        this.flSingle1 = flSingle1;
    }

    public FrameLayout getFlSingle2() {
        return flSingle2;
    }

    public void setFlSingle2(FrameLayout flSingle2) {
        this.flSingle2 = flSingle2;
    }

    public FrameLayout getFlSingle3() {
        return flSingle3;
    }

    public void setFlSingle3(FrameLayout flSingle3) {
        this.flSingle3 = flSingle3;
    }

    public FrameLayout getFlSingle4() {
        return flSingle4;
    }

    public void setFlSingle4(FrameLayout flSingle4) {
        this.flSingle4 = flSingle4;
    }

    public FrameLayout getFlSingle5() {
        return flSingle5;
    }

    public void setFlSingle5(FrameLayout flSingle5) {
        this.flSingle5 = flSingle5;
    }

    public FrameLayout getFlSingle6() {
        return flSingle6;
    }

    public void setFlSingle6(FrameLayout flSingle6) {
        this.flSingle6 = flSingle6;
    }

    public FrameLayout getFlResult() {
        return flResult;
    }

    public void setFlResult(FrameLayout flResult) {
        this.flResult = flResult;
    }

    public ImageView getImgResult1() {
        return imgResult1;
    }

    public void setImgResult1(ImageView imgResult1) {
        this.imgResult1 = imgResult1;
    }

    public ImageView getImgResult2() {
        return imgResult2;
    }

    public void setImgResult2(ImageView imgResult2) {
        this.imgResult2 = imgResult2;
    }

    public ImageView getImgResult3() {
        return imgResult3;
    }

    public void setImgResult3(ImageView imgResult3) {
        this.imgResult3 = imgResult3;
    }

    public TextView getTvResultPoint() {
        return tvResultPoint;
    }

    public void setTvResultPoint(TextView tvResultPoint) {
        this.tvResultPoint = tvResultPoint;
    }

    public TextView getTvResultBigSmall() {
        return tvResultBigSmall;
    }

    public void setTvResultBigSmall(TextView tvResultBigSmall) {
        this.tvResultBigSmall = tvResultBigSmall;
    }

    public TextView getTvResultOddEven() {
        return tvResultOddEven;
    }

    public void setTvResultOddEven(TextView tvResultOddEven) {
        this.tvResultOddEven = tvResultOddEven;
    }

    public FrameLayout getFlBetButton() {
        return flBetButton;
    }

    public void setFlBetButton(FrameLayout flBetButton) {
        this.flBetButton = flBetButton;
    }

    public String getSicboGameNumber() {
        return sicboGameNumber;
    }

    public void setSicboGameNumber(String sicboGameNumber) {
        this.sicboGameNumber = sicboGameNumber;
    }

    public boolean isSicboOpenResult() {
        return isSicboOpenResult;
    }

    public void setSicboOpenResult(boolean sicboOpenResult) {
        isSicboOpenResult = sicboOpenResult;
    }

    public boolean isSicboGetResult() {
        return isSicboGetResult;
    }

    public void setSicboGetResult(boolean sicboGetResult) {
        isSicboGetResult = sicboGetResult;
    }

    public boolean isCanBet() {
        return isCanBet;
    }

    public void setCanBet(boolean canBet) {
        isCanBet = canBet;
    }

    public ImageView getImgBig() {
        return imgBig;
    }

    public void setImgBig(ImageView imgBig) {
        this.imgBig = imgBig;
    }

    public ImageView getImgAny() {
        return imgAny;
    }

    public void setImgAny(ImageView imgAny) {
        this.imgAny = imgAny;
    }

    public ImageView getImgSmall() {
        return imgSmall;
    }

    public void setImgSmall(ImageView imgSmall) {
        this.imgSmall = imgSmall;
    }

    public List<AnimationDrawable> getAnimationList() {
        return animationList;
    }

    public AnimationDrawable getAnimationBig() {
        return AnimationBig;
    }

    public void setAnimationBig(AnimationDrawable animationBig) {
        AnimationBig = animationBig;
    }

    public AnimationDrawable getAnimationAny() {
        return AnimationAny;
    }

    public void setAnimationAny(AnimationDrawable animationAny) {
        AnimationAny = animationAny;
    }

    public AnimationDrawable getAnimationSmall() {
        return AnimationSmall;
    }

    public void setAnimationSmall(AnimationDrawable animationSmall) {
        AnimationSmall = animationSmall;
    }

    public AnimationDrawable getAnimation1() {
        return Animation1;
    }

    public void setAnimation1(AnimationDrawable animation1) {
        Animation1 = animation1;
    }

    public AnimationDrawable getAnimation2() {
        return Animation2;
    }

    public void setAnimation2(AnimationDrawable animation2) {
        Animation2 = animation2;
    }

    public AnimationDrawable getAnimation3() {
        return Animation3;
    }

    public void setAnimation3(AnimationDrawable animation3) {
        Animation3 = animation3;
    }

    public AnimationDrawable getAnimation4() {
        return Animation4;
    }

    public void setAnimation4(AnimationDrawable animation4) {
        Animation4 = animation4;
    }

    public AnimationDrawable getAnimation5() {
        return Animation5;
    }

    public void setAnimation5(AnimationDrawable animation5) {
        Animation5 = animation5;
    }

    public AnimationDrawable getAnimation6() {
        return Animation6;
    }

    public void setAnimation6(AnimationDrawable animation6) {
        Animation6 = animation6;
    }

    public TextView getTvTableNumber() {
        return tvTableNumber;
    }

    public void setTvTableNumber(TextView tvTableNumber) {
        this.tvTableNumber = tvTableNumber;
    }

    public TextView getTvBetHint() {
        return tvBetHint;
    }

    public void setTvBetHint(TextView tvBetHint) {
        this.tvBetHint = tvBetHint;
    }
}
