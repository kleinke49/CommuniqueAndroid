package com.funkypantssoftware.communique;

import com.funkypantssoftware.communique.Util.MessageAdapter;
import com.funkypantssoftware.communique.Util.XML.Message;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class NewsActivity extends ListActivity {
	private ListView mList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.news_view);
		this.setListAdapter(new MessageAdapter(this, MainActivity.newsFeed));
		
		mList = (ListView) this.findViewById(android.R.id.list);
		
		NewsActivity.this.mList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				try {
					final Intent details = new Intent(NewsActivity.this, NewsDetailActivity.class);
					final Message message = (Message) view.getTag();
					details.putExtra("message", message);
					NewsActivity.this.startActivity(details);
					
				} catch (final Exception e) {
					//TODO: Handle error
					System.out.println(e.toString());
				}
			}
		});
	}

}
