package com.kanneki.resultapi

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.kanneki.resultapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Result Activity
    private val requestNoDataLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->

        if (result.resultCode == RESULT_OK) {
            result.data?.getStringExtra(DetailContract.TAG_VALUE)?.let {
                Toast.makeText(applicationContext, "Result: $it", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Result Activity(自定義)
    private val requestDataLauncher = registerForActivityResult(DetailContract()) { result ->
        result?.let {
            Toast.makeText(applicationContext, "Result: $it", Toast.LENGTH_SHORT).show()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){ granted ->
        if (granted) {
            // User allow the permission
        } else {
            // User deny the permission
        }

        Toast.makeText(applicationContext, "Granted is $granted", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.changePageButton.setOnClickListener {
            // Result Activity
            Intent(this, DetailActivity::class.java).apply {
                requestNoDataLauncher.launch(this)
            }

            // Result Activity(自定義)
//            requestDataLauncher.launch(100)
        }

        binding.permissionButton.setOnClickListener {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }
}