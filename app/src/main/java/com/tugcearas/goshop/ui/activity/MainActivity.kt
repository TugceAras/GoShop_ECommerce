package com.tugcearas.goshop.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.tugcearas.goshop.R
import com.tugcearas.goshop.databinding.ActivityMainBinding
import com.tugcearas.goshop.util.extensions.gone
import com.tugcearas.goshop.util.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        with(binding) {
            bottomNavView.setupWithNavController(navController)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.splashScreen, R.id.viewPagerFragment,
                    R.id.cartScreen , R.id.paymentScreen,
                    R.id.successScreen-> bottomNavView.gone()
                    else -> bottomNavView.visible()
                }
            }
        }
    }
}