package com.example.cateringapps

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_resetpw.*

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resetpw)

        auth = FirebaseAuth.getInstance()
        chang_pw.setOnClickListener {
            changepw()
        }
    }

    private fun changepw() {
        if (current_pw.text.isNotEmpty() &&
            new_pw.text.isNotEmpty() &&
            cnf_new_pw.text.isNotEmpty()
        ) {
            if (new_pw.text.toString().equals(cnf_new_pw.text.toString())) {
                val user = auth.currentUser
                if (user != null && user.email != null) {
                    val credential =
                        EmailAuthProvider.getCredential(
                            user.email!!,
                            current_pw.text.toString()
                        )
                    user.reauthenticate(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    "Re-Authentication Success.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                user.updatePassword(new_pw.text.toString())
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(
                                                this,
                                                "Password Change Successful!!!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            auth.signOut()
                                            startActivity(
                                                Intent(
                                                    this,
                                                    MainActivity::class.java
                                                )
                                            )
                                            finish()
                                        }
                                    }
                            } else {
                                Toast.makeText(
                                    this,
                                    "Change Password Not Successful!!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            } else {
                Toast.makeText(this, "Change Password Successful!!!.", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(this, "Please enter all the fields!!!", Toast.LENGTH_SHORT).show()
        }
    }

}