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
package com.memetix.mst;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Language - an enum of all language codes supported by the Microsoft Translator API
 * 
 * @author Jonathan Griggs <jonathan.griggs at gmail.com>
 */
public enum Language {
        AUTO_DETECT(""),
        ARABIC("ar"),
        BULGARIAN("bg"),
        CHINESE_SIMPLIFIED("zh-CHS"),
	CHINESE_TRADITIONAL("zh-CHT"),
	CZECH("cs"),
	DANISH("da"),
	DUTCH("nl"),
	ENGLISH("en"),
        ESTONIAN("et"),
	FINNISH("fi"),
	FRENCH("fr"),
	GERMAN("de"),
	GREEK("el"),
	HATIAN_CREOLE("ht"),
        HEBREW("he"),
	HUNGARIAN("hu"),
        INDONESIAN("id"),
        ITALIAN("it"),
	JAPANESE("ja"),
	KOREAN("ko"),
	LATVIAN("lv"),
	LITHUANIAN("lt"),
        NORWEGIAN("no"),
	POLISH("pl"),
	PORTUGUESE("pt"),
	ROMANIAN("ro"),
	RUSSIAN("ru"),
	SLOVAK("sk"),
	SLOVENIAN("sl"),
	SPANISH("es"),
	SWEDISH("sv"),
	THAI("th"),
	TURKISH("tr"),
	UKRANIAN("uk"),
	VIETNAMESE("vi");
	
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
	private Language(final String pLanguage) {
		language = pLanguage;
	}
	
	public static Language fromString(final String pLanguage) {
		for (Language l : values()) {
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
            LanguageService.setKey(pKey);
        }
        
        public String getName(Language locale) throws Exception {
            String localizedName;
            if(this.localizedCache.containsKey(locale)) {
                localizedName = this.localizedCache.get(locale);
            } else {
                if(this==Language.AUTO_DETECT) {
                    localizedName = "Auto Detect";
                } else {
                    localizedName = Language.getLanguageName(this, locale);
                    this.localizedCache.put(locale,localizedName);
                }
            }
            return localizedName;
        }
        
        /*
         * Calls the Language Localization Service
         */
        private static String getLanguageName(Language targetCode, Language locale) throws Exception {
            return LanguageService.execute(targetCode, locale);
        }
        
        
        private final static class LanguageService extends MicrosoftAPI {
            private static final String SERVICE_URL = "http://api.microsofttranslator.com/V2/Ajax.svc/GetLanguageNames?locale=";
            /**
             * Detects the language of a supplied String.
             * 
             * @param text The String to detect the language of.
             * @return A DetectResult object containing the language, confidence and reliability.
             * @throws Exception on error.
             */
            public static String execute(final Language target, final Language locale) throws Exception {
                    final URL url = new URL(SERVICE_URL 
                            +URLEncoder.encode(locale.toString(), ENCODING)
                            +"&languageCodes=" + URLEncoder.encode("[\""+target.toString()+"\"]", ENCODING)
                            +"&appId="+apiKey);
                    final String json = retrieveJSON(url);
                    if(json!=null&&json.length()>0)
                        return json.substring(1,json.length()-1);
                    else
                        return json;
            }

        }
}
