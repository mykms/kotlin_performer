package ru.dogwalk.performer.Model

class ClientOrder {
    val id: Long = 0
    val amount: Long = 0
    val service_state_id: Long = 0
    val plan_id: Long = 0
    val user_id: Long = 0
    val pet_id: Long = 0
    val created_at: String = ""
    val updated_at: String = ""
    val begins_at: String = ""
    val is_recurring: Boolean = false
    val comment: String = ""
    val ends_at: String = ""
    val supplementary_id: Long? = null
    val aasm_state: String = ""
    val cards: Any? = null
    val title: String = ""
    val client: Client = Client()
}