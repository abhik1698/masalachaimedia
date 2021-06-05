package com.masalachaimedia;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    WebView webView;

    @SuppressLint({"SetJavaScriptEnabled", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.web);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap facIcon) {
                progressBar.setVisibility(View.VISIBLE);//showProgressBar();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //hide loading image
                findViewById(R.id.imageView).setVisibility(View.GONE);
                progressBar.setVisibility(View.INVISIBLE);
                //show webview
                findViewById(R.id.web).setVisibility(View.VISIBLE);
            }
        });
        webView.loadUrl("https://masalachaimedia.com/");
//        final SwipeRefreshLayout  swipeRefreshLayout = findViewById(R.id.swipeToRefresh);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                webView.reload();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack())
            webView.goBack();
        else {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.btn_radio)
                    .setTitle("Exit Masala Chai Media Application")
                    .setMessage("Are you sure?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.super.onDestroy();
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }
}
