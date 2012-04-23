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
package com.memetix.mst.translate;

import com.memetix.mst.language.Language;
import com.memetix.mst.MicrosoftTranslatorAPI;
import java.net.URL;
import java.net.URLEncoder;
/**
 * Translate
 * 
 * Makes calls to the Microsoft Translator API /Translate service
 * 
 * Uses the AJAX Interface V2 - see: http://msdn.microsoft.com/en-us/library/ff512406.aspx
 * 
 * @author Jonathan Griggs <jonathan.griggs at gmail.com>
 */
public final class Translate extends MicrosoftTranslatorAPI {
    
    private static final String SERVICE_URL = "http://api.microsofttranslator.com/V2/Ajax.svc/Translate?";
    private static final String ARRAY_SERVICE_URL = "http://api.microsofttranslator.com/V2/Ajax.svc/TranslateArray?";
    private static final String ARRAY_JSON_OBJECT_PROPERTY = "TranslatedText";
    
    //prevent instantiation
    private Translate(){};
    
    /**
     * Translates text from a given Language to another given Language using Microsoft Translator.
     * 
     * @param text The String to translate.
     * @param from The language code to translate from.
     * @param to The language code to translate to.
     * @return The translated String.
     * @throws Exception on error.
     */
    public static String execute(final String text, final Language from, final Language to) throws Exception {
        //Run the basic service validations first
        validateServiceState(text); 
        final String params = 
                (apiKey != null ? PARAM_APP_ID + URLEncoder.encode(apiKey,ENCODING) : "") 
                + PARAM_FROM_LANG + URLEncoder.encode(from.toString(),ENCODING) 
                + PARAM_TO_LANG + URLEncoder.encode(to.toString(),ENCODING) 
                + PARAM_TEXT_SINGLE + URLEncoder.encode(text,ENCODING);
        
        final URL url = new URL(SERVICE_URL + params);
    	final String response = retrieveString(url);
    	return response;
    }
    
    /**
     * Translates text from a given Language to another given Language using Microsoft Translator.
     * 
     * Default the from to AUTO_DETECT
     * 
     * @param text The String to translate.
     * @param to The language code to translate to.
     * @return The translated String.
     * @throws Exception on error.
     */
    public static String execute(final String text, final Language to) throws Exception {
        return execute(text,Language.AUTO_DETECT,to);
    }
    
    /**
     * Translates an array of texts from a given Language to another given Language using Microsoft Translator's TranslateArray
     * service
     * 
     * Note that the Microsoft Translator expects all source texts to be of the SAME language. 
     * 
     * @param texts The Strings Array to translate.
     * @param from The language code to translate from.
     * @param to The language code to translate to.
     * @return The translated Strings Array[].
     * @throws Exception on error.
     */
    public static String[] execute(final String[] texts, final Language from, final Language to) throws Exception {
        //Run the basic service validations first
        validateServiceState(texts); 
        final String params = 
                (apiKey != null ? PARAM_APP_ID + URLEncoder.encode(apiKey,ENCODING) : "") 
                + PARAM_FROM_LANG + URLEncoder.encode(from.toString(),ENCODING) 
                + PARAM_TO_LANG + URLEncoder.encode(to.toString(),ENCODING) 
                + PARAM_TEXT_ARRAY + URLEncoder.encode(buildStringArrayParam(texts),ENCODING);
        
        final URL url = new URL(ARRAY_SERVICE_URL + params);
    	final String[] response = retrieveStringArr(url,ARRAY_JSON_OBJECT_PROPERTY);
    	return response;
    }
    
    /**
     * Translates an array of texts from an Automatically detected language to another given Language using Microsoft Translator's TranslateArray
     * service
     * 
     * Note that the Microsoft Translator expects all source texts to be of the SAME language. 
     * 
     * This is an overloaded convenience method that passes Language.AUTO_DETECT as fromLang to
     * execute(texts[],fromLang,toLang)
     * 
     * @param texts The Strings Array to translate.
     * @param from The language code to translate from.
     * @param to The language code to translate to.
     * @return The translated Strings Array[].
     * @throws Exception on error.
     */
    public static String[] execute(final String[] texts, final Language to) throws Exception {
        return execute(texts,Language.AUTO_DETECT,to);
    }
    
    private static void validateServiceState(final String[] texts) throws Exception {
        int length = 0;
        for(String text : texts) {
            length+=text.getBytes(ENCODING).length;
        }
        if(length>10240) {
            throw new RuntimeException("TEXT_TOO_LARGE - Microsoft Translator (Translate) can handle up to 10,240 bytes per request");
        }
        validateServiceState();
    }
    
    
    private static void validateServiceState(final String text) throws Exception {
    	final int byteLength = text.getBytes(ENCODING).length;
        if(byteLength>10240) {
            throw new RuntimeException("TEXT_TOO_LARGE - Microsoft Translator (Translate) can handle up to 10,240 bytes per request");
        }
        validateServiceState();
    }
    
    
    
}
