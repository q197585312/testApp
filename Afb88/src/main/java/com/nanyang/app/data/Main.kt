package com.nanyang.app.data

data class Main(
    val dbid: String,
    val g: String,
    val img: String,
    val type: String,
    var number: String = "0"
) : Comparable<Main> {
    override fun compareTo(other: Main): Int {
        if (this.number.isNullOrEmpty()) {
            this.number = "0"
        }
        return if (this.number == "0") {
            0
        } else {
            -1
        }
    }
}