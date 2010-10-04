package com.funkypantssoftware.communique.Util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class Util {
	static final HashMap<String, SoftReference<Drawable>> imageCache = new HashMap<String, SoftReference<Drawable>>();
	
	public static Drawable fetchDrawable(final Context context, final String URL, String key) throws IOException {
		Drawable drawable = null;
		
		drawable = Drawable.createFromStream(GetImage(URL), "src");
		imageCache.put(key, new SoftReference<Drawable>(drawable));
		
		return drawable;
	}
	
	public static void fetchDrawableOnThread(final Context context, final String URL, final ImageView imageView, final String key) {
		
		final SoftReference<Drawable> ic = imageCache.get(key);
		if (ic != null) {
			final Drawable image = ic.get();
			if (image != null) {
				imageView.setImageDrawable(image);
			} else {
				imageCache.remove(URL);
			}
		}

		final Handler handler = new Handler() {
			@Override
			public void handleMessage(final Message message) {
				imageView.setImageDrawable((Drawable) message.obj);
			}
		};

		final Thread thread = new Thread() {
			@Override
			public void run() {
				Drawable drawable;
				
				try {
					drawable = fetchDrawable(context, URL, key);
				
					final Message message = handler.obtainMessage(1, drawable);
					handler.sendMessage(message);
				} catch (IOException e) {
					//TODO: Error handle.
					e.printStackTrace();
				}
			}
		};
		thread.start();
	}
	
	public static InputStream GetImage(final String URL) throws IOException {
		
		URL formattedURL = new URL(URL);
		
		HttpURLConnection request = (HttpURLConnection) formattedURL.openConnection();
		request.setRequestMethod("GET");

		request.connect();
		
		return request.getInputStream();
	}
	
	public static boolean IsEmpty(final String check) {
        boolean ret = false;

        if (check.equals(null) || check.equals("") || check.equals("null")) {
                ret = true;
        }
        return ret;
}

	public static String GetVimeoStreamURL(final String url) {
		String ret = "none";
		
		try {
			URL formattedURL = new URL(url);
			
			HttpURLConnection request = (HttpURLConnection) formattedURL.openConnection();
			request.addRequestProperty("Referer", "http://www.vimeo.com/m/");
			request.setRequestMethod("GET");
			
			request.connect();
			
			request.getInputStream();
			
			ret = request.getURL().toString();
		}
		catch (Exception e) {
			//TODO: HANDLE EXCEPTIONS
		}
		return ret;
	}
}
