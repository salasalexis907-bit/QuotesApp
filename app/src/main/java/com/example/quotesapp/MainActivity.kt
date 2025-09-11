package com.example.quotesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quotesapp.databinding.ActivityMainBinding
import com.example.quotesapp.helpers.QuotesHelper
import com.example.quotesapp.helpers.SharedPrefHelper
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var prefHelper: SharedPrefHelper
    private var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefHelper = SharedPrefHelper(this)

        // show initial random quote
        binding.tvQuote.text = QuotesHelper.getRandomQuote()

        // disable favourites button if no saved favourite
        binding.btnFavourites.isEnabled =
            prefHelper.getString(SharedPrefHelper.KEY_FAV_QUOTE) != null

        // button listeners — start CategoryActivity and pass the category
        binding.btnComedy.setOnClickListener {
            val i = Intent(this, CategoryActivity::class.java)
            i.putExtra("category", "Comedy")
            startActivity(i)
        }

        binding.btnFunny.setOnClickListener {
            val i = Intent(this, CategoryActivity::class.java)
            i.putExtra("category", "Funny")
            startActivity(i)
        }

        binding.btnLove.setOnClickListener {
            val i = Intent(this, CategoryActivity::class.java)
            i.putExtra("category", "Love")
            startActivity(i)
        }

        binding.btnFavourites.setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }

        // rotate quote every 5 seconds (bonus)
        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    binding.tvQuote.text = QuotesHelper.getRandomQuote()
                }
            }
        }, 5000, 5000)
    }

    override fun onResume() {
        super.onResume()
        // re-enable favourites button if a favourite exists now
        binding.btnFavourites.isEnabled =
            prefHelper.getString(SharedPrefHelper.KEY_FAV_QUOTE) != null
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}
