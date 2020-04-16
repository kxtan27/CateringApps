package com.example.cateringapps

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.editText_email

class RegisterActivity: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        sign_up_button.setOnClickListener{
            sign_up_user()
        }
    }

    private fun sign_up_user(){
        if(editText_email.text.toString().isEmpty()){
            editText_email.error = "Please Enter Email!!!"
            editText_email.requestFocus()
        }

        if(Patterns.EMAIL_ADDRESS.matcher(editText_email.text.toString()).matches()){
            editText_email.error = "Please Enter Email!!!"
            editText_email.requestFocus()
        }

        if(editText_password.text.toString().isEmpty()){
            editText_password.error = "Please Enter Password!!!"
            editText_password.requestFocus()
        }

        auth.createUserWithEmailAndPassword(editText_email.text.toString(), editText_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            }
                        }
                } else {
                    Toast.makeText(baseContext, "Sign Up Failed!!! Please Try Again. ",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }


}