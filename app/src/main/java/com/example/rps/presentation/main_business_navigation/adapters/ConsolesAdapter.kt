package com.example.rps.presentation.main_business_navigation.adapters

import android.content.Context
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.recyclerview.widget.RecyclerView
import com.example.rps.R
import com.example.rps.domain.model.Console

class ConsolesAdapter(
    private val context: Context,
    private val consoles: List<Console>,
    private val changeConsoleStateClickListener: ChangeConsoleStateClickListener
) : RecyclerView.Adapter<ConsolesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val consoleNameTextView: TextView = itemView.findViewById(R.id.console_name_text_view_cn)
        val serialNumberTextView: TextView = itemView.findViewById(R.id.serial_number_text_view_cn)
        val gamesInfoTextView: TextView = itemView.findViewById(R.id.games_info_text_view_cn)
        val freeStatusTextView: TextView = itemView.findViewById(R.id.free_status_text_view)
        val freeStatusImageView: ImageView = itemView.findViewById(R.id.free_status_image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.console_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.consoleNameTextView.text = consoles[position].consoleName
        holder.gamesInfoTextView.text = SpannableStringBuilder()
            .append("Игры и доп. информация:\n")
            .color(ContextCompat.getColor(context, R.color.white)) { append(consoles[position].gamesInfo) }
        holder.serialNumberTextView.text = SpannableStringBuilder()
            .append("Серийный номер:\n")
            .color(ContextCompat.getColor(context, R.color.white)) { append(consoles[position].serialNumber) }
        if (consoles[position].consoleState == true) {
            holder.freeStatusTextView.text = "Свободна"
            holder.freeStatusTextView.setTextColor(ContextCompat.getColor(context, R.color.free))
            holder.freeStatusImageView.setImageResource(R.drawable.ic_free)
        } else {
            holder.freeStatusTextView.text = "Занята"
            holder.freeStatusTextView.setTextColor(ContextCompat.getColor(context, R.color.no_free))
            holder.freeStatusImageView.setImageResource(R.drawable.ic_no_free)
        }


        holder.freeStatusImageView.setOnClickListener {
            if (consoles[position].consoleState == true) {
                changeConsoleStateClickListener.onStateClick(consoles[position])
                consoles[position].consoleState = false
                holder.freeStatusTextView.text = "Занята"
                holder.freeStatusTextView.setTextColor(ContextCompat.getColor(context, R.color.no_free))
                holder.freeStatusImageView.setImageResource(R.drawable.ic_no_free)
            } else {
                changeConsoleStateClickListener.onStateClick(consoles[position])
                consoles[position].consoleState = true
                holder.freeStatusTextView.text = "Свободна"
                holder.freeStatusTextView.setTextColor(ContextCompat.getColor(context, R.color.free))
                holder.freeStatusImageView.setImageResource(R.drawable.ic_free)
            }
        }
    }

    override fun getItemCount(): Int {
        return consoles.size
    }
}

interface ChangeConsoleStateClickListener {
    fun onStateClick(console: Console)
}