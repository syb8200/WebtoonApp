package com.practice.fc_2_chapter1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.practice.fc_2_chapter1.databinding.FragmentWebviewBinding

class WebViewFragment : Fragment() {

    private lateinit var binding : FragmentWebviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.apply {
            webViewClient = WebToonWebViewClient(binding.progressBar)
            settings.javaScriptEnabled = true
            loadUrl("https://comic.naver.com/webtoon/detail?titleId=183559&no=566")
        }
    }

    fun canGoBack() : Boolean {
        return binding.webView.canGoBack()
    }

    fun goBack() {
        binding.webView.goBack()
    }
}