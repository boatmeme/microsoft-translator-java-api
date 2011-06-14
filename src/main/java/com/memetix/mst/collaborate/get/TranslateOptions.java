package com.memetix.mst.collaborate.get;

/**
 * TranslateOptions
 * 
 * Encapsulates the Translation Options that may be sent with a request to the GetTranslations service, 
 * part of the Collaborative Translations API
 * 
 * @author Jonathan Griggs <jonathan.griggs at gmail.com>
 * @since 0.4
 * @since 06/13/2011
 */
public class TranslateOptions {
	private String category;
	private String contentType;
	private String uri;
	private String user;
	private String state;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
