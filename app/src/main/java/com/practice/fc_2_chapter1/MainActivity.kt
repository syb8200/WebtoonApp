package com.practice.fc_2_chapter1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.google.android.material.tabs.TabLayoutMediator
import com.practice.fc_2_chapter1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
                tab.text = "position $position"

                // 조금 더 커스텀을 하고 싶은 경우 (위의 코드 대체)
//                val textView = TextView(this@MainActivity)
//                textView.text = "position $position"
//                textView.gravity = Gravity.CENTER
//                tab.customView = textView
            }
        }.attach()

//        this.onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem]
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
}