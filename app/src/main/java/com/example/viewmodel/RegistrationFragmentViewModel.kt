package com.example.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegistrationFragmentViewModel : ViewModel() {
    private var _isNameValid = MutableLiveData<ResourceState>()
    val isNameValid: LiveData<ResourceState>
        get() {
            return _isNameValid
        }

    private var _districtList = MutableLiveData<ResourceState>()
    val districtList: LiveData<ResourceState>
        get() {
            return _districtList
        }

    fun inputNameValidation(name: String) {
        //simulasi delay dari backend api
        GlobalScope.launch {
            _isNameValid.postValue(ResourceState.loading())
            delay(3000)
            if (!name.isNullOrBlank()) {
                _isNameValid.postValue(ResourceState.success(true))
            } else {
                _isNameValid.postValue(ResourceState.fail("name can not empty"))
            }
//        }
        }
    }

    fun onAddressGetDistrict(address: String) {
        GlobalScope.launch {
            _districtList.postValue(ResourceState.loading())
            delay(1000)
            if (address == "Jakarta Selatan") {
                _districtList.postValue(
                    ResourceState.success(
                        arrayListOf(
                            "Ragunan",
                            "Pasar Minggu",
                            "Pancoran"
                        )
                    )
                )
            } else {
                _districtList.postValue(
                    ResourceState.success(
                        arrayListOf(
                            "Duren Sawit",
                            "Manggarai",
                            "Pasar Rebo"
                        )
                    )
                )
            }
        }
    }


}

//global scope hanya contoh