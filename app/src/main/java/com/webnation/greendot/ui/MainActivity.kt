package com.webnation.greendot.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.webnation.greendot.R
import com.webnation.greendot.viewmodel.MainViewModel
import com.webnation.greendot.viewmodel.NavigationFragment
import org.koin.android.ext.android.inject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        btn_navigation.text = mainViewModel.getButtonText()

        btn_navigation.setOnClickListener {

            if (mainViewModel.whichFragmentWeAreOn == NavigationFragment.FIRST) {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_FirstFragment_to_SecondFragment)
            } else {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
            mainViewModel.changeFragment()

            btn_navigation.text = mainViewModel.getButtonText()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (mainViewModel.whichFragmentWeAreOn == NavigationFragment.SECOND) {
            mainViewModel.changeFragment()
        }
        btn_navigation.text = mainViewModel.getButtonText()
    }

}