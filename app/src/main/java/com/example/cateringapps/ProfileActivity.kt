package com.example.cateringapps

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val currentUser = FirebaseAuth.getInstance().currentUser
    //lateinit var storageReference: StorageReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()


        currentUser?.let { user ->
            Glide.with(this)
                .load(user.photoUrl)
                .into(image_profile)

            text_displayUsername.text = user.displayName
            text_displayEmail.text = user.email
            text_displayPhone.text = user.phoneNumber
        }

        /**     Upload                   */
        //init
        //alertDialog = SpotsDialog.Builder().setContext(this).build()

        // storageReference = FirebaseStorage.getInstance().getReference("image uploads") // file name


        button_profile.setOnClickListener {
            startActivity(Intent(this, EditProfile::class.java))
        }

        button_changepw.setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }

        button_sign_out.setOnClickListener {
            Toast.makeText(this, "Sign Out", Toast.LENGTH_SHORT).show()
            signout()
        }
    }


    private fun signout() {
        FirebaseAuth.getInstance().signOut()
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

}

