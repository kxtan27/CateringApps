package com.example.cateringapps

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_order.*


class OrderActivity : AppCompatActivity() {

    lateinit var name: EditText
    lateinit var address: EditText
    lateinit var phone: EditText
    lateinit var ppl: EditText
    lateinit var date: EditText
    lateinit var time: EditText
    lateinit var menu : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        name = findViewById(R.id.name)
        address = findViewById(R.id.address)
        phone = findViewById(R.id.phone)
        ppl = findViewById(R.id.ppl)
        date = findViewById(R.id.date)
        time = findViewById(R.id.time)
        menu = findViewById(R.id.menu)

        confirm_order.setOnClickListener {
            confirm_order()
            finish()
            startActivity(Intent(this, FragmentGallery::class.java))
        }

    }

    private fun confirm_order() {
        if (name.text.toString().isEmpty()) {
            name.error = "Please enter email"
            name.requestFocus()
        }

        if (address.text.toString().isEmpty()) {
            address.error = "Please enter address"
            address.requestFocus()
        }

        if (phone.text.toString().isEmpty()) {
            phone.error = "Please enter your contact no."
            phone.requestFocus()
        }

        if (ppl.text.toString().isEmpty()) {
            ppl.error = "How many people will attend?"
            ppl.requestFocus()
        }

        if (date.text.toString().isEmpty()) {
            date.error = "When you plan to organize this event?"
            date.requestFocus()
        }

        if (time.text.toString().isEmpty()) {
            time.error = "What time would you like to start this event?"
            time.requestFocus()
        }

        if (menu.text.toString().isEmpty()){
            menu.error = "Please enter menu."
            menu.requestFocus()
        }

        val ref = FirebaseDatabase.getInstance().getReference("customer")
        val customerId = ref.push().key
        val customer = Customer(
            customerId,
            name.text.toString(),
            address.text.toString(),
            phone.text.toString(),
            ppl.text.toString(),
            date.text.toString(),
            time.text.toString(),
            menu.text.toString()
        )
        ref.child(customerId.toString()).setValue(customer).addOnCompleteListener {
            Toast.makeText(
                this,
                "Order successful!!! We will re-confirm the order with you.Thank You.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}