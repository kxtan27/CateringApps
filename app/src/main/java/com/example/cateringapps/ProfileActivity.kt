package com.example.cateringapps

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity:AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()

        button_changepw.setOnClickListener{
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }

        button_sign_out.setOnClickListener{
            Toast.makeText(this, "Sign Out", Toast.LENGTH_SHORT).show()
            signout()
        }
    }

    private fun signout(){
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, MainActivity::class.java))
    }
}