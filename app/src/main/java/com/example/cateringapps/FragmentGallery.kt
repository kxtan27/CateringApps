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

class FragmentGallery : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gallery)
        auth = FirebaseAuth.getInstance()

        add_a.setOnClickListener {
            finish()
            startActivity(Intent(this, OrderActivity::class.java))
        }
        add_b.setOnClickListener {
            finish()
            startActivity(Intent(this, OrderActivity::class.java))
        }
        add_c.setOnClickListener {
            finish()
            startActivity(Intent(this, OrderActivity::class.java))
        }
        add_d.setOnClickListener {
            finish()
            startActivity(Intent(this, OrderActivity::class.java))
        }
        add_e.setOnClickListener {
            finish()
            startActivity(Intent(this, OrderActivity::class.java))
        }

        menu_a.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Menu")
            val view = layoutInflater.inflate(R.layout.fragment_menua, null)
            builder.setView(view)
            builder.setNegativeButton("Close",DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }
        menu_b.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Menu")
            val view = layoutInflater.inflate(R.layout.fragment_menub, null)
            builder.setView(view)
            builder.setNegativeButton("Close",DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }
        menu_c.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Menu")
            val view = layoutInflater.inflate(R.layout.fragment_menuc, null)
            builder.setView(view)
            builder.setNegativeButton("Close",DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }
        menu_d.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Menu")
            val view = layoutInflater.inflate(R.layout.fragment_menud, null)
            builder.setView(view)
            builder.setNegativeButton("Close",DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }
        menu_e.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Menu")
            val view = layoutInflater.inflate(R.layout.fragment_menue, null)
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
                finish()
            }
            R.id.nav_aboutus -> {
                finish()
                startActivity(Intent(this, FragmentAboutUs::class.java))
            }
            R.id.nav_rateus -> {
                finish()
                startActivity(Intent(this, RatingActivity::class.java))
            }
            R.id.nav_update -> {
                finish()
                startActivity(Intent(this, ProfileActivity::class.java))

            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign out", Toast.LENGTH_SHORT).show()
                FirebaseAuth.getInstance().signOut()
                finish()
                startActivity(Intent(this, MainActivity::class.java))
    }
}
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}
