package com.example.get_api.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.example.get_api.Adapters.Spinner.*
import com.example.get_api.ApiClient.MasterApiClient
import com.example.get_api.Model.BaseResponse
import com.example.get_api.Model.CityBody
import com.example.get_api.Model.Ulearn.EducationModel
import com.example.get_api.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_u_learn.*

class ULearn : AppCompatActivity() {
    lateinit var educationModel : ArrayList<EducationModel>
    var education : ArrayList<String> = ArrayList<String>()
    var state_id : Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_u_learn)

        val call : Call<BaseResponse> = MasterApiClient.getClientData.getMasterData(6)
        call.enqueue(object : Callback<BaseResponse>{
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("Error", "onFailure: "+ t.localizedMessage.toString())
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if(response.body() != null)
                {
                    if(response.body()!!.Status == 1)
                    {
                        if(response.body()!!.ResponseData.size > 0)
                        {
                            education_spinner.adapter=
                                CustomAdapter(
                                    applicationContext,
                                    response.body()!!.ResponseData.get(0).Education
                                )

                                 employee_spinner.adapter= EmployeeAdapter(applicationContext,response.body()!!.ResponseData.get(0).EmployeeType)
                            employee_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                    TODO("Not yet implemented")
                                }

                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    /*if(position == 0)
                                    {
                                        desingnation_spinner.adapter= DesignationAdapter(applicationContext,response.body()!!.ResponseData.get(0).EmployeeType.get(0).Designation)
                                    }
                                    if(position == 1)
                                    {
                                        desingnation_spinner.adapter= DesignationAdapter(applicationContext,response.body()!!.ResponseData.get(0).EmployeeType.get(1).Designation)
                                    }*/
                                    desingnation_spinner.adapter= DesignationAdapter(applicationContext,response.body()!!.ResponseData.get(0).EmployeeType.get(position).Designation)
                                }

                            }

                            occupation_spinner.adapter= OccupationAdapter(applicationContext,response.body()!!.ResponseData.get(0).Occupation)

                            state_spinner.adapter=StateAdapter(applicationContext,response.body()!!.ResponseData.get(0).State)
                            state_spinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                    TODO("Not yet implemented")
                                }

                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                        state_id= response.body()!!.ResponseData.get(0).State.get(position).StateId
                                        CityData(state_id)
                                }

                            }
                        }
                    }

                }
            }
        })
    }

    fun CityData(stateid : Int)
    {
        val city_call : Call<BaseResponse> = MasterApiClient.getClientData.getCityData(CityBody(stateid,6))

        city_call.enqueue(object : Callback<BaseResponse>{
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("Error", "onFailure: "+ t.localizedMessage.toString())
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if(response.body() != null) {
                    if (response.body()!!.Status == 1) {
                        if (response.body()!!.ResponseData.size > 0) {

                            city_spinner.adapter=CityAdapter(applicationContext,response.body()!!.ResponseData.get(0).City)
                        }
                    }
                }
            }

        })
    }
}