package com.example.viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel.databinding.FragmentRegistrationBinding

class RegistrationFragment(val onNavigationListener: OnNavigationListener) : Fragment() {
    lateinit var viewModel: RegistrationFragmentViewModel
    lateinit var sharedViewModel: MainViewModel
    lateinit var binding :FragmentRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel =
            ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel = ViewModelProvider(this).get(RegistrationFragmentViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(layoutInflater)
        binding.apply {
//            helloTextView.setText(viewModel.helloWorld)
            helloButton.setOnClickListener {
                val personName = helloTextView.text
                if (viewModel.inputNameValidation(personName.toString())) {
                    sharedViewModel.sayHello("Hi...${personName}")
                } else {
                    Toast.makeText(
                        requireContext(),"Please input name", Toast.LENGTH_LONG
                    ).show()
                }

                onNavigationListener.onHello()

            }
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(onNavigationListener: OnNavigationListener) =
            RegistrationFragment(onNavigationListener)
    }
}