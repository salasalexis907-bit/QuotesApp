package com.example.quotesapp.helpers

object QuotesHelper {

    private val quotes = mapOf(
        "Comedy" to listOf(
            "Comedy is simply a funny way of being serious.",
            "Laughter is timeless, imagination has no age.",
            "A day without laughter is a day wasted."
        ),
        "Funny" to listOf(
            "I'm on a seafood diet. I see food and I eat it.",
            "Life is short. Smile while you still have teeth.",
            "My bed is a magical place — I suddenly remember everything I had to do."
        ),
        "Love" to listOf(
            "Love all, trust a few, do wrong to none.",
            "We accept the love we think we deserve.",
            "Love is composed of a single soul inhabiting two bodies."
        )
    )

    fun getRandomQuoteByCategory(category: String): String {
        val list = quotes[category]
        return if (!list.isNullOrEmpty()) list.random()
        else "No quotes available for $category."
    }

    fun getRandomQuote(): String {
        val all = quotes.values.flatten()
        return if (all.isNotEmpty()) all.random() else "No quotes available."
    }
}
