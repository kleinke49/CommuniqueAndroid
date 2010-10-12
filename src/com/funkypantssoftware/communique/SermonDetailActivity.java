package com.funkypantssoftware.communique;

import com.funkypantssoftware.communique.Util.Util;
import com.funkypantssoftware.communique.Util.XML.Message;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SermonDetailActivity extends Activity {
	
	private Message message;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		message = (Message) this.getIntent().getSerializableExtra("message");
		
		this.setContentView(R.layout.sermon_detail_view);
		this.loadInterface();
	}
	
	private void loadInterface() {
		
		ImageView image;
		TextView description;
		ImageButton video;
		ImageButton audio;
		
		image = (ImageView)this.findViewById(R.id.image);
		description = (TextView) this.findViewById(R.id.description);
		video = (ImageButton) this.findViewById(R.id.playVideo);
		audio = (ImageButton) this.findViewById(R.id.playAudio);
		
		description.setText(message.getDescription());
		com.funkypantssoftware.communique.Util.Util.fetchDrawableOnThread(this, message.getThumbnail().toString(), image, message.getThumbnail().toString());
		
		if (message.getLink() == null) {
			video.setVisibility(View.GONE);
		}
		else {
			video.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					String url = "";
					
					if (SermonDetailActivity.this.getString(R.string.use_vimeo).equals("1")) {
						url = Util.GetVimeoStreamURL(message.getLink().toString());
					}
					else {	
						url = message.getLink().toString();
					}
					Intent tostart = new Intent(Intent.ACTION_VIEW);
					tostart.setDataAndType(Uri.parse(url), "video/*");
					startActivity(tostart);
				}
			});
		}
		
		if (message.getAudioLink() == null) {
			audio.setVisibility(View.GONE);
		} 
		else {
			audio.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Intent tostart = new Intent(Intent.ACTION_VIEW);
					tostart.setDataAndType(Uri.parse(message.getAudioLink().toString()), "audio/*");
					tostart.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
					startActivity(tostart);
				}
			});
		}
	}
}
