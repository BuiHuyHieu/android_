package com.example.read_article_express_rss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Process extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        WebView webView = findViewById(R.id.WebView);

        webView.loadUrl(getIntent().getStringExtra("Link"));
        webView.setWebViewClient(new WebViewClient());
    }
}
