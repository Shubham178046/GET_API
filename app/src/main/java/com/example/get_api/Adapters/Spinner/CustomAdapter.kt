package com.example.get_api.Adapters.Spinner

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.get_api.Model.Ulearn.EducationModel
import com.example.get_api.R

class CustomAdapter(context: Context,var educationModel: ArrayList<EducationModel>) :
   ArrayAdapter<EducationModel>(context,0,educationModel) {
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
        vh.education.setText(educationModel.get(position).Education)
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return this.getView(position,convertView,parent)
    }

    override fun getItem(position: Int): EducationModel {
    return educationModel[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return educationModel.size
    }
    private class ItemHolder(row: View?) {
        val education: TextView
        init {
            education = row?.findViewById(R.id.title) as TextView
        }
    }
}