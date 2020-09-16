package gaming178.com.casinogame.load

import kotlinx.coroutines.*
import java.util.*
import kotlin.properties.Delegates
import kotlin.reflect.jvm.internal.impl.protobuf.LazyStringArrayList

class TeMain constructor(
    val code: String,
    val type: String,
    val name: String? = null
) {


    init {
        require(name != null) {
            "name should not null"
        }
    }
}

class Update : Observable() {
    val observables = mutableSetOf<Observer>()
    fun update(price: Int) {
        observables.forEach {
            it.update(this, price)
        }
    }

    var price: Int by Delegates.observable(0) { _, old, new ->
        if (new > old)
            println("$new>$old")
        else
            println("$new<$old")
    }
    var price2: Int by Delegates.vetoable(0) { _, old, new ->
        new > old

    }

    class Display() : Observer {
        override fun update(o: Observable, arg: Any) {
            println("last price is $arg")
        }
    }


    class Datamap(val datas: List<DataBean>) {
        operator fun iterator(): Iterator<DataBean> = datas.iterator()

    }
}


public fun main(args: Array<String>)=runBlocking {//主协成 一定会执行
    launch {
        delay(2000L)//挂起 不阻塞线程  挂起可以执行其他的线程或者携程
        test()
    }
    println("runBlocking")
    val one= async {
        searchStr("One")
    }
    val two= async {//相当于一个子携程  会有个返回值 非阻塞 可取消
        searchStr("two")
    }
    println("reslut one=${one.await()},two=${two.await()}")


//        delay(2000L)//挂起 不阻塞线程
}/* {
//    println(TeMain("", ""))
//    val su = Update()
//    val sd = Display()
//    su.observables.add(sd)
//    su.update(11)
//    su.price = 1
//    su.price = 2
//    su.price = 0
//    test1(::test)
//    test1 { test() }
*//*    val listOf = listOf(DataBean("11","22"), DataBean("121","221"))
    for (dataBean in Update.Datamap(listOf)) {
        println(dataBean)
    }*//*
    GlobalScope.launch {
        delay(1500L)
        println("World1500")
    }
    println("Hello")
    runBlocking {//主协成 一定会执行
        launch {
            delay(2000L)//挂起 不阻塞线程  挂起可以执行其他的线程或者携程
            println("World2000")
        }
        println("runBlocking")
//        delay(2000L)//挂起 不阻塞线程
    }
//    Thread.sleep(2000L)//阻塞线程 JVM保活 不保活 非主携程可能不会执行
}
*/

suspend fun searchStr(s: String): String {
    delay(1000)
    println("执行$s")
    return s
}

fun test() {
    val intlist= arrayListOf<Int>(1,2,3)
    val flist= arrayListOf(1.1,2.2,3)
    val dlist= arrayOf(1.1,2.2,3.2,3.222)
    val slist2= LazyStringArrayList()

    slist2.add("slist2")
    slist2.add("ss2")
    println(intlist)
    println(flist)
    println(dlist)
    println(slist2)

}

fun test1(t: () -> Unit) {
    t.invoke()


}
