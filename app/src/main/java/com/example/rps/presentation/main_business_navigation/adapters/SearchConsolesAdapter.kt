package com.example.rps.presentation.main_business_navigation.adapters

import android.content.Context
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.recyclerview.widget.RecyclerView
import com.example.rps.R
import com.example.rps.domain.model.Console

class SearchConsolesAdapter(
    private val context: Context,
    private val consoles: List<Console>
) : RecyclerView.Adapter<SearchConsolesAdapter.ViewHolder>() {

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
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.console_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.headerTextView.text = SpannableStringBuilder().append("Предложение от: ${consoles[position].userName}")
        holder.cityTextView.text = SpannableStringBuilder()
            .append("Город:\n")
            .color(ContextCompat.getColor(context, R.color.white)) { append(consoles[position].userCity) }
        holder.consoleNameTextView.text = consoles[position].consoleName
        holder.gamesInfoTextView.text = SpannableStringBuilder()
            .append("Игры и доп. информация:\n")
            .color(ContextCompat.getColor(context, R.color.white)) { append(consoles[position].gamesInfo) }
        holder.weekdayPriceTextView.text = SpannableStringBuilder()
            .append("Цена в будни:\n")
            .color(ContextCompat.getColor(context, R.color.white)) { append(consoles[position].weekdayTextField) }
        holder.weekendPriceTextView.text = SpannableStringBuilder()
            .append("Цена в выходные:\n")
            .color(ContextCompat.getColor(context, R.color.white)) { append(consoles[position].weekendTextField) }
        holder.joypadCountTextView.text =SpannableStringBuilder()
            .append("Количество джойстиков: ")
            .color(ContextCompat.getColor(context, R.color.white)) { append(consoles[position].joypadCount) }
    }

    override fun getItemCount(): Int {
        return consoles.size
    }
}