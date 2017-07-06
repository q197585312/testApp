package solid.ren.skinlibrary.attr.base;

import java.util.HashMap;

import solid.ren.skinlibrary.attr.BackgroundAttr;
import solid.ren.skinlibrary.attr.DrawableBottomAttr;
import solid.ren.skinlibrary.attr.DrawableLeftAttr;
import solid.ren.skinlibrary.attr.DrawableRightAttr;
import solid.ren.skinlibrary.attr.DrawableTopAttr;
import solid.ren.skinlibrary.attr.ImageViewSrcAttr;
import solid.ren.skinlibrary.attr.TextColorAttr;

/**
 * Created by _SOLID
 * Date:2016/4/14
 * Time:9:47
 */
public class AttrFactory {

    private static HashMap<String, SkinAttr> sSupportAttr = new HashMap<>();

    static {
        sSupportAttr.put("background", new BackgroundAttr());
        sSupportAttr.put("textColor", new TextColorAttr());
        sSupportAttr.put("src", new ImageViewSrcAttr());
        sSupportAttr.put("drawableTop", new DrawableTopAttr());
        sSupportAttr.put("drawableRight", new DrawableRightAttr());
        sSupportAttr.put("drawableBottom", new DrawableBottomAttr());
        sSupportAttr.put("drawableLeft", new DrawableLeftAttr());
    }


    public static SkinAttr get(String attrName, int attrValueRefId, String attrValueRefName, String typeName) {
        SkinAttr mSkinAttr = sSupportAttr.get(attrName).clone();
        if (mSkinAttr == null) return null;
        mSkinAttr.attrName = attrName;
        mSkinAttr.attrValueRefId = attrValueRefId;
        mSkinAttr.attrValueRefName = attrValueRefName;
        mSkinAttr.attrValueTypeName = typeName;
        return mSkinAttr;
    }

    /**
     * check current attribute if can be support
     *
     * @param attrName attribute name
     * @return true : supported <br>
     * false: not supported
     */
    public static boolean isSupportedAttr(String attrName) {
        return sSupportAttr.containsKey(attrName);
    }

    /**
     * add support's attribute
     *
     * @param attrName attribute name
     * @param skinAttr skin attribute
     */
    public static void addSupportAttr(String attrName, SkinAttr skinAttr) {
        sSupportAttr.put(attrName, skinAttr);
    }
}
