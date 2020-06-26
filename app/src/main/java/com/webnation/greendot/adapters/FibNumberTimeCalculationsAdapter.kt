
package com.webnation.greendot.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.webnation.greendot.R
import com.webnation.greendot.database.FibNumber
import com.webnation.greendot.viewmodel.SeedTime
import kotlinx.android.synthetic.main.item_fib_number.view.*
import kotlinx.android.synthetic.main.item_fib_number.view.fibNumber
import kotlinx.android.synthetic.main.item_time_calculation.view.*


class FibNumberTimeCalculationsAdapter(val context: Context) : RecyclerView.Adapter<FibNumberTimeCalculationsAdapter.FibNumberViewHolder>() {

    private var fibNumbers = listOf<SeedTime>()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    fun setItems(list: List<SeedTime>) {
        this.fibNumbers = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FibNumberViewHolder {
        val itemView = inflater.inflate(R.layout.item_time_calculation, parent, false)

        return FibNumberViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return fibNumbers.size
    }

    override fun onBindViewHolder(
        holder: FibNumberTimeCalculationsAdapter.FibNumberViewHolder,
        position: Int
    ) {
        holder.bindData(fibNumbers[position], context)
    }

    inner class FibNumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(seedTime: SeedTime, context: Context) {

            itemView.fibNumber.text = context.getString(R.string.seed_number, seedTime.seed)
            itemView.timeCalculation.text = context.getString(R.string.time_of_calculations,seedTime.time.toString())
        }
    }
}
