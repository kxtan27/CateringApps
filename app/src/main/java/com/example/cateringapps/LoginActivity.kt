package com.example.cateringapps

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        sign_in_button.setOnClickListener {
            doLogin()
        }

        forget_pw_button.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Enter your email.")
            val view = layoutInflater.inflate(R.layout.dialog_forgetpw, null)
            val email = view.findViewById<EditText>(R.id.email)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->
                forgotpassword(email)
            })
            builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }
    }

    private fun forgotpassword(email: EditText) {
        if (email.text.toString().isEmpty()) {
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            return
        }

        auth.sendPasswordResetEmail(email.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Email sent.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun doLogin() {
        if (username.text.toString().isEmpty()) {
            username.error = "Please Enter Email!!!"
            username.requestFocus()
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()) {
            username.error = "Please Enter Email!!!"
            username.requestFocus()
        }

        if (password.text.toString().isEmpty()) {
            password.error = "Please Enter Password!!!"
            password.requestFocus()
        }

        auth.signInWithEmailAndPassword(username.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            if (currentUser.isEmailVerified) {
                startActivity(Intent(this, FragmentGallery::class.java))
            }
        } else {
            Toast.makeText(
                baseContext, "Please verify your email address",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}