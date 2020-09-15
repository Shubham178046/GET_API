package com.example.get_api.Interface

import com.example.get_api.ApiClient.ApiClient.BASE_URL
import com.example.get_api.Model.BaseResponse
import com.example.get_api.Model.CityBody
import com.example.get_api.Model.Users_Model
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("https://www.u-learn.in/v1/language/")
    fun getData() : Call<BaseResponse>

    @GET("home")
    fun  getLanguageData(@Query("LanguageId")LanguageId : Int) : Call<BaseResponse>

    @GET("users/{username}")
    fun getUsersData(@Path("username") username : String) : Call<Users_Model>

    @GET("masters")
    fun getMasterData(@Query("LanguageId") LanguageId : Int) : Call<BaseResponse>

    @POST("city")
    fun getCityData(@Body cityBody: CityBody) : Call<BaseResponse>
}