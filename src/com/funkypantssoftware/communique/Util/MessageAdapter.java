package com.funkypantssoftware.communique.Util;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.funkypantssoftware.communique.R;
import com.funkypantssoftware.communique.Util.XML.Message;

public class MessageAdapter extends BaseAdapter {

	private List<Message> items;
	private Context c;
	
	public MessageAdapter(Context context, List<Message> messages) {
		this.items = messages;
		this.c = context;
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RelativeLayout rowLayout = null;
		
		try {
			ResultHolder holder = new ResultHolder(this.c);
			
			Message m = items.get(position);
			
			if (m != null) {
				rowLayout = (RelativeLayout)LayoutInflater.from(c).inflate(R.layout.row, parent, false);
				
				//Put the Message object in the tag so we can
				//get it when they press the item and pass it to the 
				//sermon detail view.
				rowLayout.setTag(m);

				holder.Title = (TextView)rowLayout.findViewById(R.id.title);
				holder.Date = (TextView)rowLayout.findViewById(R.id.date);
				holder.Thumbnail = (ImageView)rowLayout.findViewById(R.id.icon);
				
				holder.Title.setText(m.getTitle());
				holder.Date.setText(m.getDate());
				
				if (m.getThumbnail() != null) {
					Util.fetchDrawableOnThread(this.c, m.getThumbnail().toString(), holder.Thumbnail, m.getThumbnail().toString() + "th");
				}
				else {
					holder.Thumbnail.setVisibility(View.GONE);
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());			
		}
		
		return rowLayout;
	}	
}
