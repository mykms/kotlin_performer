package ru.dogwalk.performer.Network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResponseCallBack<T>: Callback<T> {
    var onResponse: ((Response<T>) -> Unit)? = null
    var onFailure: ((t: Throwable?) -> Unit)? = null

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure?.invoke(t)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onResponse?.invoke(response)
    }
}