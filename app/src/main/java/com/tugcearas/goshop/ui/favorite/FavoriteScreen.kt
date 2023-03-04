package com.tugcearas.goshop.ui.favorite

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tugcearas.goshop.R
import com.tugcearas.goshop.databinding.FragmentFavoriteScreenBinding
import com.tugcearas.goshop.ui.favorite.adapter.FavoriteAdapter
import com.tugcearas.goshop.util.extensions.gone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteScreen : Fragment() {

    private lateinit var binding: FragmentFavoriteScreenBinding
    private val favViewModel: FavoriteViewModel by viewModels()
    private val favAdapter by lazy { FavoriteAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favToolbar.apply {
            toolbarImageView.gone()
            toolbarTitle.gone()
            favButton.gone()
            customToolbar.navigationIcon = null
        }
        initAdapter()
        initObserver()
    }

    private fun initAdapter(){
        binding.favRecyclerView.adapter = favAdapter
        favAdapter.onDeleteClick = {
            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.alertdialog_set_title))
                .setMessage(getString(R.string.alertdialog_set_message))
                .setPositiveButton(R.string.alertdialog_positive_button) { _, _ ->
                    favViewModel.deleteFromFavorites(it)
                }
                .setNegativeButton(R.string.alertdialog_negative_button,null)
                .show()
        }
    }

    private fun initObserver(){
        favViewModel.getFavoriteProduct.observe(viewLifecycleOwner){
            favAdapter.submitList(it)
        }
    }
}