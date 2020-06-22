package gaming178.com.mylibrary.base;

import android.view.View;

/**
 * @Filename ContentViewImp.java
 * @Description
 * @Version 1.0
 * @Author xs
 * @Email 197585312@qq.com
 * @History <li>Author: xs</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public interface ItemCLickImp<T> {

    void itemCLick(View view, T t, int position);

}
