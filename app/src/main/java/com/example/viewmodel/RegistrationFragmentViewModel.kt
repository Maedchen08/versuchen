package com.example.viewmodel

import androidx.lifecycle.ViewModel

class RegistrationFragmentViewModel : ViewModel(){

    fun inputNameValidation(name:String) = !name.isNullOrBlank()

}