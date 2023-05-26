package com.practice.fc_2_chapter1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.google.android.material.tabs.TabLayoutMediator
import com.practice.fc_2_chapter1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnTabLayoutNameChanged {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sp = getSharedPreferences(WebViewFragment.SHARED_PREFERENCE, MODE_PRIVATE)
        val tab0 = sp?.getString("tab0_name", "월요웹툰")
        val tab1 = sp?.getString("tab1_name", "화요웹툰")
        val tab2 = sp?.getString("tab2_name", "수요웹툰")

        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
                tab.text = when(position) {
                    0 -> tab0
                    1 -> tab1
                    else -> tab2
                }

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

    override fun nameChanged(position: Int, name: String) {
        val tab = binding.tabLayout.getTabAt(position)
        tab?.text = name
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