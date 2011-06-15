/*
 * microsoft-translator-java-api
 * 
 * Copyright 2011 Jonathan Griggs <jonathan.griggs at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.memetix.mst.collaborate.get;

/**
 * TranslationMatch 
 * 
 * Encapsulates a Translation result from the GetTranslations service, 
 * part of the Collaborative Translations API
 * 
 * @author Jonathan Griggs <jonathan.griggs at gmail.com>
 * @since 0.4
 * @since 06/13/2011
 */
public class TranslationMatch {
	private String error;
	private int matchDegree;
	private String matchedOriginalText;
	private int rating;
	private int count;
	private String translatedText;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getMatchDegree() {
		return matchDegree;
	}
	public void setMatchDegree(int matchDegree) {
		this.matchDegree = matchDegree;
	}
	public String getMatchedOriginalText() {
		return matchedOriginalText;
	}
	public void setMatchedOriginalText(String matchedOriginalText) {
		this.matchedOriginalText = matchedOriginalText;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getTranslatedText() {
		return translatedText;
	}
	public void setTranslatedText(String translatedText) {
		this.translatedText = translatedText;
	}
}
