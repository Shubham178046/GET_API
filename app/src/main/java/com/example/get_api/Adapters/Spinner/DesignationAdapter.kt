package com.example.get_api.Adapters.Spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.get_api.Model.Ulearn.DesignationModel
import com.example.get_api.R

class DesignationAdapter(context : Context, var designationModel: ArrayList<DesignationModel>) : ArrayAdapter<DesignationModel>(context,0,designationModel) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
      val view  : View
        val vh : ItemHolder
        if (convertView == null) {
            view = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.spinner_layout, parent, false)
            vh = ItemHolder(
                view
            )
            view?.tag = vh
        }
        else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.EmployeeType.setText(designationModel.get(position).EmployeeType)
        return view
    }

    override fun getItem(position: Int): DesignationModel {
      return designationModel[position]
    }
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return this.getView(position,convertView,parent)
    }
    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getCount(): Int {
        return designationModel.size
    }
    private class ItemHolder(row: View?) {
        val EmployeeType : TextView
        init {
            EmployeeType = row?.findViewById(R.id.title) as TextView
        }
    }
}