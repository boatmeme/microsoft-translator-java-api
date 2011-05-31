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
    /**
	 * Detects the language of a supplied String.
	 * 
	 * @param text The String to detect the language of.
	 * @return A DetectResult object containing the language, confidence and reliability.
	 * @throws Exception on error.
	 */
	public static String execute(final String text) throws Exception {
		final URL url = new URL(SERVICE_URL 
                        +URLEncoder.encode(text, ENCODING)
                        +"&appId="+URLEncoder.encode(apiKey,ENCODING));
		final String response = retrieveString(url);
                return response;
	}

}
