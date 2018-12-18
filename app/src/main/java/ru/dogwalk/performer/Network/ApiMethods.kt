package ru.dogwalk.performer.Network

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import ru.dogwalk.performer.BuildConfig
import ru.dogwalk.performer.Model.Order
import ru.dogwalk.performer.Model.Client
import ru.dogwalk.performer.Model.ClientOrder
import ru.dogwalk.performer.Model.Settings

interface ApiMethods {
    companion object {
        const val BASE_URL = "https://uapi.new-staging.dog-walk.ru/"
        private const val VERSION: String = "v2/"

        fun getInstance(context: Context): ApiMethods {
            val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
                .baseUrl(BASE_URL + VERSION)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .client(getOkHttpClient(context))

            return  retrofitBuilder.build().create(ApiMethods::class.java)
        }

        private fun getOkHttpClient(context: Context): OkHttpClient {
            val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            httpClient.interceptors().add(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            httpClient.interceptors().add(getInterceptorAuthorizationParam(context))

            return httpClient.build()
        }

        private fun getInterceptorAuthorizationParam(context: Context): Interceptor {
            return Interceptor { chain ->
                val original: Request = chain.request()

                // Настраиваем запросы
                val requestBuilder: Request.Builder = original.newBuilder()
                    .header("X-App-ID", "8ac302d08830f91dc5c7201c93f1686d4dbf3deefde0aaa5ffe7b08811906b900cc1d592e134c4890586624bc858511673a5a410b54ea39404133185acaf06fd")

                val settings = Settings(context)
                if (!settings.getLogin()?.isEmpty()!!) {
                    requestBuilder
                        .header("X-User-Phone", settings.getLogin()!!)
                        .header("X-User-Token", settings.getToken()!!)
                }
                requestBuilder
                    .method(original.method(), original.body())

                return@Interceptor chain.proceed(requestBuilder.build())
            }
        }
    }

    @POST("user/sessions")
    fun login(@Query("phone") phone: String, @Query("password") password: String): Call<Client>

    @GET("user/sessions")
    fun getUserInfo(): Call<Client>

    @GET("user/orders")
    fun getOrders(@Query("hot") hot: Boolean): Call<List<Order>>

    @GET("user/scheduled_services")//https://uapi.new-staging.dog-walk.ru/v2/user/scheduled_services?begins_at_gte=2018-11-10&ends_at_lte=2018-12-10
    fun getOrders(@Query("begins_at_gte") begins_at_gte: String, @Query("ends_at_lte") ends_at_lte: String): Call<List<ClientOrder>>
}