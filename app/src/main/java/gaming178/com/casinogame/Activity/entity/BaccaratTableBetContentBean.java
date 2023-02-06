package gaming178.com.casinogame.Activity.entity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BaccaratTableBetContentBean {
    private int tableId;
    private View contentView;
    private TextView tvPlayerPoint;
    private TextView tvBankerPoint;
    private LinearLayout llResult;
    private ImageView imgPlayer1;
    private ImageView imgPlayer2;
    private ImageView imgPlayer3;
    private ImageView imgBanker1;
    private ImageView imgBanker2;
    private ImageView imgBanker3;
    private LinearLayout llPlayerParent;
    private LinearLayout llBankerParent;
    private View resultWinView;
    private FrameLayout flTablePlayer;
    private FrameLayout flTableBanker;
    private FrameLayout flTableTie;
    private FrameLayout flTablePP;
    private FrameLayout flTableBP;
    private String BaccaratGameNumber;
    private boolean isBaccaratOpenPoker;
    private boolean isBaccaratGetResult;
    private boolean isCanBet;
    private TextView tvTableNumber;
    private TextView tvBetHint;
    private TextView tvPlayerName;
    private TextView tvBankerName;
    private FrameLayout flBetBg;

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

    public TextView getTvPlayerPoint() {
        return tvPlayerPoint;
    }

    public void setTvPlayerPoint(TextView tvPlayerPoint) {
        this.tvPlayerPoint = tvPlayerPoint;
    }

    public TextView getTvBankerPoint() {
        return tvBankerPoint;
    }

    public void setTvBankerPoint(TextView tvBankerPoint) {
        this.tvBankerPoint = tvBankerPoint;
    }

    public ImageView getImgPlayer1() {
        return imgPlayer1;
    }

    public void setImgPlayer1(ImageView imgPlayer1) {
        this.imgPlayer1 = imgPlayer1;
    }

    public ImageView getImgPlayer2() {
        return imgPlayer2;
    }

    public void setImgPlayer2(ImageView imgPlayer2) {
        this.imgPlayer2 = imgPlayer2;
    }

    public ImageView getImgPlayer3() {
        return imgPlayer3;
    }

    public void setImgPlayer3(ImageView imgPlayer3) {
        this.imgPlayer3 = imgPlayer3;
    }

    public ImageView getImgBanker1() {
        return imgBanker1;
    }

    public void setImgBanker1(ImageView imgBanker1) {
        this.imgBanker1 = imgBanker1;
    }

    public ImageView getImgBanker2() {
        return imgBanker2;
    }

    public void setImgBanker2(ImageView imgBanker2) {
        this.imgBanker2 = imgBanker2;
    }

    public ImageView getImgBanker3() {
        return imgBanker3;
    }

    public void setImgBanker3(ImageView imgBanker3) {
        this.imgBanker3 = imgBanker3;
    }

    public LinearLayout getLlResult() {
        return llResult;
    }

    public void setLlResult(LinearLayout llResult) {
        this.llResult = llResult;
    }

    public LinearLayout getLlPlayerParent() {
        return llPlayerParent;
    }

    public void setLlPlayerParent(LinearLayout llPlayerParent) {
        this.llPlayerParent = llPlayerParent;
    }

    public LinearLayout getLlBankerParent() {
        return llBankerParent;
    }

    public void setLlBankerParent(LinearLayout llBankerParent) {
        this.llBankerParent = llBankerParent;
    }

    public View getResultWinView() {
        return resultWinView;
    }

    public void setResultWinView(View resultWinView) {
        this.resultWinView = resultWinView;
    }

    public FrameLayout getFlTablePlayer() {
        return flTablePlayer;
    }

    public void setFlTablePlayer(FrameLayout flTablePlayer) {
        this.flTablePlayer = flTablePlayer;
    }

    public FrameLayout getFlTableBanker() {
        return flTableBanker;
    }

    public void setFlTableBanker(FrameLayout flTableBanker) {
        this.flTableBanker = flTableBanker;
    }

    public FrameLayout getFlTableTie() {
        return flTableTie;
    }

    public void setFlTableTie(FrameLayout flTableTie) {
        this.flTableTie = flTableTie;
    }

    public FrameLayout getFlTablePP() {
        return flTablePP;
    }

    public void setFlTablePP(FrameLayout flTablePP) {
        this.flTablePP = flTablePP;
    }

    public FrameLayout getFlTableBP() {
        return flTableBP;
    }

    public void setFlTableBP(FrameLayout flTableBP) {
        this.flTableBP = flTableBP;
    }

    public String getBaccaratGameNumber() {
        return BaccaratGameNumber;
    }

    public void setBaccaratGameNumber(String baccaratGameNumber) {
        BaccaratGameNumber = baccaratGameNumber;
    }

    public boolean isBaccaratOpenPoker() {
        return isBaccaratOpenPoker;
    }

    public void setBaccaratOpenPoker(boolean baccaratOpenPoker) {
        isBaccaratOpenPoker = baccaratOpenPoker;
    }

    public boolean isBaccaratGetResult() {
        return isBaccaratGetResult;
    }

    public void setBaccaratGetResult(boolean baccaratGetResult) {
        isBaccaratGetResult = baccaratGetResult;
    }

    public boolean isCanBet() {
        return isCanBet;
    }

    public void setCanBet(boolean canBet) {
        isCanBet = canBet;
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

    public TextView getTvPlayerName() {
        return tvPlayerName;
    }

    public void setTvPlayerName(TextView tvPlayerName) {
        this.tvPlayerName = tvPlayerName;
    }

    public TextView getTvBankerName() {
        return tvBankerName;
    }

    public void setTvBankerName(TextView tvBankerName) {
        this.tvBankerName = tvBankerName;
    }

    public FrameLayout getFlBetBg() {
        return flBetBg;
    }

    public void setFlBetBg(FrameLayout flBetBg) {
        this.flBetBg = flBetBg;
    }
}
