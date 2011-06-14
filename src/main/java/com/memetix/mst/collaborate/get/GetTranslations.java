package com.memetix.mst.collaborate.get;

import com.memetix.mst.MicrosoftTranslatorAPI;

/**
 * GetTranslations 
 * 
 * Provides an interface to the Microsoft Translator GetTranslations service
 * This is part of the Collaborative Translations functionality and not to be
 * confused with the Translate service.
 * 
 * @author Jonathan Griggs <jonathan.griggs at gmail.com>
 * @since 0.4
 * @since 06/13/2011
 */
public final class GetTranslations extends MicrosoftTranslatorAPI {
	private static final String SERVICE_URL = "http://api.microsofttranslator.com/V2/Ajax.svc/GetTranslations?";
	
	//prevent instantiation
	private GetTranslations(){};
}
