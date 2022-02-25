package com.example.rps.presentation.main_business_navigation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rps.R
import com.example.rps.domain.model.Console
import com.google.android.gms.tasks.Task
import java.util.*
import kotlin.collections.ArrayList

class SearchConsolesAdapter(
    private val context: Context,
    private val consoles: List<Console>,
    private val consoleClickListener: ConsoleClickListener
) : RecyclerView.Adapter<SearchConsolesAdapter.ViewHolder>(), Filterable {

    var consolesFilterList = ArrayList<Console>()

    init {
        consolesFilterList = consoles as ArrayList<Console>
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headerTextView: TextView = itemView.findViewById(R.id.header_text_view)
        val consoleNameTextView: TextView = itemView.findViewById(R.id.console_name_text_view)
        val weekdayPriceTextView: TextView = itemView.findViewById(R.id.weekday_price_text_view)
        val weekendPriceTextView: TextView = itemView.findViewById(R.id.weekend_price_text_view)
        val joypadCountTextView: TextView = itemView.findViewById(R.id.joypad_count_text_view)
        val gamesInfoTextView: TextView = itemView.findViewById(R.id.games_info_text_view)
        val cityTextView: TextView = itemView.findViewById(R.id.city_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.console_item_in_search, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            consoleClickListener.onConsoleClick(consolesFilterList[position])
        }

        holder.headerTextView.text = SpannableStringBuilder().append("Предложение от: ${consolesFilterList[position].userName}")
        holder.cityTextView.text = SpannableStringBuilder()
            .append("Город:\n")
            .color(ContextCompat.getColor(context, R.color.white)) { append(consolesFilterList[position].userCity) }
        holder.consoleNameTextView.text = consolesFilterList[position].consoleName
        holder.gamesInfoTextView.text = SpannableStringBuilder()
            .append("Игры и доп. информация:\n")
            .color(ContextCompat.getColor(context, R.color.white)) { append(consolesFilterList[position].gamesInfo) }
        holder.weekdayPriceTextView.text = SpannableStringBuilder()
            .append("Цена в будни:\n")
            .color(ContextCompat.getColor(context, R.color.white)) { append(consolesFilterList[position].weekdayTextField) }
        holder.weekendPriceTextView.text = SpannableStringBuilder()
            .append("Цена в выходные:\n")
            .color(ContextCompat.getColor(context, R.color.white)) { append(consolesFilterList[position].weekendTextField) }
        holder.joypadCountTextView.text =SpannableStringBuilder()
            .append("Количество джойстиков: ")
            .color(ContextCompat.getColor(context, R.color.white)) { append(consolesFilterList[position].joypadCount) }
    }

    override fun getItemCount(): Int {
        return consolesFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val stringSearch = constraint.toString()
                consolesFilterList = if (stringSearch.isEmpty()) {
                    consoles as ArrayList<Console>
                } else {
                    val resultList = ArrayList<Console>()
                    for (console in consoles) {
                        if (console.userCity?.lowercase()?.contains(stringSearch.lowercase()) == true) {
                            resultList.add(console)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = consolesFilterList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                consolesFilterList = results?.values as ArrayList<Console>
                notifyDataSetChanged()
            }

        }
    }

}

interface ConsoleClickListener {
    fun onConsoleClick(console: Console)
}