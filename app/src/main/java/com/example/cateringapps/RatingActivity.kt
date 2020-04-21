package com.example.cateringapps

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_rate.*

class RatingActivity : AppCompatActivity() {

    lateinit var service: RatingBar
    lateinit var fnb : RatingBar
    lateinit var comment : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate)

        service = findViewById(R.id.service)
        fnb = findViewById(R.id.fnb)
        comment = findViewById(R.id.comment)

        submit_button.setOnClickListener {
            rate_us()
            finish()
            startActivity(Intent(this, FragmentGallery::class.java))
        }
    }

    private fun rate_us(){
        if(comment.text.toString().isEmpty()){
            comment.error = "Please give us some comment."
            comment.requestFocus()
        }

        val ref = FirebaseDatabase.getInstance().getReference("rating")
        val ratingId = ref.push().key
        val rating = Rating(
            ratingId,
            service.numStars,
            fnb.numStars,
            comment.text.toString()
        )

        ref.child(ratingId.toString()).setValue(rating).addOnCompleteListener {
            Toast.makeText(
                this,
                "Thanks for you feedback.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}