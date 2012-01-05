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
package com.memetix.mst;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * MicrosoftAPI
 * 
 * Makes the generic Microsoft Translator API calls. Different service classes then
 * extend this to make the specific service calls.
 * 
 * Uses the AJAX Interface V2 - see: http://msdn.microsoft.com/en-us/library/ff512404.aspx
 * 
 * @author Jonathan Griggs
 */
public abstract class MicrosoftTranslatorAPI {
    //Encoding type
    protected static final String ENCODING = "UTF-8";
    
    protected static String apiKey;
    private static String referrer;
    
    protected static final String PARAM_APP_ID = "appId=",
                                  PARAM_TO_LANG = "&to=",
                                  PARAM_FROM_LANG = "&from=",
                                  PARAM_TEXT_SINGLE = "&text=",
                                  PARAM_TEXT_ARRAY = "&texts=",
                                  PARAM_SPOKEN_LANGUAGE = "&language=",
                                  PARAM_SENTENCES_LANGUAGE = "&language=",
                                  PARAM_LOCALE = "&locale=",
                                  PARAM_LANGUAGE_CODES = "&languageCodes=";
    
    /**
     * Sets the API key.
     * @param pKey The API key.
     */
    public static void setKey(final String pKey) {
    	apiKey = pKey;
    }
    
    /**
     * Sets the API key.
     * @param pKey The API key.
     */
    public static void setHttpReferrer(final String pReferrer) {
    	referrer = pReferrer;
    }
    
    /**
     * Forms an HTTP request, sends it using GET method and returns the result of the request as a String.
     * 
     * @param url The URL to query for a String response.
     * @return The translated String.
     * @throws Exception on error.
     */
    private static String retrieveResponse(final URL url) throws Exception {
        final HttpURLConnection uc = (HttpURLConnection) url.openConnection();
        if(referrer!=null)
            uc.setRequestProperty("referer", referrer);
        uc.setRequestProperty("Content-Type","text/plain; charset=" + ENCODING);
        uc.setRequestProperty("Accept-Charset",ENCODING);
        uc.setRequestMethod("GET");
        uc.setDoOutput(true);

        try {
                final int responseCode = uc.getResponseCode();
                final String result = inputStreamToString(uc.getInputStream());
                if(responseCode!=200) {
                    throw new Exception("Error from Microsoft Translator API: " + result);
                }
                return result;
        } finally { 
        	if(uc!=null) {
    			uc.disconnect();
    		}
        }
    }
    
    /**
     * Fetches the JSON response, parses the JSON Response, returns the result of the request as a String.
     * 
     * @param url The URL to query for a String response.
     * @return The translated String.
     * @throws Exception on error.
     */
    protected static String retrieveString(final URL url) throws Exception {
    	try {
    		final String response = retrieveResponse(url);    		
            return jsonToString(response);
    	} catch (Exception ex) {
    		throw new Exception("[microsoft-translator-api] Error retrieving translation : " + ex.getMessage(), ex);
    	}
    }
    
    /**
     * Fetches the JSON response, parses the JSON Response as an Array of JSONObjects,
     * retrieves the String value of the specified JSON Property, and returns the result of 
     * the request as a String Array.
     * 
     * @param url The URL to query for a String response.
     * @return The translated String[].
     * @throws Exception on error.
     */
    protected static String[] retrieveStringArr(final URL url, final String jsonProperty) throws Exception {
    	try {
    	    final String response = retrieveResponse(url);    		
            return jsonToStringArr(response,jsonProperty);
    	} catch (Exception ex) {
    		throw new Exception("[microsoft-translator-api] Error retrieving translation.", ex);
    	}
    }
    
    /**
     * Fetches the JSON response, parses the JSON Response as an array of Strings
     * and returns the result of the request as a String Array.
     * 
     * Overloaded to pass null as the JSON Property (assume only Strings instead of JSONObjects)
     * 
     * @param url The URL to query for a String response.
     * @return The translated String[].
     * @throws Exception on error.
     */
    protected static String[] retrieveStringArr(final URL url) throws Exception {
    	return retrieveStringArr(url,null);
    }
    
    /**
     * Fetches the JSON response, parses the JSON Response, returns the result of the request as an array of integers.
     * 
     * @param url The URL to query for a String response.
     * @return The translated String.
     * @throws Exception on error.
     */
    protected static Integer[] retrieveIntArray(final URL url) throws Exception {
    	try {
    		final String response = retrieveResponse(url);    		
            return jsonToIntArr(response);
    	} catch (Exception ex) {
    		throw new Exception("[microsoft-translator-api] Error retrieving translation : " + ex.getMessage(), ex);
    	}
    }
    
    private static Integer[] jsonToIntArr(final String inputString) throws Exception {
    	final JSONArray jsonArr = (JSONArray)JSONValue.parse(inputString);
        Integer[] intArr = new Integer[jsonArr.size()];
        int i = 0;
        for(Object obj : jsonArr) {
        	intArr[i] = ((Long)obj).intValue();
        	i++;
        }
        return intArr;
    }
    
    private static String jsonToString(final String inputString) throws Exception {
        String json = (String)JSONValue.parse(inputString);
        return json.toString();
    }
    
    // Helper method to parse a JSONArray. Reads an array of JSONObjects and returns a String Array
    // containing the toString() of the desired property. If propertyName is null, just return the String value.
    private static String[] jsonToStringArr(final String inputString, final String propertyName) throws Exception {
        final JSONArray jsonArr = (JSONArray)JSONValue.parse(inputString);
        String[] values = new String[jsonArr.size()];
        
        int i = 0;
        for(Object obj : jsonArr) {
            if(propertyName!=null&&propertyName.length()!=0) {
                final JSONObject json = (JSONObject)obj;
                if(json.containsKey(propertyName)) {
                    values[i] = json.get(propertyName).toString();
                }
            } else {
                values[i] = obj.toString();
            }
            i++;
        }
        return values;
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
                            // Need to strip the Unicode Zero-width Non-breaking Space. For some reason, the Microsoft AJAX
                            // services prepend this to every response
                            outputBuilder.append(string.replaceAll("\uFEFF", ""));
    			}
    		}
    	} catch (Exception ex) {
    		throw new Exception("[microsoft-translator-api] Error reading translation stream.", ex);
    	}
    	
    	return outputBuilder.toString();
    }
    
    //Check if ready to make request, if not, throw a RuntimeException
    protected static void validateServiceState() throws Exception {
        if(apiKey==null||apiKey.length()<16) {
            throw new RuntimeException("INVALID_API_KEY - Please set the API Key with your Bing Developer's Key");
        }
    }
    
    protected static String buildStringArrayParam(Object[] values) {
        StringBuilder targetString = new StringBuilder("[\""); 
        String value;
        for(Object obj : values) {
            if(obj!=null) {
                value = obj.toString();
                if(value.length()!=0) {
                    if(targetString.length()>2)
                        targetString.append(",\"");
                    targetString.append(value);
                    targetString.append("\"");
                }
            }
        }
        targetString.append("]");
        return targetString.toString();
    }
    
}
