package com.example.chapter1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.FragmentManager
import com.example.chapter1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val container = binding.fragmentContainer

        binding.button1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(container.id, WebViewFragment())
                    .commit()
            }
        }

        binding.button2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(container.id, BFragment())
                    .commit()
            }
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentFragment = supportFragmentManager.fragments[0]
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