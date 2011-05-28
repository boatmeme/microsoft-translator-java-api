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
}
