package com.example.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel(){
var helloWord :String?=null

    fun sayHello(word:String){
        helloWord = word
    }
}