package com.kaflix.retrofitrealtimetest

import android.util.Log
import androidx.lifecycle.*
import com.kaflix.retrofitrealtimetest.Service.Companion.retrofitCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext
import kotlin.math.log

class MainViewModel : ViewModel() {
    private val TAG = "viewmodel"
    private val _gitContent = MutableLiveData<String>()
    val gitContent: LiveData<String>
        get() = _gitContent

    fun getContentFromGit() {
        val service = Service.retrofit.build().create(Service::class.java)
        val call = service.getReadMe()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "fail network");
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                _gitContent.postValue(response.body())
            }
        })
//        val remain: (String) -> Unit = { git ->
////            _gitContent.observe(owner, Observer {
////                _gitContent.postValue(it)
////                Log.e(TAG, "$it < inner")
////            })
//            _gitContent.postValue(git)
//            Log.e(TAG, "$git < git outer");
//        }
//        retrofitCallback(call = call, remains = remain)
    }
}