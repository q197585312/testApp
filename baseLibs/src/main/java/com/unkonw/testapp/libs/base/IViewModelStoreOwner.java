package com.unkonw.testapp.libs.base;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

/**
 * Created by Administrator on 2016/12/15 0015.
 */

interface IViewModelStoreOwner {
    ViewModelStore getViewModelStore();
    ViewModelProvider.Factory getViewModelFactory();
}
