package nanyang.com.dig88.Table.Thread;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2015/11/9.
 */
public abstract class ThreadEndT<T> {
    Type t;

    public ThreadEndT(Type t) {
        this.t = t;
    }



    public Type getT() {
        return t;
    }

    public abstract void endT(T result,int model);
    public abstract void endString(String result,int model);
    public abstract void endError(String error);
    T gsonAnalysis(String s) {
            return new Gson().fromJson(s, getT());

    }
}
