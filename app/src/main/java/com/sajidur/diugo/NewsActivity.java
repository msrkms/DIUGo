package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class NewsActivity extends AppCompatActivity {

    WebView webViewNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        webViewNews=(WebView) findViewById(R.id.webViewNews);
        webViewNews.loadUrl("https://news.daffodilvarsity.edu.bd/");
    }
}
