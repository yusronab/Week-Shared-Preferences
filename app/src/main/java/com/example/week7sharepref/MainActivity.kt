package com.example.week7sharepref

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.week7sharepref.databinding.ActivityMainBinding
import com.example.week7sharepref.model.User
import com.example.week7sharepref.response.SingleResponse
import com.example.week7sharepref.webService.ApiService
import com.example.week7sharepref.webService.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moveRegister()
        isLogin()
        btnLogin()

    }

    private fun moveRegister(){
        binding.moveRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    private fun isLogin(){
        val token = Constant.getToken(this)
        if (!token.equals("Undef")){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun btnLogin(){
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            ApiService.apiEndpoint().login(username, password).enqueue(object : Callback<SingleResponse<User>> {
                override fun onResponse(call: Call<SingleResponse<User>>, response: Response<SingleResponse<User>>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            Constant.setToken(this@MainActivity, body.data.token)
                            Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
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