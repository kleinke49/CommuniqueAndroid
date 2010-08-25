package com.funkypantssoftware.communique.Util.XML;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;

import com.funkypantssoftware.communique.R;

import android.content.Context;
import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;

public class FeedParser extends BaseFeedParser{

	Context context = null;
	String mLink;
	String mTitle;
	String mThumbnail;
	String mDescription;
	String mItem;
	String mAudioLink;
	String mPubDate;
	
	public FeedParser(Context context, String feedUrl) throws MalformedURLException {
		super(feedUrl);
		this.context = context;
		
		//Load up our fields names from the string.xml
		//This allows people to map XML fields without having to change code.
		mLink = this.context.getString(R.string.xml_link);
		mTitle = this.context.getString(R.string.xml_title);
		mThumbnail = this.context.getString(R.string.xml_thumbnail);
		mDescription = this.context.getString(R.string.xml_description);
		mItem = this.context.getString(R.string.xml_item);
		mAudioLink = this.context.getString(R.string.xml_audioLink);
		mPubDate = this.context.getString(R.string.xml_pubDate);
	}

	@Override
	public List<Message> parse() throws IOException, SAXException {
		final Message currentMessage = new Message();
        RootElement root = new RootElement("rss");
        final List<Message> messages = new ArrayList<Message>();
        Element channel = root.getChild("channel");
        Element item = channel.getChild(mItem);
        
        item.setEndElementListener(new EndElementListener(){
            public void end() {
                messages.add(currentMessage.copy());
            }
        });
        item.getChild(mTitle).setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
                currentMessage.setTitle(body);
            }
        });
        item.getChild(mLink).setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
                try {
					currentMessage.setLink(body);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        item.getChild(mDescription).setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
                currentMessage.setDescription(body);
            }
        });
        item.getChild(mPubDate).setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
                try {
					currentMessage.setDate(body);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        item.getChild(mThumbnail).setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
                try {
					currentMessage.setThumbnail(body);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        item.getChild(mAudioLink).setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
                try {
					currentMessage.setAudioLink(body);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        try {
        	Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8, root.getContentHandler());
        } catch (Exception e) {
        	System.out.println("Test");
        }
        return messages;
    }
}