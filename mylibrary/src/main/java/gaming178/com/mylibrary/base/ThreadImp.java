package gaming178.com.mylibrary.base;

/**
 * @Filename ContentViewImp.java
 * @Description
 * @Version 1.0
 * @Author xs
 * @Email 197585312@qq.com
 * @History <li>Author: xs</li> <li>Date: 2015年1月27日</li> <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public interface ThreadImp<A, S, E> {
    /**
     * 网络请求开始
     *
     * @param obj
     */
    void startThread(A obj);

    /**
     * 网络请求成功
     *
     * @param obj
     */
    void successEnd(S obj);

    /**
     * 网络请求失败
     *
     * @param obj
     */
    void errorEnd(E obj);
}
