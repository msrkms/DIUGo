package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ViewWebPages extends AppCompatActivity {
private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_web_pages);
        String url= getIntent().getStringExtra("News");
        webView=findViewById(R.id.webviewid);
         WebSettings webSettings= webView.getSettings();
         webSettings.setJavaScriptEnabled(true);
         webView.setWebViewClient(new WebViewClient());
         webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}
