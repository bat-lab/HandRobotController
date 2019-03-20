package de.hsm.handrobotcontroller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


import im.delight.android.webview.AdvancedWebView;

public class MainActivity extends AppCompatActivity implements AdvancedWebView.Listener {
    private AdvancedWebView mWebView;

    private BtService btService = null;
    private HandRoboterInterface handRoboterInterface = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        mWebView.setListener(this, this);
        mWebView.setGeolocationEnabled(false);
        mWebView.setMixedContentAllowed(true);
        mWebView.setCookiesEnabled(true);
        mWebView.setThirdPartyCookiesEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                Toast.makeText(MainActivity.this, "Finished loading", Toast.LENGTH_SHORT).show();
            }

        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
            }

        });

        this.btService = new BtService(this);
        this.handRoboterInterface  = new HandRoboterInterface(this.btService);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(handRoboterInterface, "HRI");
        mWebView.addHttpHeader("X-Requested-With", "");
        mWebView.loadUrl("file:///android_asset/app/index.html");
    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) {
            return;
        }
        // ...
        super.onBackPressed();
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
    }

    @Override
    public void onPageFinished(String url) {
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
    }

    @Override
    public void onExternalPageRequest(String url) {
    }
}
