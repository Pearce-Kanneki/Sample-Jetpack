package com.kanneki.resultapi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kanneki.resultapi.databinding.ActivityDetialBinding

class DetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Intent().apply {
            putExtra(DetailContract.TAG_VALUE, "Exit Detail")
            setResult(RESULT_OK, this)
        }
    }
}