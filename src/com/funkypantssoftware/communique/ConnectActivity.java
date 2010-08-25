package com.funkypantssoftware.communique;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ConnectActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.connect_view);
		
		TextView text = (TextView)this.findViewById(R.id.description);
		text.setText(this.getString(R.string.connect_description));
		text.setMovementMethod(new ScrollingMovementMethod());
		
		ImageButton facebook = (ImageButton) this.findViewById(R.id.facebook);
		facebook.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent tostart = new Intent(Intent.ACTION_VIEW);
				tostart.setDataAndType(Uri.parse(ConnectActivity.this.getString(R.string.connect_facebook_url)), null);
				startActivity(tostart);
			}
		});
		
		ImageButton twitter = (ImageButton) this.findViewById(R.id.twitter);
		twitter.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent tostart = new Intent(Intent.ACTION_VIEW);
				tostart.setDataAndType(Uri.parse(ConnectActivity.this.getString(R.string.connect_twitter_url)), null);
				startActivity(tostart);
			}
		});
		
		ImageButton email = (ImageButton) this.findViewById(R.id.email);
		email.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent tostart = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", ConnectActivity.this.getString(R.string.connect_email), null));
				startActivity(tostart);
			}
		});
		
		ImageButton phone = (ImageButton) this.findViewById(R.id.phone);
		phone.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				final Intent tostart = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + ConnectActivity.this.getString(R.string.connect_phone)));
				startActivity(tostart);
			}
		});
		
		ImageButton website = (ImageButton) this.findViewById(R.id.website);
		website.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent tostart = new Intent(Intent.ACTION_VIEW);
				tostart.setDataAndType(Uri.parse(ConnectActivity.this.getString(R.string.connect_website)), null);
				startActivity(tostart);
			}
		});
		
		ImageButton map = (ImageButton) this.findViewById(R.id.map);
		map.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				final Intent tostart = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + ConnectActivity.this.getString(R.string.connect_address)));
				startActivity(tostart);
			}
		});
	}
}
