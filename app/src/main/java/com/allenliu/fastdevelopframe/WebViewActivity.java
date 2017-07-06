package com.allenliu.fastdevelopframe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView webView= (WebView) findViewById(R.id.webview);
        webView.loadData(getIntent().getStringExtra("data"),"text/html","UTF-8");
    }
}
