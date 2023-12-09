package com.example.androidadvanced_mobileafternoon.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.androidadvanced_mobileafternoon.R
import com.example.androidadvanced_mobileafternoon.databinding.ActivityMainBinding
import com.example.androidadvanced_mobileafternoon.fragment.ApiJsonRetrofitFragment
import com.example.androidadvanced_mobileafternoon.fragment.HomeFragment
import com.example.androidadvanced_mobileafternoon.fragment.TambahFragment
import com.example.androidadvanced_mobileafternoon.utils.CustomSharePreference
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

private lateinit var binding: ActivityMainBinding
private lateinit var pref: CustomSharePreference

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    NavigationBarView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // toolbar
        setSupportActionBar(binding.toolbar)

        // navigation drawer
        val toggle = ActionBarDrawerToggle(
            this,
            binding.mainActivity,
            binding.toolbar,
            R.string.open_nav,
            R.string.close_nav
        )
        binding.mainActivity.addDrawerListener(toggle)
        toggle.syncState()

        binding.navDrawer.setNavigationItemSelectedListener(this)

        binding.bottomNav.setOnItemSelectedListener(this)
        replaceFragment(HomeFragment())

        init()
        checkCondition()

    }

    private fun init() {
        pref = CustomSharePreference(this@MainActivity)
    }

    private fun loginClick() {
        pref.saveLogin(1).let {
            val intent = Intent(this@MainActivity, SharePreferenceActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun checkCondition() {
        if (!pref.getLogin().equals(0)) {
            val intent = Intent(this@MainActivity, SharePreferenceActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_container, fragment)
        fragmentTransaction.commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.toolbar_notif -> {
                Toast.makeText(this, "Kamu menekan notifikasi", Toast.LENGTH_SHORT).show()
            }

            R.id.toolbar_sharepref -> {
                loginClick()
            }

            R.id.toolbar_tab_layout -> {
                val intent = Intent(this@MainActivity, TabLayoutActivity::class.java)
                startActivity(intent)
            }

            R.id.toolbar_logout -> finish()
        }
        return true
    }


    override fun onNavigationItemSelected(itemnav: MenuItem): Boolean {
        when (itemnav.itemId) {
            R.id.navbar_materi -> {
                Toast.makeText(this, "Kamu menekan Tentang", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.navbar_brain_trainer -> {
                val intent = Intent(this@MainActivity, BrainTrainerActivity::class.java)
                startActivity(intent)
                return true
            }

            // nav bottom
            R.id.home -> {
                replaceFragment(HomeFragment())
                return true
            }
            R.id.tambah -> {
                replaceFragment(TambahFragment())
                return true
            }
            R.id.profile -> {
                replaceFragment(ApiJsonRetrofitFragment())
                return true
            }
            else -> return false

        }
    }

}