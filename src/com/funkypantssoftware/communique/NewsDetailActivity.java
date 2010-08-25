package com.funkypantssoftware.communique;

import com.funkypantssoftware.communique.Util.XML.Message;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class NewsDetailActivity extends Activity {
	Message message;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		message = (Message) this.getIntent().getSerializableExtra("message");
		
		WebView webview = new WebView(this);
		setContentView(webview);
		
		webview.loadData(message.getDescription(), "text/html", "utf-8");
	}
}
