package ru.dogwalk.performer.Model

class Order() {
    constructor(id: Long) : this() {
        this.id = id
    }

    constructor(id: Long, title: String, sub_title: String, date_time: String, address: String, metro: String) : this(id) {
        this.title = title
        this.subtitle = sub_title
        this.starting_at = date_time
        this.client_street = address
        this.client_metro = metro
    }

    var id: Long = 0L
    var title: String = ""
    var subtitle: String = ""
    var starting_at: String = ""
    var client_street: String = ""
    var client_metro: String = ""
    var client: Client = Client()
    var is_hot: Boolean = false
    var is_like: Boolean = false
    var price: Double = 0.0
}