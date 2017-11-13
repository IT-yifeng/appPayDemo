package com.yifeng.loadurltoalipay;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btn_startaliapp;
	private WebView webview;
	private String  TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_startaliapp = (Button) findViewById(R.id.btn_startaliapp);
		webview = (WebView) findViewById(R.id.webview);
		WebSettings webSettings = webview.getSettings();
		webSettings.setJavaScriptEnabled(true);
		// ���ÿ��Է����ļ�
		webSettings.setAllowFileAccess(true);
		// ����֧������
		webSettings.setBuiltInZoomControls(true);
		webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		// webSettings.setDatabaseEnabled(true);
		
		// ʹ��localStorage������
		webSettings.setDomStorageEnabled(true);

		webSettings.setGeolocationEnabled(true);
		
		webview.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
			}
			
			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
			}
			
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.e(TAG, "���ʵ�url��ַ��" + url);
				if (parseScheme(url)) {
					try {
						Uri uri = Uri.parse(url);
						Intent intent;
						intent = Intent.parseUri(url,
								Intent.URI_INTENT_SCHEME);
						intent.addCategory("android.intent.category.BROWSABLE");
						intent.setComponent(null);
						// intent.setSelector(null);
						startActivity(intent);

					} catch (Exception e) {

					}
				} else {
					view.loadUrl(url);
				}

				return true;

			}
			
		});
		
		
		btn_startaliapp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ���ﴫ��Web APIԤ�µ��ӿڷ��صĲ���qr_code��URL
				webview.loadUrl("https://qr.alipay.com/bax05540lsilvauvzwvs20d8");
				webview.setVisibility(View.GONE);
			}
		});
		
	}
	
	
	public boolean parseScheme(String url) {
		
		if (url.contains("platformapi/startapp")) {

			return true;
		} else {

			return false;
		}
	}

	
}
