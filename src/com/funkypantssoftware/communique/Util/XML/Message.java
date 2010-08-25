package com.funkypantssoftware.communique.Util.XML;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Comparable<Message>, Serializable{
	
	private static final long serialVersionUID = 4468145719644484523L;
	 static SimpleDateFormat FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
	
	private URL link;
	private String title;
	private URL thumbnail;
	private String description;
	private String item;
	private URL audioLink;
	private Date pubDate;
	
	public URL getLink() {
		return link;
	}

	public void setLink(final String link) throws MalformedURLException {
		this.link = new URL(link);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public URL getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(final String thumbnail) throws MalformedURLException {
		this.thumbnail = new URL(thumbnail);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getItem() {
		return item;
	}

	public void setItem(final String item) {
		this.item = item;
	}

	public URL getAudioLink() {
		return audioLink;
	}

	public void setAudioLink(final String audioLink) throws MalformedURLException {
		this.audioLink = new URL(audioLink);
	}

	public String getDate() {
		SimpleDateFormat test = new SimpleDateFormat("MMMMM dd, yyyy");
        return test.format(this.pubDate);
    }

    public void setDate(String date) throws ParseException {
        // pad the date if necessary
        while (!date.endsWith("00")){
            date += "0";
        }    
            this.pubDate = FORMATTER.parse(date.trim());
        }
	
	@Override
	public int compareTo(final Message another) {
		if (another == null) {
			return 1;
		}
		return 0;
	}

	public Message copy() {		
		Message newMessage = new Message();
		newMessage.audioLink = this.audioLink;
		newMessage.description = this.description;
		newMessage.item = this.item;
		newMessage.link = this.link;
		newMessage.pubDate = this.pubDate;
		newMessage.thumbnail = this.thumbnail;
		newMessage.title = this.title;
		
		return newMessage;
	}

}