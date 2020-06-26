package com.webnation.greendot.ui

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.webnation.greendot.R
import com.webnation.greendot.adapters.FibNumbersAdapter
import com.webnation.greendot.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_first.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val mainViewModel: MainViewModel by sharedViewModel()

    private val DEFAULT_ACTIONS_TO_HANDLE_AS_DONE_FOR_EDIT_TEXT = arrayListOf(EditorInfo.IME_ACTION_SEND, EditorInfo.IME_ACTION_GO, EditorInfo.IME_ACTION_SEARCH, EditorInfo.IME_ACTION_DONE)
    private val DEFAULT_KEYS_TO_HANDLE_AS_DONE_FOR_EDIT_TEXT = arrayListOf(KeyEvent.KEYCODE_ENTER, KeyEvent.KEYCODE_NUMPAD_ENTER)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editText.setOnDoneListener({
            mainViewModel.calculateFibNumber(editText.text.toString().toInt())
            collapseKeyboard(requireContext(), editText)
        })

        val adapter = FibNumbersAdapter(requireContext())
        numberlist.adapter = adapter

        mainViewModel.fibRepository.getAllFibNumber().observe(viewLifecycleOwner, Observer {
            adapter.setItems(it)
            mainViewModel.calculateTotalTimeOfCalculations(it)
        })

        mainViewModel.totalTimeOfCalculation.observe(viewLifecycleOwner, Observer {
            totalTimeOfCalculation.text = requireContext().getString(R.string.total_time_of_calculations, it.toString())
        })

    }

    fun AppCompatEditText.setOnDoneListener(function: () -> Unit, onKeyListener: View.OnKeyListener? = null, onEditorActionListener: TextView.OnEditorActionListener? = null,
                                            actionsToHandle: Collection<Int> = DEFAULT_ACTIONS_TO_HANDLE_AS_DONE_FOR_EDIT_TEXT,
                                            keysToHandle: Collection<Int> = DEFAULT_KEYS_TO_HANDLE_AS_DONE_FOR_EDIT_TEXT) {
        setOnEditorActionListener { v, actionId, event ->
            if (onEditorActionListener?.onEditorAction(v, actionId, event) == true)
                return@setOnEditorActionListener true
            if (actionsToHandle.contains(actionId)) {
                function.invoke()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        setOnKeyListener { v, keyCode, event ->
            if (onKeyListener?.onKey(v, keyCode, event) == true)
                return@setOnKeyListener true
            if (event.action == KeyEvent.ACTION_DOWN && keysToHandle.contains(keyCode)) {
                function.invoke()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }
}

fun collapseKeyboard(con: Context, v: View) {
    val imm = con.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(v.windowToken, 0)

}

