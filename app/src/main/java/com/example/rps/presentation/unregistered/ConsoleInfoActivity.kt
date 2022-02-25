package com.example.rps.presentation.unregistered

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.core.content.ContextCompat
import androidx.core.text.color
import com.example.rps.R
import com.example.rps.databinding.ActivityConsoleInfoBinding
import com.example.rps.domain.model.Console
import android.content.Intent
import android.net.Uri


class ConsoleInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConsoleInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsoleInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.closeButton.setOnClickListener { onBackPressed() }

        val console = intent.getParcelableExtra<Console>("console")

        if (console != null) {
            binding.cityTextView.text = SpannableStringBuilder()
                .color(
                    ContextCompat.getColor(
                        this,
                        R.color.text_hint_color
                    )
                ) { append("Город: ") }
                .append(console.userCity)
            binding.nameTextView.text = SpannableStringBuilder()
                .color(
                    ContextCompat.getColor(
                        this,
                        R.color.text_hint_color
                    )
                ) { append("Имя: ") }
                .append(console.userName)
            binding.telephoneTextView.text = SpannableStringBuilder()
                .color(
                    ContextCompat.getColor(
                        this,
                        R.color.text_hint_color
                    )
                ) { append("Телефон: ") }
                .append(console.userTelephone)
            binding.consoleNameTextView.text = SpannableStringBuilder()
                .color(
                    ContextCompat.getColor(
                        this,
                        R.color.text_hint_color
                    )
                ) { append("Приставка: ") }
                .append(console.consoleName)
            binding.joypadCountTextView.text = SpannableStringBuilder()
                .color(
                    ContextCompat.getColor(
                        this,
                        R.color.text_hint_color
                    )
                ) { append("Количество джойстиков: ") }
                .append(console.joypadCount)
            binding.weekdayPriceTextView.text = SpannableStringBuilder()
                .color(
                    ContextCompat.getColor(
                        this,
                        R.color.text_hint_color
                    )
                ) { append("Цена в будни: ") }
                .append(console.weekdayTextField)
            binding.weekendPriceTextView.text = SpannableStringBuilder()
                .color(
                    ContextCompat.getColor(
                        this,
                        R.color.text_hint_color
                    )
                ) { append("Цена в выходные: ") }
                .append(console.weekendTextField)
            binding.gamesInfoTextView.text = SpannableStringBuilder()
                .color(
                    ContextCompat.getColor(
                        this,
                        R.color.text_hint_color
                    )
                ) { append("Игры и дополнительная информация:\n") }
                .append(console.gamesInfo)
        }

        binding.callButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${console?.userTelephone}"))
            startActivity(intent)
        }
    }
}