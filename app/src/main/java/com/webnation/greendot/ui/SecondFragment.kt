package com.webnation.greendot.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.webnation.greendot.R
import com.webnation.greendot.adapters.FibNumberTimeCalculationsAdapter
import com.webnation.greendot.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_second.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FibNumberTimeCalculationsAdapter(requireContext())

        listofTimeCalculated.adapter = adapter

        mainViewModel.fibRepository.getAllTimesForSeeds() .observe(viewLifecycleOwner, Observer {
            adapter.setItems(mainViewModel.calculateTotalTimeOfCalculationsForEachSeed(it))
        })

    }
}