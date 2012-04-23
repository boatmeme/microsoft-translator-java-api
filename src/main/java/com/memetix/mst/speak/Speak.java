/*
 * microsoft-translator-java-api
 * 
 * Copyright 2012 Jonathan Griggs.
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
package com.memetix.mst.speak;

import com.memetix.mst.MicrosoftTranslatorAPI;
import com.memetix.mst.language.SpokenDialect;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Speak 
 * 
 * Provides an interface to the Microsoft Translator Speak service method
 * 
 * Returns a string which is a URL to a wave stream of the passed-in text being spoken in the desired language.
 * 
 * Uses the AJAX Interface V2 - see: http://msdn.microsoft.com/en-us/library/ff512405.aspx
 * 
 * @author Jonathan Griggs <jonathan.griggs at gmail.com>
 */
public final class Speak extends MicrosoftTranslatorAPI {
    private static final String SERVICE_URL = "http://api.microsofttranslator.com/V2/Ajax.svc/Speak?";

     //prevent instantiation
     private Speak() {};
     /**
	 * Detects the language of a supplied String.
	 * 
	 * @param text The String to generate a WAV for
     * @param to The language code to translate to
	 * @return A String containing the URL to a WAV of the spoken text
	 * @throws Exception on error.
	 */
	public static String execute(final String text, final SpokenDialect language) throws Exception {
                //Run the basic service validations first
                validateServiceState(text);             
		final URL url = new URL(SERVICE_URL 
                        +(apiKey != null ? PARAM_APP_ID + URLEncoder.encode(apiKey,ENCODING) : "") 
                        +PARAM_SPOKEN_LANGUAGE+URLEncoder.encode(language.toString(),ENCODING)
                        +PARAM_TEXT_SINGLE+URLEncoder.encode(text, ENCODING));
		final String response = retrieveString(url);
                return response;
	}
        
        private static void validateServiceState(final String text) throws Exception {
        	final int byteLength = text.getBytes(ENCODING).length;
            if(byteLength>2000) {
                throw new RuntimeException("TEXT_TOO_LARGE - Microsoft Translator (Speak) can handle up to 2000 bytes per request");
            }
            validateServiceState();
        }
}

