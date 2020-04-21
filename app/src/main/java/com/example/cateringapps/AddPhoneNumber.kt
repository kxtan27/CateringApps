package com.example.cateringapps

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_add_phone.*
import java.util.concurrent.TimeUnit


class AddPhoneNumber : AppCompatActivity() {

    //private val currentUser = FirebaseAuth.getInstance().currentUser


    private var verificationId : String? = null
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    // private var numb : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_phone)



        button_send_verification.setOnClickListener {

            val phone = edit_text_phone.text.toString().trim()

            if (phone.isEmpty() || phone.length != 10) {
                edit_text_phone.error = "Enter a valid phone"
                edit_text_phone.requestFocus()
                return@setOnClickListener
            }

            val phoneNumber = '+' + ccp.selectedCountryCode + phone

            PhoneAuthProvider.getInstance()
                .verifyPhoneNumber(
                    phoneNumber,
                    60,
                    TimeUnit.SECONDS,
                    this,
                    phoneAuthCallbacks
                )

            layoutPhone.visibility = View.GONE
            layoutVerification.visibility = View.VISIBLE
        }

        button_verify.setOnClickListener {
            val code = edit_text_code.text.toString().trim()
            //val verificationId = numb

            if (code.isEmpty()) {
                edit_text_code.error = "Code required"
                edit_text_code.requestFocus()
                return@setOnClickListener
            }

            verificationId?.let {
                val credential = PhoneAuthProvider.getCredential(it,code)
                addPhoneNumber(credential)
            }

            layoutPhone.visibility = View.VISIBLE
            layoutVerification.visibility = View.GONE
            finish()
        }
    }

    //val view = setContentView(R.layout.fragment_add_phone)

    private fun Context.toast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    private val phoneAuthCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks()

        {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                phoneAuthCredential?.let {
                    addPhoneNumber(phoneAuthCredential)
                }
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                Toast.makeText(
                    baseContext, "Failed",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken)
            {

                super.onCodeSent(verificationId, token)
                resendToken = token
            }
        }


    private fun addPhoneNumber(phoneAuthCredential: PhoneAuthCredential) {
        FirebaseAuth.getInstance()
            .currentUser?.updatePhoneNumber(phoneAuthCredential)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    this.toast("Phone Added")
                    setContentView(R.layout.fragment_add_phone)

                } else {
                    this.toast(task.exception?.message!!)
                }
            }
    }

}





