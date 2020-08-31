package gaming178.com.casinogame.load

import java.util.*
import kotlin.properties.Delegates

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


public fun main(args: Array<String>) {
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
    val listOf = listOf(DataBean("11","22"), DataBean("121","221"))
    for (dataBean in Update.Datamap(listOf)) {
        println(dataBean)
    }
}

fun test() {
    println("test222")
}

fun test1(t: () -> Unit) {
    t.invoke()
}
