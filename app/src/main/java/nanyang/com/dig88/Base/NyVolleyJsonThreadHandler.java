package nanyang.com.dig88.Base;

import android.content.Context;

/**
 * Created by Administrator on 2015/10/21.
 */
public abstract class NyVolleyJsonThreadHandler<T> extends SportJsonThreadHandler<NyBaseResponse<T>> {

    public NyVolleyJsonThreadHandler(Context context) {
        super(context);
    }
    @Override
    public void successEnd(NyBaseResponse<T> obj) {
        super.successEnd(obj);
        if (obj.getCode().trim().equals("1")) {
            int total = 0;
            if (obj.getTotal() != null) {
                total = obj.getTotal();
            }
            successEndT(total, obj.getData());
        } else {
            errorEnd("Code:"+obj.getCode()+",Msg:"+obj.getMsg());
        }
    }

    protected abstract void successEndT(int total, T data);
}
