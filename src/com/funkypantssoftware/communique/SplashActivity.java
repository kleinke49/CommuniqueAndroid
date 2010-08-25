package com.funkypantssoftware.communique;

import java.io.IOException;
import java.net.MalformedURLException;

import org.xml.sax.SAXException;

import com.funkypantssoftware.communique.Util.XML.FeedParser;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;


public class SplashActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.splash);
        
        this.setProgressBarIndeterminateVisibility(true);
        
        new LoadData().execute();
	}

	public class LoadData extends AsyncTask<Void, Void, Void> {
	
		@Override
		protected Void doInBackground(final Void... params) {
			try {
		    	//Parse all the feeds.
				FeedParser parser;
				parser = new FeedParser(SplashActivity.this, SplashActivity.this.getString(R.string.url_sermon_feed));
				MainActivity.sermonFeed = parser.parse();
				
				parser = new FeedParser(SplashActivity.this, SplashActivity.this.getString(R.string.url_media_feed));
				MainActivity.mediaFeed = parser.parse();
				
				parser = new FeedParser(SplashActivity.this, SplashActivity.this.getString(R.string.url_news_feed));
				MainActivity.newsFeed = parser.parse();
	
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	
		@Override
		protected void onPostExecute(final Void message) {
			SplashActivity.this.setProgressBarIndeterminateVisibility(false);
			Intent i = new Intent(SplashActivity.this, MainActivity.class);
			SplashActivity.this.startActivity(i);
			finish();
		}
	}
}

