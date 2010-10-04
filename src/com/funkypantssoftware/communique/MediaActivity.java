package com.funkypantssoftware.communique;

import com.funkypantssoftware.communique.Util.MessageAdapter;
import com.funkypantssoftware.communique.Util.Util;
import com.funkypantssoftware.communique.Util.XML.Message;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MediaActivity extends ListActivity {

	private ListView mList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.media_view);
		this.setListAdapter(new MessageAdapter(this, MainActivity.mediaFeed));
		
		mList = (ListView) this.findViewById(android.R.id.list);
		
		this.mList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				try {
					String url = "";
					final Message message = (Message) view.getTag();
					if (MediaActivity.this.getString(R.string.use_vimeo).equals("1")) {
						url = Util.GetVimeoStreamURL(message.getLink().toString());
					}
					else {	
						url = message.getLink().toString();
					}
					Intent tostart = new Intent(Intent.ACTION_VIEW);
					
					tostart.setDataAndType(Uri.parse(url), "video/*");
					startActivity(tostart);
				} catch (final Exception e) {
					System.out.println(e.toString());
				}
			}
		});
	}
}
