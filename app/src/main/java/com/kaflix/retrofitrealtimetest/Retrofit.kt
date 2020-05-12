package com.kaflix.retrofitrealtimetest

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

const val BASE_URL = "https://raw.githubusercontent.com/f2janyway/dev/master/"
const val ex = "http://14.4.84.184:4000/"
interface Service{

    @GET("test")
    fun getReadMe():Call<String>

    companion object {
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder().baseUrl(ex)
//            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())

        // T 는 callback 의 대상이 됨
        fun <T> retrofitCallback(call: Call<T>, remains: (T) -> Unit) {
            call.enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    val TAG = "RetrofitCallback"
                    Log.e(TAG, "$t")
                }
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val data = response.body()
                    remains(data!!)
                }
            })
        }
    }
}

data class GitTestDto(val con:String)