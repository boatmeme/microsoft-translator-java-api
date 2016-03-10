///*
// * microsoft-translator-java-api
// * 
// * Copyright 2012 Jonathan Griggs <jonathan.griggs at gmail.com>.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.memetix.mst.language;
//
//import static org.junit.Assert.*;
//
//import com.memetix.mst.language.Language;
//import java.net.URL;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//
///**
// *
// * @author Jonathan Griggs <jonathan.griggs at gmail.com>
// */
//public class LanguageTest {
//    Properties p;
//    
//    @Rule
//    public ExpectedException exception = ExpectedException.none();
//    
//    
//    @Before
//    public void setUp() throws Exception {
//        p = new Properties();
//        URL url = ClassLoader.getSystemResource("META-INF/config.properties");
//        p.load(url.openStream());
//        String apiKey = p.getProperty("microsoft.translator.api.key");
//        if(System.getProperty("test.api.key")!=null) {
//            apiKey = System.getProperty("test.api.key").split(",")[0];
//        }
//        String clientId = p.getProperty("microsoft.translator.api.clientId");
//        if(System.getProperty("test.api.key")!=null) {
//            clientId = System.getProperty("test.api.key").split(",")[1];
//        }
//        String clientSecret = p.getProperty("microsoft.translator.api.clientSecret");
//        if(System.getProperty("test.api.key")!=null) {
//            clientSecret = System.getProperty("test.api.key").split(",")[2];
//        }
//        Language.setClientId(clientId);
//        Language.setClientSecret(clientSecret);
//        Language.setKey(apiKey);
//    }
//    
//    @After
//    public void tearDown() throws Exception {
//
//    }
//
//    /**
//     * Test of valueOf method, of class Language.
//     */
//    @Test
//    public void testValueOf() {
//        String name = "ENGLISH";
//        Language expResult = Language.ENGLISH;
//        Language result = Language.valueOf(name);
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of fromString method, of class Language.
//     */
//    @Test
//    public void testFromString() {
//        String pLanguage = "en";
//        Language expResult = Language.ENGLISH;
//        Language result = Language.fromString(pLanguage);
//        assertEquals(expResult, result);
//    }
//    
//    @Test
//    public void testFromString_ClientIdOnly() {
//        String pLanguage = "en";
//        Language.setKey(null);
//        Language expResult = Language.ENGLISH;
//        Language result = Language.fromString(pLanguage);
//        assertEquals(expResult, result);
//    }
//    @Test
//    public void testGetLanguage_NoKey() throws Exception {
//        Language.setKey(null);
//        Language.setClientId(null);
//        Language locale = Language.PERSIAN;
//        
//        exception.expect(RuntimeException.class);
//        exception.expectMessage("Must provide a Windows Azure Marketplace Client Id and Client Secret - Please see http://msdn.microsoft.com/en-us/library/hh454950.aspx for further documentation");
//        Language.FRENCH.getName(locale);
//    }
//    
//    @Test
//    public void testGetLanguage_WrongKey() throws Exception {
//        Language.setKey("wrong_key");
//        Language locale = Language.PERSIAN;
//        
//        exception.expect(RuntimeException.class);
//        exception.expectMessage("INVALID_API_KEY - Please set the API Key with your Bing Developer's Key");
//        Language.FRENCH.getName(locale);
//    }
//
//    /**
//     * Test of toString method, of class Language.
//     */
//    @Test
//    public void testToString() {
//        Language instance = Language.ENGLISH;
//        String expResult = "en";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//    }
//
//    
//    /**
//     * Test of getLanguageName method, of class Language.
//     */
//    @Test
//    public void testGetNameLocalized() throws Exception {
//        Language locale = Language.ENGLISH;
//        String expResult = "French";
//        String result = Language.FRENCH.getName(locale);
//        assertEquals(expResult, result);
//        
//        locale = Language.FRENCH;
//        expResult = "Anglais";
//        result = Language.ENGLISH.getName(locale);
//        assertEquals(expResult, result);
//    }
//    
//    @Test
//    public void testGetAllNamesLocalizedCached() throws Exception {
//        //Flush the caches, so we can test for timing
//        Language.flushNameCache();
//        
//        
//        long startTime1 = System.currentTimeMillis();
//        for(Language lang : Language.values()) {
//            lang.getName(Language.FRENCH);
//            //System.out.println(name + " : " + lang.toString());
//        }
//        long totalTime1 = System.currentTimeMillis()-startTime1;
//        
//        long startTime2 = System.currentTimeMillis();
//        for(Language lang : Language.values()) {
//            lang.getName(Language.FRENCH);
//        }
//        long totalTime2 = System.currentTimeMillis()-startTime2;
//        assert totalTime1 > totalTime2;
//        
//        /* Uncomment this block to eyeball and make sure the name localization is working for all languages
//        for(Language lang : Language.values()) {
//            System.out.println(lang.toString() + " / " + Language.VIETNAMESE.getName(lang));
//        }
//        */
//    }
//    
//    @Test
//    public void testGetAllLanguageCodes() throws Exception {
//        //Flush the caches, so we can test for timing
//        Language.flushNameCache();
//        
//        List<String> languageCodes = Language.getLanguageCodesForTranslation();
//        assert languageCodes.size() > 0;
//    }
//    
//    @Test
//    public void testGetLocalizedNameMap() throws Exception {
//        Language locale = Language.ENGLISH;
//        Map<String,Language> result = Language.values(locale);
//        /*
//        for(String langName : result.keySet()) {
//            System.out.println(langName);
//        }
//         */
//        assertEquals(42, result.size());
//    }
//}
