package com.example.quotesapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quotesapp.databinding.ActivityCategoryBinding
import com.example.quotesapp.helpers.QuotesHelper
import com.example.quotesapp.helpers.SharedPrefHelper
import java.text.SimpleDateFormat
import java.util.*

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var prefHelper: SharedPrefHelper
    private var currentQuote = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefHelper = SharedPrefHelper(this)

        val category = intent.getStringExtra("category") ?: "Comedy"
        binding.tvCategoryTitle.text = "$category Quotes"

        currentQuote = QuotesHelper.getRandomQuoteByCategory(category)
        binding.tvCategoryQuote.text = currentQuote

        binding.btnSaveFavourite.setOnClickListener {
            val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            prefHelper.saveString(SharedPrefHelper.KEY_FAV_QUOTE, currentQuote)
            prefHelper.saveString(SharedPrefHelper.KEY_TIMESTAMP, timestamp)
            Toast.makeText(this, "Saved as favourite", Toast.LENGTH_SHORT).show()
        }

        binding.btnNewQuote.setOnClickListener {
            currentQuote = QuotesHelper.getRandomQuoteByCategory(category)
            binding.tvCategoryQuote.text = currentQuote
        }
    }
}
