package com.tugcearas.goshop.ui.onboarding

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tugcearas.goshop.R

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val imageList = listOf(
        R.drawable.onboarding_first_screen,
        R.drawable.onboarding_order_confirmed,
        R.drawable.onboarding_delivery
    )

    private val titleList = listOf(
        R.string.first_onboarding_screen_title,
        R.string.onboarding_second_screen_title,
        R.string.onboarding_third_screen_title,
    )

    private val descriptionList = listOf(
        R.string.first_onboarding_screen_description,
        R.string.onboarding_second_screen_description,
        R.string.onboarding_third_screen_description,
    )

    override fun getItemCount() = imageList.size

    override fun createFragment(position: Int) = OnBoardingFragment.newInstance(
        imageList[position],
        titleList[position],
        descriptionList[position]
    )
}