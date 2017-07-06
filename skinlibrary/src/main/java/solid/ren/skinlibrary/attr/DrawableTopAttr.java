package solid.ren.skinlibrary.attr;

import android.view.View;
import android.widget.TextView;

import solid.ren.skinlibrary.attr.base.SkinAttr;
import solid.ren.skinlibrary.utils.SkinResourcesUtils;

/**
 * Created by _SOLID
 * Date:2017/2/15
 * Time:17:39
 * Desc:
 */

public class DrawableTopAttr extends SkinAttr {
    @Override
    public void apply(View view) {
        if (view instanceof TextView) {
            TextView iv = (TextView) view;
            if (isDrawable()) {
                iv.setCompoundDrawablesWithIntrinsicBounds(null,SkinResourcesUtils.getDrawable(attrValueRefId),null,null);
            }
        }
    }
}
