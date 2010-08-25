package com.funkypantssoftware.communique.Util.XML;

import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;

public interface IFeedParser {
	List<Message> parse() throws IOException, SAXException;
}
