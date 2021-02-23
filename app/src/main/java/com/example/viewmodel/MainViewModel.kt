package com.example.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel(){
var helloWorld :String?=null

    fun sayHello(word:String){
        helloWorld = word
    }
}