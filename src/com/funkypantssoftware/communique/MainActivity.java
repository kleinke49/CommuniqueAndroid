package com.funkypantssoftware.communique;

import java.util.List;

import com.funkypantssoftware.communique.Util.XML.Message;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
    
	public static List<Message> sermonFeed = null;
	public static List<Message> mediaFeed = null;
	public static List<Message> newsFeed = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
		setContentView(R.layout.main);
        
        Resources res = getResources();
        TabHost tabHost = getTabHost(); 
        TabHost.TabSpec spec;
        Intent intent;

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, SermonActivity.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("tab_0").setIndicator(this.getString(R.string.tab_0), res.getDrawable(R.drawable.ic_tab_sermon)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, MediaActivity.class);
        spec = tabHost.newTabSpec("tab_1").setIndicator(this.getString(R.string.tab_1), res.getDrawable(R.drawable.ic_tab_media)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, NewsActivity.class);
        spec = tabHost.newTabSpec("tab_2").setIndicator(this.getString(R.string.tab_2), res.getDrawable(R.drawable.ic_tab_news)).setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, ConnectActivity.class);
        spec = tabHost.newTabSpec("tab_3").setIndicator(this.getString(R.string.tab_3), res.getDrawable(R.drawable.ic_tab_connect)).setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
}