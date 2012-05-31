package com.weiss.flickr;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.test.TestInterface;

public class FlickrTest {
	public static void main(String[] args) throws IOException, SAXException, FlickrException {
		String apiKey = "5d02fe9f3857bd77c558db64ac41da75";
		Flickr f = new Flickr(apiKey);
		f.setSharedSecret("d61dcd09512729c6");
		TestInterface testInterface = f.getTestInterface();
		Collection<?> results = testInterface.echo(Collections.EMPTY_LIST);
		System.out.println(results);
	}
}
