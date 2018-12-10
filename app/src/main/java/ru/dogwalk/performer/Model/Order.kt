package ru.dogwalk.performer.Model

class Order() {
    constructor(id: Long) : this()
    constructor(id: Long, title: String, sub_title: String, date_time: String, address: String, metro: String) : this()

    val id: Long = 0L
    val title: String = ""
    val sub_title: String = ""
    val date_time: String = ""
    val address: String = ""
    val metro: String = ""
    val price: Double = 0.0
}