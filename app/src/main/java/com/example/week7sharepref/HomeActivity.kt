package com.example.week7sharepref

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week7sharepref.databinding.ActivityHomeBinding
import com.example.week7sharepref.webService.Constant

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnLogout()

    }

    private fun btnLogout(){
        binding.btnLogout.setOnClickListener {
            Constant.clearToken(this)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}