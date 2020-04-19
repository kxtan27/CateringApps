package com.example.cateringapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    /** upload */
    //lateinit var alertDialog:AlertDialog
    lateinit var storageReference: StorageReference

    companion object {
        private val PICK_IMAGE_CODE = 1000
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.fragment_bar, null)
        val message = dialogView.findViewById<TextView>(R.id.message)
        message.text = "Please wait...changing"
        builder.setView(dialogView)
        val dialog = builder.create()
        dialog.show()

        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_CODE) {
            dialog.show()
            val uploadTask = storageReference!!.putFile(data!!.data!!)
            val task = uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    Toast.makeText(this@ProfileActivity, "Failed", Toast.LENGTH_SHORT).show()
                }
                storageReference!!.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    val url = downloadUri!!.toString()
                        .substring(0, downloadUri.toString().indexOf("&token"))
                    Log.d("DirectLink", url)
                    dialog.dismiss()
                    Picasso.get().load(url).into(image_profile)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()


        /**     Upload                   */
        //init
        //alertDialog = SpotsDialog.Builder().setContext(this).build()

        storageReference = FirebaseStorage.getInstance().getReference("image uploads") // file name

        //event
        change_avatar.setOnClickListener() {
            //Pick image
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "select picture"), PICK_IMAGE_CODE)
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
        startActivity(Intent(this, MainActivity::class.java))
    }
}