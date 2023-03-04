package com.tugcearas.goshop.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.tugcearas.goshop.R
import com.tugcearas.goshop.databinding.FragmentPaymentScreenBinding
import com.tugcearas.goshop.util.extensions.click
import com.tugcearas.goshop.util.extensions.gone

class PaymentScreen : Fragment() {

    private lateinit var binding: FragmentPaymentScreenBinding
    private val monthList = listOf(1,2,3,4,5,6,7,8,9,10,11,12)
    private val yearList = listOf(2023,2024,2025,2026,2027,2028,2029,2030,2031,2032)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.paymentToolbar.apply {
            toolbarTitle.gone()
            toolbarImageView.gone()
            toolbarImageView.gone()
            favButton.gone()
        }
        clickBackButton()
        clickPayButton()
        monthAutoComplete()
        yearAutoComplete()
    }

    private fun clickBackButton(){
        binding.paymentToolbar.customToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun clickPayButton(){
        binding.payButton.click {
            if (checkCardInformations()){
                val action = PaymentScreenDirections.actionPaymentScreenToSuccessScreen()
                findNavController().navigate(action)
            }
        }
    }

    private fun monthAutoComplete(){
        val monthAutoComplete: AutoCompleteTextView = binding.monthAutoComplete
        val monthAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item_menu,monthList)
        monthAutoComplete.setAdapter(monthAdapter)
        monthAutoComplete.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val itemSelected = parent.getItemAtPosition(position)
        }
    }

    private fun yearAutoComplete(){
        val yearAutoComplete: AutoCompleteTextView = binding.yearAutoComplete
        val yearAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item_menu,yearList)
        yearAutoComplete.setAdapter(yearAdapter)
        yearAutoComplete.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val itemSelected = parent.getItemAtPosition(position)
        }
    }

    private fun validateCardField(
        field:String?,
        layout: TextInputLayout,
        errorMessage:String,
        requiredLength:Int? = null
    ):Boolean{
        return if (field.isNullOrEmpty()){
            layout.error = errorMessage
            false
        }
        else{
            if(requiredLength != null && field.length != requiredLength){
                layout.error = getString(R.string.check_number_length)
                false
            }
            else{
                layout.error = null
                true
            }
        }
    }

    private fun checkCardInformations():Boolean{
        val getCardName = binding.cardNameEditText.text.toString()
        val getCardNumber = binding.cardNumberEditText.text.toString()
        val getMonth = binding.monthAutoComplete.text.toString()
        val getYear = binding.yearAutoComplete.text.toString()
        val getCvv = binding.paymentCvvEditText.text.toString()
        val getAddress = binding.paymentAddressEditText.text.toString()

        val isCardNameValid = validateCardField(
            getCardName,
            binding.paymentCardNameLayout,
            getString(R.string.error_card_name)
        )
        val isCardNumberValid = validateCardField(
            getCardNumber,
            binding.paymentCardNumberLayout,
            getString(R.string.error_card_number),
            requiredLength = 16
        )
        val isCardMonthValid = validateCardField(
            getMonth,
            binding.paymentMonthLayout,
            getString(R.string.error_month)
        )
        val isCardYearValid = validateCardField(
            getYear,
            binding.paymentYearLayout,
            getString(R.string.error_year)
        )
        val isCardCvvValid = validateCardField(
            getCvv,
            binding.paymentCvvLayout,
            getString(R.string.error_cvv),
            requiredLength = 3
        )
        val isCardAddressValid = validateCardField(
            getAddress,
            binding.paymentAddressLayout,
            getString(R.string.error_address)
        )

        return isCardNameValid && isCardNumberValid && isCardMonthValid
                && isCardYearValid && isCardCvvValid && isCardAddressValid
    }
}