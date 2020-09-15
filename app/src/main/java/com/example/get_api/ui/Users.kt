package com.example.get_api.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.get_api.ApiClient.UserApiClient
import com.example.get_api.Model.Users_Model
import com.example.get_api.R
import kotlinx.android.synthetic.main.activity_users.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Users : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        val call : Call<Users_Model> = UserApiClient.getUsersData.getUsersData("sarahjean")
        call.enqueue(object : Callback<Users_Model>{
            override fun onFailure(call: Call<Users_Model>, t: Throwable) {
                Log.d("Error", "onFailure: "+ t.localizedMessage.toString())
            }

            override fun onResponse(call: Call<Users_Model>, response: Response<Users_Model>) {
                if(response.body() != null)
                {
                    user_id.setText(""+response.body()!!.id)
                    user_name.setText(response.body()!!.name)
                    user_company.setText(response.body()!!.company)
                    user_location.setText(response.body()!!.location)
                    user_url.setText(response.body()!!.url)
                }
            }

        })
    }
}