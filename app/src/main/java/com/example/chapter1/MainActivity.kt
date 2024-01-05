package com.example.chapter1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.FragmentManager
import com.example.chapter1.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
                val textView = TextView(this@MainActivity)
                textView.text = "position $position"
                textView.gravity = Gravity.CENTER

                tab.customView = textView
                //tab.text =
            }
        }.attach()

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem]
                if(currentFragment is WebViewFragment) {
                    if(currentFragment.canGoBack()) {
                        currentFragment.goBack()
                    }else {
                        finishAffinity()
                    }
                } else {
                    finishAffinity()
                }
            }
        }

        this.onBackPressedDispatcher.addCallback(this, callback)
    }

}