package gaming178.com.mylibrary.myview.myImageView;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class ErnieAnimationImageView extends ImageView{
    public ErnieAnimationImageView(Context context) {
        super(context);
    }
    List<View> animationViews=new ArrayList<>();
    public void addAnimationView(View v){
        animationViews.add(v);
    }

}
