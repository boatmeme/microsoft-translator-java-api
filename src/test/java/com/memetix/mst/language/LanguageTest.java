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

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Jonathan Griggs <jonathan.griggs at gmail.com>
 */
public class LanguageTest {
    Properties p;

    String clientId;
    String clientSecret;
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    
    @Before
    public void setUp() throws Exception {
        p = new Properties();
        URL url = ClassLoader.getSystemResource("META-INF/config.properties");
        p.load(url.openStream());
        clientId = p.getProperty("microsoft.translator.api.clientId");
        if(System.getProperty("test.api.key")!=null) {
            clientId = System.getProperty("test.api.key").split(",")[1];
        }
        clientSecret = p.getProperty("microsoft.translator.api.clientSecret");
        if(System.getProperty("test.api.key")!=null) {
            clientSecret = System.getProperty("test.api.key").split(",")[2];
        }
        
    }
    
    @After
    public void tearDown() throws Exception {

    }

    /**
     * Test of valueOf method, of class Language.
     */
    @Test
    public void testValueOf() {
        String name = "ENGLISH";
        Language expResult = Language.ENGLISH;
        Language result = Language.valueOf(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of fromString method, of class Language.
     */
    @Test
    public void testFromString() {
        String pLanguage = "en";
        Language expResult = Language.ENGLISH;
        Language result = Language.fromString(pLanguage);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFromString_ClientIdOnly() {
        String pLanguage = "en";
         
        Language expResult = Language.ENGLISH;
        Language result = Language.fromString(pLanguage);
        assertEquals(expResult, result);
    }
    @Test
    public void testGetLanguage_NoKey() throws Exception {
    	clientId = null;
    	clientSecret = null;
        Language locale = Language.PERSIAN;
        
        exception.expect(RuntimeException.class);
        exception.expectMessage("Must provide a Windows Azure Marketplace Client Id and Client Secret - Please see http://msdn.microsoft.com/en-us/library/hh454950.aspx for further documentation");
        Language.FRENCH.getName(locale, null, null);
    }
    
    @Test
    public void testGetLanguage_WrongKey() throws Exception {
    	clientId = "WRONG";
        Language locale = Language.PERSIAN;
        
        exception.expect(Exception.class);
        exception.expectMessage("Server returned HTTP response code: 400");
        Language.FRENCH.getName(locale, clientId, clientSecret);
    }

    /**
     * Test of toString method, of class Language.
     */
    @Test
    public void testToString() {
        Language instance = Language.ENGLISH;
        String expResult = "en";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    
    /**
     * Test of getLanguageName method, of class Language.
     */
    @Test
    public void testGetNameLocalized() throws Exception {
        Language locale = Language.ENGLISH;
        String expResult = "French";
        String result = Language.FRENCH.getName(locale, clientId, clientSecret);
        assertEquals(expResult, result);
        
        locale = Language.FRENCH;
        expResult = "Anglais";
        result = Language.ENGLISH.getName(locale, clientId, clientSecret);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetAllNamesLocalizedCached() throws Exception {
        //Flush the caches, so we can test for timing
        Language.flushNameCache();
        
        long startTime1 = System.currentTimeMillis();
        for(Language lang : Language.values()) {
            lang.getName(Language.FRENCH, clientId, clientSecret);
            //System.out.println(name + " : " + lang.toString());
        }
        long totalTime1 = System.currentTimeMillis()-startTime1;
        
        long startTime2 = System.currentTimeMillis();
        for(Language lang : Language.values()) {
            lang.getName(Language.FRENCH, clientId, clientSecret);
        }
        long totalTime2 = System.currentTimeMillis()-startTime2;
        assert totalTime1 > totalTime2;
        
        /* Uncomment this block to eyeball and make sure the name localization is working for all languages
        for(Language lang : Language.values()) {
            System.out.println(lang.toString() + " / " + Language.VIETNAMESE.getName(lang));
        }
        */
    }
    
    @Test
    public void testGetAllLanguageCodes() throws Exception {
        //Flush the caches, so we can test for timing
        Language.flushNameCache();

        List<String> languageCodes = Language.getLanguageCodesForTranslation(clientId, clientSecret);
        for(String lc : languageCodes) {
        	System.out.println(lc);
        }
        assert languageCodes.size() > 0;
    }
    
    @Test
    public void testGetLocalizedNameMap() throws Exception {
        Language locale = Language.ENGLISH;
        Map<String,Language> result = Language.values(locale, clientId, clientSecret);
        
        for(String langName : result.keySet()) {
            System.out.println(langName);
        }
        assertEquals(42, result.size());
    }
}
