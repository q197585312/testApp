package nanyang.com.dig88.Base;

public interface IBaseView<T>  {
    void onGetData(T data);
    void onFailed(String error);

}
