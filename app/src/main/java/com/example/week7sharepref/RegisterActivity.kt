package com.example.week7sharepref

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.week7sharepref.databinding.ActivityRegisterBinding
import com.example.week7sharepref.model.User
import com.example.week7sharepref.response.SingleResponse
import com.example.week7sharepref.webService.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moveLogin()
        btnRegister()

    }

    private fun moveLogin(){
        binding.moveLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun btnRegister(){
        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString()
            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            ApiService.apiEndpoint().register(name, username, email, password).enqueue(object : Callback<SingleResponse<User>> {
                override fun onResponse(call: Call<SingleResponse<User>>, response: Response<SingleResponse<User>>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            Toast.makeText(applicationContext, "Register Successfully", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                            finish()
                        }
                    }
                }

                override fun onFailure(call: Call<SingleResponse<User>>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

}