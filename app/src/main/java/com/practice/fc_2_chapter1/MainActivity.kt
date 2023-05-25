package com.practice.fc_2_chapter1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import com.practice.fc_2_chapter1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

// onBackPressed()가 deprecated 되어서 바꿔본 코드 -> OnBackPressedCallback()
//    private val callback = object : OnBackPressedCallback(true) {
//        override fun handleOnBackPressed() {
//            // 뒤로가기 버튼 이벤트 처리
//            val currentFragment = supportFragmentManager.fragments[0]
//            if (currentFragment is WebViewFragment) {
//                if (currentFragment.canGoBack()) {
//                    currentFragment.goBack()
//                } else {
//                    finish()
//                }
//            } else {
//                finish()
//            }
//        }
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button1.setOnClickListener {
            // supportFragmentManager : activity 내부에서 fragment 관리해주는 역할
            // transaction : 작업의 단위
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, WebViewFragment())
                commit()
            }
        }

        binding.button2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, BFragment())
                commit()
            }
        }

//        this.onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.fragments[0]
        if (currentFragment is WebViewFragment) {
            if (currentFragment.canGoBack()) {
                currentFragment.goBack()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }
}