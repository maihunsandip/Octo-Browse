package com.maihunsandip.octobrowse;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    WebView browser;
    EditText urlText;
    Button go, forward, backward, reload, clear;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        progressbar = (ProgressBar) findViewById(R.id.progressbar);

        browser = (WebView) findViewById(R.id.wv_browser);
        urlText = (EditText) findViewById(R.id.et_url);
        go = (Button) findViewById(R.id.btn_go);
        forward = (Button) findViewById(R.id.btn_forward);
        backward = (Button) findViewById(R.id.btn_backword);
        reload = (Button) findViewById(R.id.btn_reload);
        clear = (Button) findViewById(R.id.btn_clear);

        browser.setWebViewClient(new octoViewClient() );

//        ============== Loader =====================
//        browser.setWebChromeClient(new WebChromeClient(){
//
//            public void OnProgressChanged(WebView v, int newProgress) {
//                progressbar.setProgress(newProgress);
//
//                if(newProgress == 100)
//                    progressbar.setVisibility(View.GONE);
//                else
//                    progressbar.setVisibility(View.VISIBLE);
//            }
//        });
//        ============== Loader =====================

        WebSettings websettings = browser.getSettings();
        websettings.getJavaScriptEnabled();

        browser.loadUrl("https://www.google.com");

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editTextValue = urlText.getText().toString();

                if(!editTextValue.startsWith("https://"))
                    editTextValue = "https://" + editTextValue;

                String url = editTextValue;
                browser.loadUrl(url);

                //Hide keyboard after GO
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(urlText.getWindowToken(), 0);
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(browser.canGoForward())
                    browser.goForward();
            }
        });

        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(browser.canGoBack())
                    browser.goBack();
            }
        });

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browser.reload();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browser.clearHistory();
            }
        });

    }
}