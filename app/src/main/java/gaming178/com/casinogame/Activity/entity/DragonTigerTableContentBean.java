package gaming178.com.casinogame.Activity.entity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DragonTigerTableContentBean {
    private int tableId;
    private View contentView;
    private TextView tvDragonPoint;
    private TextView tvTigerPoint;
    private LinearLayout llResult;
    private ImageView imgDragon;
    private ImageView imgTiger;
    private LinearLayout llDragonParent;
    private LinearLayout llTigerParent;
    private View resultWinView;
    private FrameLayout flTableDragon;
    private FrameLayout flTableTiger;
    private FrameLayout flTableTie;
    private String DragonTigerGameNumber;
    private boolean isDragonTigerOpenPoker;
    private boolean isDragonTigerGetResult;

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

    public TextView getTvDragonPoint() {
        return tvDragonPoint;
    }

    public void setTvDragonPoint(TextView tvDragonPoint) {
        this.tvDragonPoint = tvDragonPoint;
    }

    public TextView getTvTigerPoint() {
        return tvTigerPoint;
    }

    public void setTvTigerPoint(TextView tvTigerPoint) {
        this.tvTigerPoint = tvTigerPoint;
    }

    public LinearLayout getLlResult() {
        return llResult;
    }

    public void setLlResult(LinearLayout llResult) {
        this.llResult = llResult;
    }

    public ImageView getImgDragon() {
        return imgDragon;
    }

    public void setImgDragon(ImageView imgDragon) {
        this.imgDragon = imgDragon;
    }

    public ImageView getImgTiger() {
        return imgTiger;
    }

    public void setImgTiger(ImageView imgTiger) {
        this.imgTiger = imgTiger;
    }

    public LinearLayout getLlDragonParent() {
        return llDragonParent;
    }

    public void setLlDragonParent(LinearLayout llDragonParent) {
        this.llDragonParent = llDragonParent;
    }

    public LinearLayout getLlTigerParent() {
        return llTigerParent;
    }

    public void setLlTigerParent(LinearLayout llTigerParent) {
        this.llTigerParent = llTigerParent;
    }

    public View getResultWinView() {
        return resultWinView;
    }

    public void setResultWinView(View resultWinView) {
        this.resultWinView = resultWinView;
    }

    public FrameLayout getFlTableDragon() {
        return flTableDragon;
    }

    public void setFlTableDragon(FrameLayout flTableDragon) {
        this.flTableDragon = flTableDragon;
    }

    public FrameLayout getFlTableTiger() {
        return flTableTiger;
    }

    public void setFlTableTiger(FrameLayout flTableTiger) {
        this.flTableTiger = flTableTiger;
    }

    public FrameLayout getFlTableTie() {
        return flTableTie;
    }

    public void setFlTableTie(FrameLayout flTableTie) {
        this.flTableTie = flTableTie;
    }

    public String getDragonTigerGameNumber() {
        return DragonTigerGameNumber;
    }

    public void setDragonTigerGameNumber(String dragonTigerGameNumber) {
        DragonTigerGameNumber = dragonTigerGameNumber;
    }

    public boolean isDragonTigerOpenPoker() {
        return isDragonTigerOpenPoker;
    }

    public void setDragonTigerOpenPoker(boolean dragonTigerOpenPoker) {
        isDragonTigerOpenPoker = dragonTigerOpenPoker;
    }

    public boolean isDragonTigerGetResult() {
        return isDragonTigerGetResult;
    }

    public void setDragonTigerGetResult(boolean dragonTigerGetResult) {
        isDragonTigerGetResult = dragonTigerGetResult;
    }
}
