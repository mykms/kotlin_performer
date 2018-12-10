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
import ru.dogwalk.performer.Model.Sessions
import ru.dogwalk.performer.Model.Settings

interface ApiMethods {
    companion object {
        private val BASE_URL: String = "https://test-api.new-staging.dog-walk.ru/v1/"

        fun getInstance(context: Context): ApiMethods {
            val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
                .baseUrl(BASE_URL)
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

    @GET("orders/{type}")
    fun getOrders(@Path("type") type: String?): Call<List<Order>>

    @POST("user/sessions")
    fun login(@Query("phone") phone: String, @Query("password") password: String): Call<Sessions>

    @GET("user/sessions")
    fun userInfo(): Call<Sessions>
}