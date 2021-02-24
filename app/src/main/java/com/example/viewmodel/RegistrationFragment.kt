package com.example.viewmodel

import android.app.AlertDialog
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.viewmodel.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment(), AdapterView.OnItemSelectedListener {
    lateinit var viewModel: RegistrationFragmentViewModel
    lateinit var sharedViewModel: MainViewModel
    lateinit var binding :FragmentRegistrationBinding
    lateinit var loadingDialog : AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       initViewModel()
        subscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        loadingDialog = LoadingDialog.build(requireContext())
        binding = FragmentRegistrationBinding.inflate(layoutInflater)
        binding.apply {

            addressEditText.setOnKeyListener { view, keyCode, keyEvent ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_UP) {
                    viewModel.onAddressGetDistrict(addressEditText.text.toString())
                    return@setOnKeyListener true
                }
                false
            }

            registrationButton.setOnClickListener {
                val ktp = ktpEditText.text
                viewModel.inputNameValidation(ktp.toString())
            }
            ditrictSpinner.adapter = DistrictAdapter(null)
            ditrictSpinner.onItemSelectedListener = this@RegistrationFragment
//
        }
        return binding.root
    }



    private fun initViewModel() {
        sharedViewModel =
            ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel=
            ViewModelProvider(this).get(RegistrationFragmentViewModel::class.java)

        }
    private fun subscribe() {
        viewModel.districtList.observe(this,{
            binding.ditrictSpinner.adapter = DistrictAdapter(it)
        })
        viewModel.isNameValid.observe(this, {
            when(it.status) {
                ResourcesStatus.LOADING -> loadingDialog.show()
                ResourcesStatus.SUCCESS -> {
                    loadingDialog.hide()
                    sharedViewModel.sayHello("Hi....")
                    findNavController().navigate(R.id.action_registrationFragment_to_helloFragment)
                }
                ResourcesStatus.FAIL -> {
                    loadingDialog.hide()
                    Toast.makeText(requireContext(), it.message,
                    Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegistrationFragment
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}