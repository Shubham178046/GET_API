package com.example.get_api.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.get_api.Adapters.Adapter
import com.example.get_api.ApiClient.ApiClient
import com.example.get_api.Model.BaseResponse
import com.example.get_api.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager =
            LinearLayoutManager(this)
        getData()
    }

    private fun getData() {
        val call: Call<BaseResponse> = ApiClient.getClient.getData()
        call.enqueue(object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("Error", "onFailure: " + t.localizedMessage.toString())
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.body() != null) {
                    if (response.body()?.Status == 1) {
                        if (response.body()!!.ResponseData.size > 0) {

                            recyclerView.adapter=
                                Adapter(
                                    applicationContext,
                                    response.body()!!.ResponseData.get(0).Language
                                )
                        }
                    }

                }
            }
        })

    }
}