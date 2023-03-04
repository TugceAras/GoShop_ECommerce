package com.tugcearas.goshop.ui.splash

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tugcearas.goshop.R
import com.tugcearas.goshop.databinding.FragmentSplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment() {

    private lateinit var binding:FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater,container,false)
        activity?.window?.statusBarColor = activity?.getColor(R.color.main_color)!!
        Handler(Looper.getMainLooper()).postDelayed({
            if (onBoardingFinished()){
                findNavController().navigate(R.id.action_splashScreen_to_homeScreen)
            }
            else{
                findNavController().navigate(R.id.action_splashScreen_to_viewPagerFragment)
            }
        },5000)
        return binding.root
    }

    private fun onBoardingFinished():Boolean{
        val getSharedPreferences = requireActivity().getSharedPreferences("onBoarding",Context.MODE_PRIVATE)
        return getSharedPreferences.getBoolean("Finished",false)
    }
}