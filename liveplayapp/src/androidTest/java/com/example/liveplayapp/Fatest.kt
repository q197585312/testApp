package com.example.liveplayapp

import com.unkonw.testapp.login.DataBean
import java.nio.file.WatchEvent
import kotlin.reflect.KParameter

class Fatest  {
    operator fun invoke(): DataBean {
        return DataBean()

    }
    interface Monad<F>{
        fun <A,B> KParameter.Kind.flatmap()
    }


}