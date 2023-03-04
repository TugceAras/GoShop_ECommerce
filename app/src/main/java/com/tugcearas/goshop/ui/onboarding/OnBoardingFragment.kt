package com.tugcearas.goshop.ui.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tugcearas.goshop.databinding.FragmentOnBoardingBinding
import com.tugcearas.goshop.util.extensions.click

class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            arguments?.let {
                firstOnboardingImageView.setImageResource(it.getInt(IMAGE))
                firstOnboardingTitle.text = requireContext().getString(it.getInt(TITLE))
                firstOnboardingDescription.text = requireContext().getString(it.getInt(DESCRIPTION))
            }
        }
        onBoardingFinished()
    }

    companion object {

        private const val IMAGE = "image"
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"

        @JvmStatic
        fun newInstance(image: Int, title: Int, description: Int) =
            OnBoardingFragment().apply {
                arguments = Bundle().apply {
                    putInt(IMAGE, image)
                    putInt(TITLE, title)
                    putInt(DESCRIPTION, description)
                }
            }
    }

    private fun onBoardingFinished(){
        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putBoolean("Finished",true)
        edit.apply()
    }
}