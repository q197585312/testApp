package gaming178.com.mylibrary.base;


/**
 * @Filename ContentViewImp.java
 * @Description
 * @Version 1.0
 * @Author xs
 * @Email 197585312@qq.com
 */
public interface QuickAdapterImp<T> {
    int getBaseItemResource();

    void convert(ViewHolder helper, T item, int position);

}
