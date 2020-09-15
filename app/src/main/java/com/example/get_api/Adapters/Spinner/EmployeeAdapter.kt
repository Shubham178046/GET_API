package com.example.get_api.Adapters.Spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.get_api.Model.Ulearn.EducationModel
import com.example.get_api.Model.Ulearn.EmployeeModel
import com.example.get_api.R

class EmployeeAdapter(context: Context, var employeeModel: ArrayList<EmployeeModel>) :ArrayAdapter<EmployeeModel>(context,0,employeeModel) {
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
         vh.employee_type.setText(employeeModel.get(position).EmployeeType)
        return view
    }

    override fun getItem(position: Int): EmployeeModel {
       return employeeModel[position]
    }
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return this.getView(position,convertView,parent)
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
       return employeeModel.size
    }
    private class ItemHolder(row: View?) {
        val employee_type: TextView
        init {
            employee_type = row?.findViewById(R.id.title) as TextView
        }
    }
}