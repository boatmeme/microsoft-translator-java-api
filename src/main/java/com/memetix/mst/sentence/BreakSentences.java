/*
 * microsoft-translator-java-api
 * 
 * Copyright 2012 Jonathan Griggs <jonathan.griggs at gmail.com>.
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
package com.memetix.mst.sentence;

import java.net.URL;
import java.net.URLEncoder;

import com.memetix.mst.MicrosoftTranslatorAPI;
import com.memetix.mst.language.Language;

/**
 * BreakSentences 
 * 
 * Provides an interface to the Microsoft Translator BreakSentences service 
 * 
 * This service is basically a utility for determining how Microsoft Translator is
 * interpreting sentence breaks within a given string of text
 * 
 * Uses the AJAX Interface V2 - see: http://msdn.microsoft.com/en-us/library/ff512395.aspx
 * 
 * @author Jonathan Griggs <jonathan.griggs at gmail.com>
 */
public final class BreakSentences extends MicrosoftTranslatorAPI {

	private static final String SERVICE_URL = "http://api.microsofttranslator.com/V2/Ajax.svc/BreakSentences?";

	// prevent instantiation
	private BreakSentences(){};
	/**
	 * Reports the number of sentences detected and the length of those sentences
	 * 
	 * @param text The String to break into sentences
	 * @param fromLang The Language of origin
	 * @return an array of integers representing the size of each detected sentence
	 * @throws Exception on error.
	 */
	public static Integer[] execute(final String text, final Language fromLang) throws Exception {
        //Run the basic service validations first
        validateServiceState(text,fromLang); 
		final URL url = new URL(SERVICE_URL 
                        +(apiKey != null ? PARAM_APP_ID + URLEncoder.encode(apiKey,ENCODING) : "") 
                        +PARAM_SENTENCES_LANGUAGE+URLEncoder.encode(fromLang.toString(), ENCODING)
                        +PARAM_TEXT_SINGLE+URLEncoder.encode(text, ENCODING));
                     
		final Integer[] response = retrieveIntArray(url);
		return response;
	}
	
	private static void validateServiceState(final String text, final Language fromLang) throws Exception {
		final int byteLength = text.getBytes(ENCODING).length;
        if(byteLength>10240) {
            throw new RuntimeException("TEXT_TOO_LARGE - Microsoft Translator (BreakSentences) can handle up to 10,240 bytes per request");
        }
        if(Language.AUTO_DETECT.equals(fromLang)) {
        	throw new RuntimeException("BreakSentences does not support AUTO_DETECT Langauge. Please specify the origin language");
        }
        validateServiceState();
    }
}
