package com.example.randomfacts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomfacts.databinding.ActivityDetailsBinding
import com.example.randomfacts.domain.model.NumbersFact

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater).also { setContentView(it.root) }

        getFactDetails()
    }

    private fun getFactDetails() {
        val numberFact = intent.extras?.getParcelable<NumbersFact>(MainActivity.DETAILS)

        with(binding) {
            numberDetailTV.text = numberFact?.number.toString()
            factDetailTV.text = numberFact?.text
        }
    }
}