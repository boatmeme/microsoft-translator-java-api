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
package com.memetix.mst.language;

import com.memetix.mst.MicrosoftTranslatorAPI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SpokenLanguage - an enum of all spoken language codes supported by the Microsoft Translator API for the Speak Service
 * 
 * Uses the AJAX Interface V2 - see: http://msdn.microsoft.com/en-us/library/ff512399.aspx
 * 
 * @author Jonathan Griggs <jonathan.griggs at gmail.com>
 */
public enum SpokenDialect {
        CATALAN_SPAIN("ca-es"),
        DANISH_DENMARK("da-dk"),
        GERMAN_GERMANY("de-de"),
        ENGLISH_AUSTRALIA("en-au"),
        ENGLISH_CANADA("en-ca"),
        ENGLISH_UNITED_KINGDOM("en-gb"),
        ENGLISH_INDIA("en-in"),
        ENGLISH_UNITED_STATES("en-us"),
        SPANISH_SPAIN("es-es"),
        SPANISH_MEXICO("es-mx"),
        FINNISH_FINLAND("fi-fi"),
        FRENCH_CANADA("fr-ca"),
        FRENCH_FRANCE("fr-fr"),
        ITALIAN_ITALY("it-it"),
        JAPANESE_JAPAN("ja-jp"),
        KOREAN_KOREA("ko-kr"),
        NORWEGIAN_NORWAY("nb-no"),
        DUTCH_NETHERLANDS("nl-nl"),
        POLISH_POLAND("pl-pl"),
        PORTUGUESE_BRAZIL("pt-br"),
        PORTUGUESE_PORTUGAL("pt-pt"),
        RUSSIAN_RUSSIA("ru-ru"),
        SWEDISH_SWEDEN("sv-se"),
        CHINESE_SIMPLIFIED_PEOPLES_REPUBLIC_OF_CHINA("zh-cn"),
        CHINESE_TRADITIONAL_HONG_KONG_SAR("zh-hk"),
        CHINESE_TRADITIONAL_TAIWAN("zh-tw");

	/**
	 * Microsoft's String representation of this language.
	 */
	private final String language;
        
    /**
     * Internal Localized Name Cache
     */
    private Map<Language,String> localizedCache = new ConcurrentHashMap<Language,String>();
	
	/**
	 * Enum constructor.
	 * @param pLanguage The language identifier.
	 */
	private SpokenDialect(final String pLanguage) {
		language = pLanguage;
	}
	
	public static SpokenDialect fromString(final String pLanguage) {
		for (SpokenDialect l : values()) {
			if (l.toString().equals(pLanguage)) {
				return l;
			}
		}
		return null;
	}
	
	/**
	 * Returns the String representation of this language.
	 * @return The String representation of this language.
	 */
	@Override
	public String toString() {
		return language;
	}
        
        public static void setKey(String pKey) {
            SpokenDialectService.setKey(pKey);
        }
        
        public static void setClientId(String pId) {
            SpokenDialectService.setClientId(pId);
        }
        
        public static void setClientSecret(String pSecret) {
            SpokenDialectService.setClientSecret(pSecret);
        }
        
    /**
     * getName()
     * 
     * Returns the name for this language in the tongue of the specified locale
     * 
     * If the name is not cached, then it retrieves the name of ALL languages in this locale.
     * This is not bad behavior for 2 reasons:
     * 
     *      1) We can make a reasonable assumption that the client will request the
     *         name of another language in the same locale 
     *      2) The GetLanguageNames service call expects an array and therefore we can 
     *         retrieve ALL names in the same, single call anyway.
     * 
	 * @return The String representation of this language's localized Name.
	 */
        public String getName(Language locale) throws Exception {
            String localizedName = null;
            if(this.localizedCache.containsKey(locale)) {
                localizedName = this.localizedCache.get(locale);
            } else {
              
                //If not in the cache, pre-load all the Language names for this locale
                String[] names = SpokenDialectService.execute(SpokenDialect.values(), locale);
                int i = 0;
                for(SpokenDialect lang : SpokenDialect.values()) {
                    lang.localizedCache.put(locale,names[i]);
                    i++;
                }
                localizedName = this.localizedCache.get(locale);
            }
            return localizedName;
        }     
        
        // Flushes the localized name cache for this language
        private void flushCache() {
            this.localizedCache.clear();
        }
        
        // Flushes the localized name cache for all languages
        public static void flushNameCache() {
            for(SpokenDialect lang : SpokenDialect.values())
                lang.flushCache();
        }
        
        
        private final static class SpokenDialectService extends MicrosoftTranslatorAPI {
            private static final String SERVICE_URL = "http://api.microsofttranslator.com/V2/Ajax.svc/GetLanguageNames?";
            
	        /**
	         * Detects the language of a supplied String.
	         * 
	         * @param text The String to detect the language of.
	         * @return A DetectResult object containing the language, confidence and reliability.
	         * @throws Exception on error.
	         */
	        public static String[] execute(final SpokenDialect[] targets, final Language locale) throws Exception {
	                //Run the basic service validations first
	                validateServiceState(); 
	                String[] localizedNames = new String[0];
	                if(locale==Language.AUTO_DETECT) {
	                    return localizedNames;
	                }
	
	                final String targetString = buildStringArrayParam(SpokenDialect.values());
	
	                final URL url = new URL(SERVICE_URL 
	                        +(apiKey != null ? PARAM_APP_ID + URLEncoder.encode(apiKey,ENCODING) : "") 
	                        +PARAM_LOCALE+URLEncoder.encode(locale.toString(), ENCODING)
	                        +PARAM_LANGUAGE_CODES + URLEncoder.encode(targetString, ENCODING));
	                localizedNames = retrieveStringArr(url);
	                return localizedNames;
	        }

     }
}
