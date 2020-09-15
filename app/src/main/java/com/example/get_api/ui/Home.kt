package com.example.get_api.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.get_api.Fragment.DocCategory
import com.example.get_api.Fragment.NulmCategory
import com.example.get_api.Fragment.SbmCategory
import com.example.get_api.R
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    val nulmCategory = NulmCategory()
    val sbmCategory = SbmCategory()
    val docCategory=DocCategory()
    var fragmentManager : FragmentManager= supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        addFragment(nulmCategory)

        nulm_txt_btn.setOnClickListener {
            addFragment(nulmCategory)
        }
        sbm_txt_btn.setOnClickListener {
            addFragment(sbmCategory)
        }
        doc_txt_btn.setOnClickListener {
            addFragment(docCategory)
        }

    }

    fun addFragment(fragment: Fragment)
    {
        fragmentManager.beginTransaction().replace(R.id.frame_layout,fragment,fragment.javaClass.simpleName).addToBackStack(null).commit()
    }
}