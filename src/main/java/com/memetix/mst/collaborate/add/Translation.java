package com.memetix.mst.collaborate.add;

import com.memetix.mst.language.Language;

/**
 * Translation 
 * 
 * Encapsulates a Translation that may be added to the Translation Memory using the
 * AddTranslation service, part of the Collaborative Translations API
 * 
 * @author Jonathan Griggs <jonathan.griggs at gmail.com>
 * @since 0.4
 * @since 06/13/2011
 */
public class Translation {
	private String originalText;
	private String translatedText;
	private Language from;
	private Language to;
	private int rating = 1;
	private String contentType;
	private String category;
	private String user;
	private String uri;
	
	public String getOriginalText() {
		return originalText;
	}
	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}
	public String getTranslatedText() {
		return translatedText;
	}
	public void setTranslatedText(String translatedText) {
		this.translatedText = translatedText;
	}
	public Language getFrom() {
		return from;
	}
	public void setFrom(Language from) {
		this.from = from;
	}
	public Language getTo() {
		return to;
	}
	public void setTo(Language to) {
		this.to = to;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
}
