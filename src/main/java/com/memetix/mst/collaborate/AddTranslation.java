package com.memetix.mst.collaborate;

import com.memetix.mst.MicrosoftTranslatorAPI;

/**
 * AddTranslation 
 * 
 * Provides an interface to the Microsoft Translator Add Translation service
 * This is part of the Collaborative Translation functionality
 * 
 * @author Jonathan Griggs <jonathan.griggs at gmail.com>
 * @since 0.4
 * @since 06/13/2011
 */
public final class AddTranslation extends MicrosoftTranslatorAPI {
	private static final String SERVICE_URL = "http://api.microsofttranslator.com/V2/Ajax.svc/AddTranslation?";

	//prevent instantiation
	private AddTranslation(){};
}
