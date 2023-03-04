package com.tugcearas.goshop.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.tugcearas.goshop.R
import com.tugcearas.goshop.databinding.FragmentViewPagerBinding
import com.tugcearas.goshop.util.extensions.click

class ViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        with(binding) {
            ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle).apply {
                viewpager.adapter = this
                viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        when (position) {
                            0 -> firstOnBoardingButton.click { viewpager.currentItem++ }
                            1 -> firstOnBoardingButton.click { viewpager.currentItem++ }
                            else -> firstOnBoardingButton.click {
                                findNavController().navigate(R.id.action_viewPagerFragment_to_homeScreen)
                            }
                        }
                    }
                })
                viewpager.isUserInputEnabled = false
            }
        }
        return binding.root
    }
}