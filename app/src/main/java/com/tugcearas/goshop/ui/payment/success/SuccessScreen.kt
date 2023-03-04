package com.tugcearas.goshop.ui.payment.success

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tugcearas.goshop.databinding.FragmentSuccessScreenBinding
import com.tugcearas.goshop.util.extensions.click
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuccessScreen : Fragment() {

    private lateinit var binding: FragmentSuccessScreenBinding
    private val successViewModel: SuccessViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSuccessScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickButton()
    }

    private fun clickButton(){
        binding.paymentSuccessButton.click {
            successViewModel.clearCart()
            val action = SuccessScreenDirections.actionSuccessScreenToHomeScreen()
            findNavController().navigate(action)
        }
    }
}