package com.practice.fc_2_chapter1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.practice.fc_2_chapter1.databinding.FragmentWebviewBinding

class WebViewFragment(private val position : Int, private val webViewUrl : String) : Fragment() {

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

        // commit() : 동기처리 / apply() : 비동기처리
        binding.webView.apply {
            webViewClient = WebToonWebViewClient(binding.progressBar) { url ->
                activity?.getSharedPreferences("WEB_HISTORY", Context.MODE_PRIVATE)?.edit {
                    putString("tab$position", url)
                }
            }
            settings.javaScriptEnabled = true
            loadUrl(webViewUrl)
        }

        binding.backToLastButton.setOnClickListener {
            val sp = activity?.getSharedPreferences("WEB_HISTORY", Context.MODE_PRIVATE)
            val url = sp?.getString("tab$position", "")
            if (url.isNullOrEmpty()) {
                Toast.makeText(context, "마지막 저장 시점이 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.webView.loadUrl(url)
            }
        }
    }

    fun canGoBack() : Boolean {
        return binding.webView.canGoBack()
    }

    fun goBack() {
        binding.webView.goBack()
    }
}