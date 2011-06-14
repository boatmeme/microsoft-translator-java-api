package com.memetix.mst.collaborate.get;

import com.memetix.mst.language.Language;

/**
 * GetTranslationsResponse 
 * 
 * Encapsulates a single response from the GetTranslations service, 
 * part of the Collaborative Translations API
 * 
 * The response contains state and correlation data for the request, 
 * as well as an array of Translations that matched the request
 * 
 * @author Jonathan Griggs <jonathan.griggs at gmail.com>
 * @since 0.4
 * @since 06/13/2011
 */
public class GetTranslationsResponse {
	private TranslationMatch[] translations;
	private Language from;
	private String state;
	
	public TranslationMatch[] getTranslations() {
		return translations;
	}
	public void setTranslations(TranslationMatch[] translations) {
		this.translations = translations;
	}
	public Language getFrom() {
		return from;
	}
	public void setFrom(Language from) {
		this.from = from;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
