package com.example.get_api.Adapters.Spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.get_api.Model.Ulearn.Occupation
import com.example.get_api.R

class OccupationAdapter(context: Context, var occupation: ArrayList<Occupation>) : ArrayAdapter<Occupation>(context,0,occupation) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = LayoutInflater.from(parent!!.context).inflate(R.layout.spinner_layout,parent,false)
            vh = ItemHolder(
                view
            )
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.occupation.setText(occupation.get(position).Occupation)
        return view
    }

    override fun getItem(position: Int): Occupation {
        return occupation[position]
    }
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return this.getView(position,convertView,parent)
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return occupation.size
    }
    private class ItemHolder(row: View?) {
        val occupation: TextView
        init {
            occupation = row?.findViewById(R.id.title) as TextView
        }
    }
}