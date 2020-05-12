package com.kaflix.retrofitrealtimetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.gitContent.observe(this, Observer {
            main_textview.text = it
            Toast.makeText(this,"text changed",Toast.LENGTH_SHORT).show()
        })
        viewModel.getContentFromGit()

    }
}
