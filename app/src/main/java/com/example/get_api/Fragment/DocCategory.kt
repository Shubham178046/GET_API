package com.example.get_api.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.get_api.Adapters.DocAdapter
import com.example.get_api.ApiClient.HomeApiClient
import com.example.get_api.Model.BaseResponse
import com.example.get_api.R
import kotlinx.android.synthetic.main.fragment_doc_category.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DocCategory.newInstance] factory method to
 * create an instance of this fragment.
 */
class DocCategory : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doc_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        doc_recyclerview.layoutManager=LinearLayoutManager(context)
        getData()
    }

    private fun getData() {
        val call : Call<BaseResponse> = HomeApiClient.getClientData.getLanguageData(6)
        call.enqueue(object : Callback<BaseResponse>{
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("Error", "onFailure: "+ t.localizedMessage.toString())
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if(response.body() != null)
                {
                    if (response.body()!!.Status == 1)
                    {
                        if(response.body()!!.ResponseData.size > 0)
                        {
                            doc_recyclerview.adapter= context?.let { DocAdapter(it,response.body()!!.ResponseData.get(0).DOCCategory) }
                        }
                    }
                }
            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DocCategory.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DocCategory().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}