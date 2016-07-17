package com.johncbell.atrixtrucking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by johncbell on 5/29/2016.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class aboutUs extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_website);
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());

        String url = "http://www.atrixtrucking.com/contact-us/";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}