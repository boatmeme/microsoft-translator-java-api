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

import static org.junit.Assert.*;

import com.memetix.mst.language.SpokenDialect;
import com.memetix.mst.language.Language;
import java.net.URL;
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
public class SpokenDialectTest {
    Properties p;
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Before
    public void setUp() throws Exception {
        p = new Properties();
        URL url = ClassLoader.getSystemResource("META-INF/config.properties");
        p.load(url.openStream());
        String subscriptionKey = p.getProperty("microsoft.translator.api.subscriptionKey");
        if(System.getProperty("test.subscription.key")!=null) {
            subscriptionKey = System.getProperty("test.subscription.key");
        }
        SpokenDialect.setSubscriptionKey(subscriptionKey);
    }
    
    @After
    public void tearDown() throws Exception {
      
    }
    
    @Test
    public void testGetSpokenDialect_NoKey() throws Exception {
        SpokenDialect.flushNameCache();
        SpokenDialect.setSubscriptionKey(null);
        Language locale = Language.ENGLISH;
        
        exception.expect(RuntimeException.class);
        exception.expectMessage("Must provide a Microsoft Translator Text Translation Subscription Key - Please see http://docs.microsofttranslator.com/text-translate.html for further documentation");
        SpokenDialect.FRENCH_CANADA.getName(locale);
    }
    
    /**
     * Test of valueOf method, of class Language.
     */
    @Test
    public void testValueOf() {
        String name = "ENGLISH_UNITED_STATES";
        SpokenDialect expResult = SpokenDialect.ENGLISH_UNITED_STATES;
        SpokenDialect result = SpokenDialect.valueOf(name);
        assertEquals(expResult, result);

    }

    /**
     * Test of fromString method, of class Language.
     */
    @Test
    public void testFromString() {
        String pLanguage = "en-us";
        SpokenDialect expResult = SpokenDialect.ENGLISH_UNITED_STATES;
        SpokenDialect result = SpokenDialect.fromString(pLanguage);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Language.
     */
    @Test
    public void testToString() {
        SpokenDialect instance = SpokenDialect.ENGLISH_UNITED_STATES;
        String expResult = "en-us";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getLanguageName method, of class Language.
     */
    @Test
    public void testGetNameLocalized() throws Exception {
        Language locale = Language.ENGLISH;
        String expResult = "French (Canada)";
        String result = SpokenDialect.FRENCH_CANADA.getName(locale);
        assertEquals(expResult, result);
        
        locale = Language.FRENCH;
        expResult = "Anglais (Inde)";
        result = SpokenDialect.ENGLISH_INDIA.getName(locale);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetAllNamesLocalizedCached() throws Exception {
        //Flush the caches, so we can test for timing
        Language.flushNameCache();
        
        long startTime1 = System.currentTimeMillis();
        for(Language lang : Language.values()) {
            lang.getName(Language.FRENCH);
            //System.out.println(name + " : " + lang.toString());
        }
        long totalTime1 = System.currentTimeMillis()-startTime1;
        
        long startTime2 = System.currentTimeMillis();
        for(Language lang : Language.values()) {
            lang.getName(Language.FRENCH);
            //System.out.println(name + " : " + lang.toString());
        }
        long totalTime2 = System.currentTimeMillis()-startTime2;
        //System.out.println("Uncached: " + totalTime1 + "ms, Cached: " + totalTime2 + "ms");
        assert totalTime1 > totalTime2;
        
        /* Uncomment this block to eyeball and make sure the name localization is working for all languages
        for(Language lang : Language.values()) {
            System.out.println(lang.toString() + " / " + Language.VIETNAMESE.getName(lang));
        }
        */
    }
}
