package com.example.get_api.Adapters.Spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.get_api.Model.Ulearn.StateModel
import com.example.get_api.R

class StateAdapter(context: Context, var stateModel: ArrayList<StateModel>) : ArrayAdapter<StateModel>(context,0,stateModel) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val vh : ItemHolder
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
        vh.state.setText(stateModel.get(position).State)
        return view
    }

    override fun getItem(position: Int): StateModel {
       return stateModel[position]
    }
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return this.getView(position,convertView,parent)
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return stateModel.size
    }
    private class ItemHolder(row: View?) {
        val state: TextView
        init {
            state = row?.findViewById(R.id.title) as TextView
        }
    }
}