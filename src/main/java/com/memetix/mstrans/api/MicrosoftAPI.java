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
package com.memetix.mstrans.api;
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
    
    /**
     * Sets the API key.
     * @param pKey The API key.
     */
    public static void setKey(final String pKey) {
    	apiKey = pKey;
    }
    
}
