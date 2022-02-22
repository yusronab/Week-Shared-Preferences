package com.example.week7sharepref.webService

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {

    companion object{
        private var retrofit: Retrofit? = null
        private var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        private fun getClient(): Retrofit {
            return if (retrofit == null){
                retrofit = Retrofit.Builder().baseUrl(Constant.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                retrofit!!
            }else{
                retrofit!!
            }
        }

        fun apiEndpoint(): ApiEndpoint = getClient().create(ApiEndpoint::class.java)
    }

}

class Constant{

    companion object{
        const val BASE_URL = "https://apibarang.herokuapp.com/"

        fun setToken(context: Context, token: String){
            val sharePref = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            sharePref.edit().apply {
                putString("Token", token)
                apply()
            }
        }

        fun getToken(context: Context) : String{
            val sharePref = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            val token = sharePref.getString("Token", "Undef")
            return token!!
        }

        fun clearToken(context: Context){
            val sharePref = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            sharePref.edit().clear().apply()
        }

    }

}