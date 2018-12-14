package ru.dogwalk.performer.Model

class Client : BaseModel() {
    val id: Long = 0
    val phone: String = ""
    val email: String = ""
    val full_name: String = ""
    val authentication_token: String = ""
        get() = field
    val uuid: String = ""
    val photo: Photo = Photo()
    val is_active: Boolean = false
    val is_walker: Boolean = false
    val is_sitter_at_home: Boolean = false
    val is_sitter_guest: Boolean = false
    val is_nanny: Boolean = false
    val is_reporter: Boolean = false
    val client_street: String = ""
    val client_metro: String = ""
}