package com.example.get_api.Model

import com.example.get_api.Model.Ulearn.EducationModel
import com.example.get_api.Model.Ulearn.EmployeeModel
import com.example.get_api.Model.Ulearn.Occupation
import com.example.get_api.Model.Ulearn.StateModel

data class ResponseModel(val Language : ArrayList<LanguageModel>, val NULMCategory : ArrayList<NulmModel>, val DOCCategory : ArrayList<DocModel>, val Education : ArrayList<EducationModel>, val EmployeeType : ArrayList<EmployeeModel>, val Occupation : ArrayList<Occupation>, val State : ArrayList<StateModel>,val City : ArrayList<CityModel>) {
}