package com.example.cateringapps

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        button.setOnClickListener{
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        menu_a.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Menu")
            val view = layoutInflater.inflate(R.layout.menua, null)
            builder.setView(view)
            builder.setNegativeButton("Close",DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }

        menu_b.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Menu")
            val view = layoutInflater.inflate(R.layout.menub, null)
            builder.setView(view)
            builder.setNegativeButton("Close",DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }

        menu_c.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Menu")
            val view = layoutInflater.inflate(R.layout.menuc, null)
            builder.setView(view)
            builder.setNegativeButton("Close",DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }

        menu_d.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Menu")
            val view = layoutInflater.inflate(R.layout.menud, null)
            builder.setView(view)
            builder.setNegativeButton("Close",DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }

        menu_e.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Menu")
            val view = layoutInflater.inflate(R.layout.menue, null)
            builder.setView(view)
            builder.setNegativeButton("Close",DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }
    }
}