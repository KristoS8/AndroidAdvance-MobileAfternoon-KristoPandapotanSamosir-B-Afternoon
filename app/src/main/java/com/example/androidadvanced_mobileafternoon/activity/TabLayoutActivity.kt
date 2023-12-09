package com.example.androidadvanced_mobileafternoon.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.androidadvanced_mobileafternoon.R
import com.example.androidadvanced_mobileafternoon.adapter.ViewPagerAdapter
import com.example.androidadvanced_mobileafternoon.databinding.ActivityTabLayoutBinding
import com.example.androidadvanced_mobileafternoon.fragment.AlarmManagerFragmen
import com.example.androidadvanced_mobileafternoon.fragment.FragmentKedua

private lateinit var binding: ActivityTabLayoutBinding

class TabLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager(binding.viewpagerMedia)
        binding.tabsMedia.setupWithViewPager(binding.viewpagerMedia)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AlarmManagerFragmen(), "Alarm Manager")
        adapter.addFragment(FragmentKedua(), "Work Manager")
        viewPager.adapter = adapter
    }
}