package com.example.quotesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quotesapp.databinding.ActivityFavouritesBinding
import com.example.quotesapp.helpers.SharedPrefHelper

class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavouritesBinding
    private lateinit var prefHelper: SharedPrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefHelper = SharedPrefHelper(this)

        showFavourite()

        binding.btnDelete.setOnClickListener {
            prefHelper.deleteValue(SharedPrefHelper.KEY_FAV_QUOTE)
            prefHelper.deleteValue(SharedPrefHelper.KEY_TIMESTAMP)
            showFavourite()
        }
    }

    private fun showFavourite() {
        val favQuote = prefHelper.getString(SharedPrefHelper.KEY_FAV_QUOTE)
        val timestamp = prefHelper.getString(SharedPrefHelper.KEY_TIMESTAMP)

        if (favQuote != null && timestamp != null) {
            binding.tvFavourite.text = favQuote
            binding.tvTimestamp.text = "Saved on: $timestamp"
        } else {
            binding.tvFavourite.text = "No favourite quote saved."
            binding.tvTimestamp.text = ""

        }
    }
}
