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
import java.util.Properties;
import junit.framework.TestCase;

/**
 *
 * @author Jonathan Griggs <jonathan.griggs at gmail.com>
 */
public class LanguageTest extends TestCase {
    Properties p;
    public LanguageTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        p = new Properties();
        URL url = ClassLoader.getSystemResource("META-INF/config.properties");
        p.load(url.openStream());
        String apiKey = p.getProperty("microsoft.translator.api.key");
        Language.setKey(apiKey);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of values method, of class Language.
     */
    public void testValues() {
        System.out.println("values");
        Language[] expResult = null;
        Language[] result = Language.values();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class Language.
     */
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "ENGLISH";
        Language expResult = Language.ENGLISH;
        Language result = Language.valueOf(name);
        assertEquals(expResult, result);

    }

    /**
     * Test of fromString method, of class Language.
     */
    public void testFromString() {
        System.out.println("fromString");
        String pLanguage = "en";
        Language expResult = Language.ENGLISH;
        Language result = Language.fromString(pLanguage);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Language.
     */
    public void testToString() {
        System.out.println("toString");
        Language instance = Language.ENGLISH;
        String expResult = "en";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    
    /**
     * Test of getLanguageName method, of class Language.
     */
    public void testGetNameLocalized() throws Exception {
        System.out.println("getLanguageName");
        Language locale = Language.ENGLISH;
        String expResult = "French";
        String result = Language.FRENCH.getName(locale);
        assertEquals(expResult, result);
        
        locale = Language.FRENCH;
        expResult = "Anglais";
        result = Language.ENGLISH.getName(locale);
        assertEquals(expResult, result);
    }
    
    public void testGetAllNamesLocalizedCached() throws Exception {
        long startTime1 = System.currentTimeMillis();
        for(Language lang : Language.values()) {
            System.out.println(lang.getName(Language.FRENCH) + " : " + lang.toString());
        }
        long totalTime1 = System.currentTimeMillis()-startTime1;
        
        long startTime2 = System.currentTimeMillis();
        for(Language lang : Language.values()) {
            System.out.println(lang.getName(Language.FRENCH) + " : " + lang.toString());
        }
        long totalTime2 = System.currentTimeMillis()-startTime2;
        System.out.println("Uncached: " + totalTime1 + "ms, Cached: " + totalTime2 + "ms");
        assert totalTime1 > totalTime2;
    }
}
