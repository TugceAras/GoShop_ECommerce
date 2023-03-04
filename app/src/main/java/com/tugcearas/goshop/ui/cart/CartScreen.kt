package com.tugcearas.goshop.ui.cart

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tugcearas.goshop.R
import com.tugcearas.goshop.databinding.FragmentCartScreenBinding
import com.tugcearas.goshop.ui.cart.adapter.CartAdapter
import com.tugcearas.goshop.ui.cart.adapter.CartViewModel
import com.tugcearas.goshop.util.extensions.click
import com.tugcearas.goshop.util.extensions.gone
import com.tugcearas.goshop.util.extensions.toastMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartScreen : Fragment() {

    private lateinit var binding: FragmentCartScreenBinding
    private val cartViewModel: CartViewModel by viewModels()
    private val cartAdapter by lazy { CartAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cartToolbar.apply {
            toolbarImageView.gone()
            toolbarTitle.gone()
            favButton.gone()
        }
        initAdapter()
        initObserver()
        clickBackButton()
    }

    private fun initAdapter(){
        binding.cartRecyclerView.adapter = cartAdapter

        cartAdapter.onDeleteClick = {
            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.alertdialog_set_title))
                .setMessage(getString(R.string.alertdialog_set_message))
                .setPositiveButton(getString(R.string.alertdialog_positive_button)) { _, _ ->
                    cartViewModel.deleteProductFromCart(it)
                }
                .setNegativeButton(getString(R.string.alertdialog_negative_button),null)
                .show()
        }
        cartAdapter.onIncrease = {
            cartViewModel.increasePrice(it)
        }
        cartAdapter.onDecrease = {
            cartViewModel.decreasePrice(it)
        }
    }

    private fun initObserver(){
        cartViewModel.getProduct.observe(viewLifecycleOwner){
            cartAdapter.submitList(it)
            binding.cartBuyButton.click {
                if (it.isNotEmpty()){
                    val action = CartScreenDirections.actionCartScreenToPaymentScreen()
                    findNavController().navigate(action)
                }
                else{
                    requireContext().toastMessage(getString(R.string.cart_empty_message))
                }
            }
        }

        cartViewModel.getAmount.observe(viewLifecycleOwner){
            binding.cartPriceTextView.text = String.format("$%.2f", it)
        }
    }

    private fun clickBackButton(){
        binding.cartToolbar.customToolbar.setNavigationOnClickListener {
            val action = CartScreenDirections.actionCartScreenToHomeScreen()
            findNavController().navigate(action)
        }
    }
}