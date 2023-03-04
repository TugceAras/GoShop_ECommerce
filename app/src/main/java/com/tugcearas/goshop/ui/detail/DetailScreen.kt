package com.tugcearas.goshop.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tugcearas.goshop.R
import com.tugcearas.goshop.databinding.FragmentDetailScreenBinding
import com.tugcearas.goshop.util.extensions.click
import com.tugcearas.goshop.util.extensions.toastMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreen : Fragment() {

    private lateinit var binding: FragmentDetailScreenBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail_screen,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            detailToolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            addCartButton.click {
                detailViewModel.addProductToCart()
                requireContext().toastMessage(getString(R.string.click_cart_button_message))
            }

            favButton.click {
                detailViewModel.setFavoriteState()
            }
        }
        initObserver()
    }

    private fun initObserver(){

        detailViewModel.product.observe(viewLifecycleOwner){
           binding.product = it
        }

        detailViewModel.favState.observe(viewLifecycleOwner){
            binding.favButton.setImageResource(
                if (it.first) R.drawable.fav_icon_fill
                else R.drawable.fav_icon_outline
            )
            if (it.second){
                requireContext().toastMessage(
                    getString(
                        if (it.first) R.string.success_fav_button_message
                        else R.string.delete_from_favorites_message
                    )
                )
            }
        }
    }
}