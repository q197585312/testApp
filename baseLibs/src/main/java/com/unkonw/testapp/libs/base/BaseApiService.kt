package com.unkonw.testapp.libs.base


import com.unkonw.testapp.libs.api.ApiManager

/**
 *   @auther : Aleyn
 *   time   : 2019/11/01
 */
class BaseApiService {

    inline fun <reified T> create(): T {
        return ApiManager.getService(T::class.java)
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