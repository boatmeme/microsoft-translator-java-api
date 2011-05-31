/*
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
package com.memetix.mst.detect;

import com.memetix.mst.MicrosoftAPI;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Detect 
 * 
 * Provides an interface to the Microsoft Translator Detect service method
 * 
 * @author Jonathan Griggs <jonathan.griggs at gmail.com>
 */
public final class Detect extends MicrosoftAPI {
    private static final String SERVICE_URL = "http://api.microsofttranslator.com/V2/Ajax.svc/Detect?text=";
    private static final String ARRAY_SERVICE_URL = "http://api.microsofttranslator.com/V2/Ajax.svc/DetectArray?texts=";
        /**
	 * Detects the language of a supplied String.
	 * 
	 * @param text The String to detect the language of.
	 * @return A String containing the language
	 * @throws Exception on error.
	 */
	public static String execute(final String text) throws Exception {
                //Run the basic service validations first
                validateServiceState(text); 
		final URL url = new URL(SERVICE_URL 
                        +URLEncoder.encode(text, ENCODING)
                        +"&appId="+URLEncoder.encode(apiKey,ENCODING));
		final String response = retrieveString(url);
                return response;
	}
        
        /**
	 * Detects the language of all supplied Strings in arrary.
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
                        +URLEncoder.encode(textArr, ENCODING)
                        +"&appId="+URLEncoder.encode(apiKey,ENCODING));
		final String[] response = retrieveStringArr(url);
                return response;
	}
        
        private static void validateServiceState(final String text) throws Exception {
            if(text.length()>10240) {
                throw new RuntimeException("TEXT_TOO_LARGE - Microsoft Translator (Detect) can handle up to 10240k characters per request");
            }
            validateServiceState();
        }
        
        private static void validateServiceState(final String[] texts) throws Exception {
            for(String text : texts) {
                if(text.length()>10240) {
                    throw new RuntimeException("TEXT_TOO_LARGE - Microsoft Translator (Detect) can handle up to 10240k characters per request");
                }
            }
            validateServiceState();
        }

}
