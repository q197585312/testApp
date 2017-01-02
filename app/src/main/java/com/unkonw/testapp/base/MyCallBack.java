package com.unkonw.testapp.base;

/**
 * 发送请求后的回调接口
 */

interface MyCallBack<T>  {
   void onCompleted();
   void onError(HttpExceptionBean mHttpExceptionBean);
   void onNext(T t);
}