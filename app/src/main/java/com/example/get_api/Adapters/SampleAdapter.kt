package com.example.get_api.Adapters

import android.content.Context
import android.widget.ArrayAdapter
import com.example.get_api.Model.Ulearn.EducationModel

class SampleAdapter(context: Context, var educationModel: ArrayList<EducationModel>) : ArrayAdapter<EducationModel>(context,0, educationModel) {
}