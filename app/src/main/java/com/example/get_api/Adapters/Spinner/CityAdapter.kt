package com.example.get_api.Adapters.Spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.get_api.Model.CityModel
import com.example.get_api.R

class CityAdapter(context: Context, var cityModel: ArrayList<CityModel>) : ArrayAdapter<CityModel>(context,0,cityModel){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view : View
        val vh : ItemHolder
        if(convertView == null)
        {
            view = LayoutInflater.from(parent!!.context).inflate(R.layout.spinner_layout,parent,false)
            vh = ItemHolder(
                view
            )
            view?.tag = vh
        }
        else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.city.setText(cityModel.get(position).City)
        return view
    }

    override fun getItem(position: Int): CityModel {
        return cityModel[position]
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return this.getView(position, convertView, parent)
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getCount(): Int {
        return cityModel.size
    }
    private class ItemHolder(row: View?) {
        val city: TextView
        init {
            city = row?.findViewById(R.id.title) as TextView
        }
    }
}