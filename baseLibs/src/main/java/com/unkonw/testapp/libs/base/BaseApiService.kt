package com.unkonw.testapp.libs.base


import androidx.lifecycle.ViewModel
import com.unkonw.testapp.libs.api.Api

/**
 *   @auther : Aleyn
 *   time   : 2019/11/01
 */
class BaseApiService {

    inline fun <reified T> create(): T {
        return Api.getService(T::class.java)
    }

    companion object {

        @Volatile
        private var INSTANCE: BaseApiService? = null

        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: BaseApiService().also { INSTANCE = it }
            }
    }

}