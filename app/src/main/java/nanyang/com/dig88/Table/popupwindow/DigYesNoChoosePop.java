package nanyang.com.dig88.Table.popupwindow;

import android.content.Context;
import android.view.View;

import nanyang.com.dig88.R;
import xs.com.mylibrary.popupwindow.BaseYseNoChoosePopupwindow;

/**
 * Created by Administrator on 2016/1/7.
 */
public abstract class DigYesNoChoosePop extends BaseYseNoChoosePopupwindow {
    public DigYesNoChoosePop(Context context, View v) {
        super(context, v);
        getChooseSureTv().setText(context.getString(R.string.sure));
        getChooseCancelTv().setText(context.getString(R.string.cancel));
        getChooseTitleTv().setText(context.getString(R.string.confirm_or_not));
        getChooseMessage().setText(getContentString());
    }

    protected abstract String getContentString();

}
