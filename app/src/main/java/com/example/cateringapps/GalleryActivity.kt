package com.example.cateringapps

import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        menu_a.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Menu")
            val view = layoutInflater.inflate(R.layout.activity_menua, null)
            builder.setView(view)
            builder.setNegativeButton("Close",DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }

        menu_b.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Menu")
            val view = layoutInflater.inflate(R.layout.activity_menub, null)
            builder.setView(view)
            builder.setNegativeButton("Close",DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }

        menu_c.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Menu")
            val view = layoutInflater.inflate(R.layout.activity_menuc, null)
            builder.setView(view)
            builder.setNegativeButton("Close",DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }

        menu_d.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Menu")
            val view = layoutInflater.inflate(R.layout.activity_menud, null)
            builder.setView(view)
            builder.setNegativeButton("Close",DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }

        menu_e.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Menu")
            val view = layoutInflater.inflate(R.layout.activity_menue, null)
            builder.setView(view)
            builder.setNegativeButton("Close",DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }
    }
}