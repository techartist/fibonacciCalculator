package com.webnation.greendot.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.webnation.greendot.R
import com.webnation.greendot.database.FibNumber
import kotlinx.android.synthetic.main.item_fib_number.view.*


class FibNumbersAdapter(val context: Context) :
    RecyclerView.Adapter<FibNumbersAdapter.FibNumberViewHolder>() {

    private var fibNumbers = listOf<FibNumber>()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    fun setItems(list: List<FibNumber>) {
        this.fibNumbers = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FibNumberViewHolder {
        val itemView = inflater.inflate(R.layout.item_fib_number, parent, false)

        return FibNumberViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return fibNumbers.size
    }

    override fun onBindViewHolder(
        holder: FibNumbersAdapter.FibNumberViewHolder,
        position: Int
    ) {
        holder.bindData(fibNumbers[position])
    }

    inner class FibNumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(fibNumber: FibNumber) {

            itemView.number.text = fibNumber.inputNumber.toString()
            itemView.fibNumber.text = fibNumber.fibNumber.toString()
        }
    }
}