package com.tugcearas.goshop.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tugcearas.goshop.R
import com.tugcearas.goshop.data.model.ProductResponseModel
import com.tugcearas.goshop.databinding.FragmentProductsScreenBinding
import com.tugcearas.goshop.ui.products.adapter.ProductsAdapter
import com.tugcearas.goshop.util.extensions.gone
import com.tugcearas.goshop.util.extensions.toastMessage
import com.tugcearas.goshop.util.extensions.visible
import com.tugcearas.goshop.util.resource.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsScreen : Fragment() {

    private lateinit var binding:FragmentProductsScreenBinding
    private val productViewModel: ProductsViewModel by viewModels()
    private val productAdapter by lazy { ProductsAdapter(productOnClick = ::productOnClick)}
    private val args by navArgs<ProductsScreenArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.productsToolbar.apply {
            favButton.gone()
            customToolbar.navigationIcon = null
        }
        productViewModel.getSpecificCategory(args.category)
        initObserver()
    }

    private fun initAdapter(product: List<ProductResponseModel>){
        binding.apply {
            productsNameTextView.text = args.category
            productsRecyclerView.adapter = productAdapter
        }
        productAdapter.submitList(product)
    }

    private fun initObserver(){
        productViewModel.getCategory.observe(viewLifecycleOwner) {response ->
            when(response){
                is Resource.Success -> {
                    binding.apply {
                        productsNameTextView.visible()
                        productsProgressBar.gone()
                    }
                    response.data?.let {
                        initAdapter(it)
                    }
                }
                is Resource.Error -> {
                    binding.productsNameTextView.gone()
                    requireContext().toastMessage(getString(R.string.somethings_wrong))
                }
                is Resource.Loading ->{
                    binding.productsProgressBar.visible()
                }
                else -> {}
            }
        }
    }

    private fun productOnClick(product:ProductResponseModel){
        val action = ProductsScreenDirections.actionProductsScreenToDetailScreen(product)
        findNavController().navigate(action)
    }
}