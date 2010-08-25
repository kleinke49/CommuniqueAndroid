package com.funkypantssoftware.communique.Util.XML;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseFeedParser implements IFeedParser{
	 
    final URL feedUrl;

    protected BaseFeedParser(String feedUrl) throws MalformedURLException{
        this.feedUrl = new URL(feedUrl);
    }

    protected InputStream getInputStream() throws IOException {
    	return feedUrl.openConnection().getInputStream();
      
    }
}
