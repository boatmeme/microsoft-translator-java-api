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
package com.memetix.mst.detect;

import com.memetix.mst.MicrosoftTranslatorAPI;
import com.memetix.mst.language.Language;

import java.net.URL;
import java.net.URLEncoder;

/**
 * Detect 
 * 
 * Provides an interface to the Microsoft Translator Detect service method
 * 
 * Uses the AJAX Interface V2 - see: http://msdn.microsoft.com/en-us/library/ff512396.aspx 
 * 
 * @author Jonathan Griggs <jonathan.griggs at gmail.com>
 */
public final class Detect extends MicrosoftTranslatorAPI {
    private static final String SERVICE_URL = "http://api.microsofttranslator.com/V2/Ajax.svc/Detect?";
    private static final String ARRAY_SERVICE_URL = "http://api.microsofttranslator.com/V2/Ajax.svc/DetectArray?";
    
    // prevent instantiation
    private Detect(){};
    /**
	 * Detects the language of a supplied String.
	 * 
	 * @param text The String to detect the language of.
	 * @return A String containing the language
	 * @throws Exception on error.
	 */
	public static Language execute(final String text) throws Exception {
        //Run the basic service validations first
        validateServiceState(text); 
		final URL url = new URL(SERVICE_URL 
                        +(apiKey != null ? PARAM_APP_ID + URLEncoder.encode(apiKey,ENCODING) : "") 
                        +PARAM_TEXT_SINGLE+URLEncoder.encode(text, ENCODING));
                     
		final String response = retrieveString(url);
        return Language.fromString(response);
	}
        
     /**
	 * Detects the language of all supplied Strings in array.
	 * 
	 * @param text The Strings to detect the language of.
	 * @return A String array containing the detected languages
	 * @throws Exception on error.
	 */
	public static String[] execute(final String[] texts) throws Exception {
        //Run the basic service validations first
        validateServiceState(texts); 
        final String textArr = buildStringArrayParam(texts);
		final URL url = new URL(ARRAY_SERVICE_URL 
                        +(apiKey != null ? PARAM_APP_ID + URLEncoder.encode(apiKey,ENCODING) : "") 
                        +PARAM_TEXT_ARRAY+URLEncoder.encode(textArr, ENCODING));
		final String[] response = retrieveStringArr(url);
                return response;
	}
        
    private static void validateServiceState(final String text) throws Exception {
    	final int byteLength = text.getBytes(ENCODING).length;
        if(byteLength>10240) {
            throw new RuntimeException("TEXT_TOO_LARGE - Microsoft Translator (Detect) can handle up to 10,240 bytes per request");
        }
        validateServiceState();
    }
        
    private static void validateServiceState(final String[] texts) throws Exception {
    	int length = 0;
        for(String text : texts) {
            length+=text.getBytes(ENCODING).length;
        }
        if(length>10240) {
            throw new RuntimeException("TEXT_TOO_LARGE - Microsoft Translator (Detect) can handle up to 10,240 bytes per request");
        }
        validateServiceState();
    }

}
