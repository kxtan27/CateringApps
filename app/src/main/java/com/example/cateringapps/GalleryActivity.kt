package com.example.cateringapps

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery2)
        auth = FirebaseAuth.getInstance()

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

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_menu -> {
                startActivity(Intent(this, GalleryActivity::class.java))
            }
            R.id.nav_aboutus -> {
                setContentView(R.layout.aboutus)
            }
            R.id.nav_update -> {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign out", Toast.LENGTH_SHORT).show()
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, MainActivity::class.java))
    }
}
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
