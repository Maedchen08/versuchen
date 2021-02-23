package com.example.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnNavigationListener {
    lateinit var binding: ActivityMainBinding
    lateinit var helloFragment: HelloFragment
    lateinit var registrationFragment: RegistrationFragment
    lateinit var viewModel: MainViewModel
//    lateinit var viewModel: MainViewModel
    //cara lain pakai kotlin extension
//    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registrationFragment = RegistrationFragment.newInstance(this)
        helloFragment = HelloFragment.newInstance()
        viewModel=ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.helloWorld?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer,helloFragment).commit()
        }?: let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer,registrationFragment).commit()
        }

    }

    override fun onHello() {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, helloFragment)
            .commit()
    }

}