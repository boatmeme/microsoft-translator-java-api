/*
 * #%L
 * microsoft-translator-api
 * %%
 * Copyright (C) 2011 Jonathan Griggs
 * %%
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
 * #L%
 */
package com.memetix.mst;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * MicrosoftAPI
 * 
 * Makes the generic Microsoft Translator API calls. Different service classes then
 * extend this to make the specific service calls.
 * 
 * @author Jonathan Griggs
 */
public abstract class MicrosoftAPI {
    //Encoding type
    protected static final String ENCODING = "UTF-8";
    
    protected static String apiKey;
    //private static String referrer;
    
    /**
     * Sets the API key.
     * @param pKey The API key.
     */
    public static void setKey(final String pKey) {
    	apiKey = pKey;
    }
    
    /**
     * Forms an HTTP request, sends it using GET method and returns the result of the request as a JSONObject.
     * 
     * @param url The URL to query for a JSONObject.
     * @return The translated String.
     * @throws Exception on error.
     */
    protected static String retrieveJSON(final URL url) throws Exception {
    	try {
    		final HttpURLConnection uc = (HttpURLConnection) url.openConnection();
    		//uc.setRequestProperty("referer", referrer);
    		uc.setRequestMethod("GET");
    		uc.setDoOutput(true);
    		
    		try {
    			final String result = inputStreamToString(uc.getInputStream());
    			return massageResponse(result.trim());
    			//return new JSONObject(result);
    		} finally { 
    			uc.getInputStream().close();
    			if (uc.getErrorStream() != null) {
    				uc.getErrorStream().close();
    			}
    		}
    	} catch (Exception ex) {
    		throw new Exception("[microsoft-translator-api] Error retrieving translation.", ex);
    	}
    }
    
    /**
     * Reads an InputStream and returns its contents as a String.
     * Also effects rate control.
     * @param inputStream The InputStream to read from.
     * @return The contents of the InputStream as a String.
     * @throws Exception on error.
     */
    private static String inputStreamToString(final InputStream inputStream) throws Exception {
    	final StringBuilder outputBuilder = new StringBuilder();
    	
    	try {
    		String string;
    		if (inputStream != null) {
    			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, ENCODING));
    			while (null != (string = reader.readLine())) {
    				outputBuilder.append(string).append('\n');
    			}
    		}
    	} catch (Exception ex) {
    		throw new Exception("[microsoft-translator-api] Error reading translation stream.", ex);
    	}
    	
    	return outputBuilder.toString();
    }
    
    /*
     * The Microsoft Translator API returns some questionable, non-standard responses (for both the AJAX and HTTP
     * Services). Broke this into a different method in case the format changes in the future.
     * 
     * Currently, just strip the first two and last characters, there is Unicode non-breaking space and quotes
     * Truly odd.
     */
    private static String massageResponse(String msTranslateResponse) {
        if(msTranslateResponse==null)
            return msTranslateResponse;
        
        return msTranslateResponse.substring(2,msTranslateResponse.length()-1);
    }
    
}
