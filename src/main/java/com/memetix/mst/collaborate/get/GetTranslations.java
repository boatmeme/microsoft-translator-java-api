/*
 * microsoft-translator-java-api
 * 
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
package com.memetix.mst.collaborate.get;

import com.memetix.mst.MicrosoftTranslatorAPI;

/**
 * GetTranslations 
 * 
 * Provides an interface to the Microsoft Translator GetTranslations service
 * This is part of the Collaborative Translations functionality and not to be
 * confused with the Translate service.
 * 
 * Uses the AJAX Interface V2 - see: http://msdn.microsoft.com/en-us/library/ff512402.aspx
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
