package ru.dogwalk.performer.Presenter

import retrofit2.Call
import ru.dogwalk.performer.Network.ResponseCallBack

open class BasePresenter {

    protected fun<T> Call<T>.enqueue(callback: ResponseCallBack<T>.() -> Unit) {
        val callBackKt = ResponseCallBack<T>()
        callback.invoke(callBackKt)
        this.enqueue(callBackKt)
    }
}