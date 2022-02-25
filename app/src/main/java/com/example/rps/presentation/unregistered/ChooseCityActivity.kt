package com.example.rps.presentation.unregistered

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rps.R
import com.example.rps.databinding.ActivityChooseCityBinding
import co.lujun.androidtagview.TagView
import co.lujun.androidtagview.TagView.OnTagClickListener


class ChooseCityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseCityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseCityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener { onBackPressed() }

        val cities: List<String> = resources.getStringArray(R.array.cities).toList()
        binding.tagLayout.tags = cities

        binding.tagLayout.setOnTagClickListener(object : OnTagClickListener {
            override fun onTagClick(position: Int, text: String) {
                val intent = Intent(this@ChooseCityActivity, UnregisteredActivity::class.java)
                intent.putExtra("city", text)
                startActivity(intent)
            }
            override fun onTagLongClick(position: Int, text: String) {}
            override fun onSelectedTagDrag(position: Int, text: String) {}
            override fun onTagCrossClick(position: Int) {}
        })
    }
}