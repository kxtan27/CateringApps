package com.example.cateringapps

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        sign_up_page_button.setOnClickListener {
            finish()
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        sign_in_page_button.setOnClickListener {
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
