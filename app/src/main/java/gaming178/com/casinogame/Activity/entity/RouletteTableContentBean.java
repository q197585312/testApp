package gaming178.com.casinogame.Activity.entity;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RouletteTableContentBean {
    private int tableId;
    private View contentView;
    private FrameLayout flEven;
    private FrameLayout flZero;
    private FrameLayout flOdd;
    private ImageView imgSingle1_12;
    private ImageView imgSingle13_24;
    private ImageView imgSingle25_36;
    private ImageView imgSingle1_18;
    private ImageView imgSingle19_36;
    private ImageView imgSingleRed;
    private ImageView imgSingleBlack;
    private FrameLayout flSingle1_12;
    private FrameLayout flSingle13_24;
    private FrameLayout flSingle25_36;
    private FrameLayout flSingle1_18;
    private FrameLayout flSingle19_36;
    private FrameLayout flRed;
    private FrameLayout flBlack;
    private FrameLayout flResult;
    private TextView tvNumber;
    private TextView tvRedBlack;
    private TextView tvOddEven;
    private FrameLayout flBetButton;
    private String rouletteGameNumber;
    private boolean rouletteOpenResult;
    private boolean rouletteGetResult;
    private boolean isCanBet;
    private AnimationDrawable AnimationEven;
    private AnimationDrawable AnimationZero;
    private AnimationDrawable AnimationOdd;
    private AnimationDrawable Animation1_12;
    private AnimationDrawable Animation13_24;
    private AnimationDrawable Animation25_36;
    private AnimationDrawable Animation1_18;
    private AnimationDrawable Animation19_36;
    private AnimationDrawable AnimationRed;
    private AnimationDrawable AnimationBlack;
    private List<AnimationDrawable> animationList = new ArrayList<>();

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

    public FrameLayout getFlEven() {
        return flEven;
    }

    public void setFlEven(FrameLayout flEven) {
        this.flEven = flEven;
    }

    public FrameLayout getFlZero() {
        return flZero;
    }

    public void setFlZero(FrameLayout flZero) {
        this.flZero = flZero;
    }

    public FrameLayout getFlOdd() {
        return flOdd;
    }

    public void setFlOdd(FrameLayout flOdd) {
        this.flOdd = flOdd;
    }

    public ImageView getImgSingle1_12() {
        return imgSingle1_12;
    }

    public void setImgSingle1_12(ImageView imgSingle1_12) {
        this.imgSingle1_12 = imgSingle1_12;
    }

    public ImageView getImgSingle13_24() {
        return imgSingle13_24;
    }

    public void setImgSingle13_24(ImageView imgSingle13_24) {
        this.imgSingle13_24 = imgSingle13_24;
    }

    public ImageView getImgSingle25_36() {
        return imgSingle25_36;
    }

    public void setImgSingle25_36(ImageView imgSingle25_36) {
        this.imgSingle25_36 = imgSingle25_36;
    }

    public ImageView getImgSingle1_18() {
        return imgSingle1_18;
    }

    public void setImgSingle1_18(ImageView imgSingle1_18) {
        this.imgSingle1_18 = imgSingle1_18;
    }

    public ImageView getImgSingle19_36() {
        return imgSingle19_36;
    }

    public void setImgSingle19_36(ImageView imgSingle19_36) {
        this.imgSingle19_36 = imgSingle19_36;
    }

    public ImageView getImgSingleRed() {
        return imgSingleRed;
    }

    public void setImgSingleRed(ImageView imgSingleRed) {
        this.imgSingleRed = imgSingleRed;
    }

    public ImageView getImgSingleBlack() {
        return imgSingleBlack;
    }

    public void setImgSingleBlack(ImageView imgSingleBlack) {
        this.imgSingleBlack = imgSingleBlack;
    }

    public FrameLayout getFlSingle1_12() {
        return flSingle1_12;
    }

    public void setFlSingle1_12(FrameLayout flSingle1_12) {
        this.flSingle1_12 = flSingle1_12;
    }

    public FrameLayout getFlSingle13_24() {
        return flSingle13_24;
    }

    public void setFlSingle13_24(FrameLayout flSingle13_24) {
        this.flSingle13_24 = flSingle13_24;
    }

    public FrameLayout getFlSingle25_36() {
        return flSingle25_36;
    }

    public void setFlSingle25_36(FrameLayout flSingle25_36) {
        this.flSingle25_36 = flSingle25_36;
    }

    public FrameLayout getFlSingle1_18() {
        return flSingle1_18;
    }

    public void setFlSingle1_18(FrameLayout flSingle1_18) {
        this.flSingle1_18 = flSingle1_18;
    }

    public FrameLayout getFlSingle19_36() {
        return flSingle19_36;
    }

    public void setFlSingle19_36(FrameLayout flSingle19_36) {
        this.flSingle19_36 = flSingle19_36;
    }

    public FrameLayout getFlResult() {
        return flResult;
    }

    public void setFlResult(FrameLayout flResult) {
        this.flResult = flResult;
    }

    public TextView getTvNumber() {
        return tvNumber;
    }

    public void setTvNumber(TextView tvNumber) {
        this.tvNumber = tvNumber;
    }

    public TextView getTvRedBlack() {
        return tvRedBlack;
    }

    public void setTvRedBlack(TextView tvRedBlack) {
        this.tvRedBlack = tvRedBlack;
    }

    public TextView getTvOddEven() {
        return tvOddEven;
    }

    public void setTvOddEven(TextView tvOddEven) {
        this.tvOddEven = tvOddEven;
    }

    public FrameLayout getFlBetButton() {
        return flBetButton;
    }

    public void setFlBetButton(FrameLayout flBetButton) {
        this.flBetButton = flBetButton;
    }

    public String getRouletteGameNumber() {
        return rouletteGameNumber;
    }

    public void setRouletteGameNumber(String rouletteGameNumber) {
        this.rouletteGameNumber = rouletteGameNumber;
    }

    public boolean isRouletteOpenResult() {
        return rouletteOpenResult;
    }

    public void setRouletteOpenResult(boolean rouletteOpenResult) {
        this.rouletteOpenResult = rouletteOpenResult;
    }

    public boolean isRouletteGetResult() {
        return rouletteGetResult;
    }

    public void setRouletteGetResult(boolean rouletteGetResult) {
        this.rouletteGetResult = rouletteGetResult;
    }

    public FrameLayout getFlRed() {
        return flRed;
    }

    public void setFlRed(FrameLayout flRed) {
        this.flRed = flRed;
    }

    public FrameLayout getFlBlack() {
        return flBlack;
    }

    public void setFlBlack(FrameLayout flBlack) {
        this.flBlack = flBlack;
    }

    public boolean isCanBet() {
        return isCanBet;
    }

    public void setCanBet(boolean canBet) {
        isCanBet = canBet;
    }

    public AnimationDrawable getAnimationEven() {
        return AnimationEven;
    }

    public void setAnimationEven(AnimationDrawable animationEven) {
        AnimationEven = animationEven;
    }

    public AnimationDrawable getAnimationZero() {
        return AnimationZero;
    }

    public void setAnimationZero(AnimationDrawable animationZero) {
        AnimationZero = animationZero;
    }

    public AnimationDrawable getAnimationOdd() {
        return AnimationOdd;
    }

    public void setAnimationOdd(AnimationDrawable animationOdd) {
        AnimationOdd = animationOdd;
    }

    public AnimationDrawable getAnimation1_12() {
        return Animation1_12;
    }

    public void setAnimation1_12(AnimationDrawable animation1_12) {
        Animation1_12 = animation1_12;
    }

    public AnimationDrawable getAnimation13_24() {
        return Animation13_24;
    }

    public void setAnimation13_24(AnimationDrawable animation13_24) {
        Animation13_24 = animation13_24;
    }

    public AnimationDrawable getAnimation25_36() {
        return Animation25_36;
    }

    public void setAnimation25_36(AnimationDrawable animation25_36) {
        Animation25_36 = animation25_36;
    }

    public AnimationDrawable getAnimation1_18() {
        return Animation1_18;
    }

    public void setAnimation1_18(AnimationDrawable animation1_18) {
        Animation1_18 = animation1_18;
    }

    public AnimationDrawable getAnimation19_36() {
        return Animation19_36;
    }

    public void setAnimation19_36(AnimationDrawable animation19_36) {
        Animation19_36 = animation19_36;
    }

    public AnimationDrawable getAnimationRed() {
        return AnimationRed;
    }

    public void setAnimationRed(AnimationDrawable animationRed) {
        AnimationRed = animationRed;
    }

    public AnimationDrawable getAnimationBlack() {
        return AnimationBlack;
    }

    public void setAnimationBlack(AnimationDrawable animationBlack) {
        AnimationBlack = animationBlack;
    }

    public List<AnimationDrawable> getAnimationList() {
        return animationList;
    }
}
