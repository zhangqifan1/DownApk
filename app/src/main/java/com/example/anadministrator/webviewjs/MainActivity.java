package com.example.anadministrator.webviewjs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 输入访问网址,直接从www开始即可
     */
    private EditText mPathEt;
    /**
     * 加载
     */
    private Button mButLoad;
    private WebView mShowWb;
    private String path = "http://huixinguiyu.cn/test.html";
    /**
     * 调用js的方法
     */
    private Button mButCall;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        WebSettings settings = mShowWb.getSettings();
        settings.setJavaScriptEnabled(true);
        mShowWb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        mShowWb.addJavascriptInterface(new Object(){
            @JavascriptInterface
            public void showToast(String content){
                Toast.makeText(MainActivity.this,content,Toast.LENGTH_SHORT).show();
            }
        },"Android");
    }

    private void initView() {
        mPathEt = (EditText) findViewById(R.id.path_et);
        mButLoad = (Button) findViewById(R.id.butLoad);
        mButLoad.setOnClickListener(this);
        mShowWb = (WebView) findViewById(R.id.show_wb);
        mButCall = (Button) findViewById(R.id.butCall);
        mButCall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butLoad:
                mShowWb.loadUrl(path);
                break;
            //调用JS暴露的方法,WebView对象.loadUrl("javascript:js方法名(参数)");
            case R.id.butCall:
                mShowWb.loadUrl("javascript:changeInputValue('大大')");
                break;
        }
    }
}
