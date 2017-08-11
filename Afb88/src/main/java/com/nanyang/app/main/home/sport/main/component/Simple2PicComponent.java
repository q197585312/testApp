package com.nanyang.app.main.home.sport.main.component;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.guoqi.highlightview.Component;
import com.nanyang.app.R;
import com.unkonw.testapp.libs.utils.SystemTool;

import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by binIoter on 16/6/17.
 */
public class Simple2PicComponent implements Component {

  @Override public View getView(LayoutInflater inflater) {

    LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.gudile_layer_pic2, null);
    ll.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {

      }
    });
      View view2 = ll.findViewById(R.id.guide_content_iv);
      DeviceUtils.getScreenPix()

      return ll;
  }

  @Override public int getAnchor() {
    return Component.ANCHOR_BOTTOM;
  }

  @Override public int getFitPosition() {
    return Component.CIRCLE;
  }

  @Override public int getXOffset() {
    return 0;
  }

  @Override public int getYOffset() {
    return 10;
  }
}
