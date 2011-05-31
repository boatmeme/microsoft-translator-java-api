/*
 * Copyright 2011 boatmeme.
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
package com.memetix.mst.speak;

import com.memetix.mst.language.SpokenDialect;
import java.net.URL;
import java.util.Properties;
import junit.framework.TestCase;

/**
 *
 * @author boatmeme
 */
public class SpeakTest extends TestCase {
    Properties p;
    public SpeakTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        p = new Properties();
        URL url = ClassLoader.getSystemResource("META-INF/config.properties");
        p.load(url.openStream());
        String apiKey = p.getProperty("microsoft.translator.api.key");
        Speak.setKey(apiKey);
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    /**
     * Test of execute method, of class Speak.
     */
    public void testGetSpeakUrl_NoKey() throws Exception {
        Speak.setKey(null);
        boolean exception = false;
        String text = "Hello World!";
        SpokenDialect language = SpokenDialect.ENGLISH_INDIA;
        try {
            String result = Speak.execute(text, language);
        }catch(RuntimeException re) {
            exception = true;
            assertEquals("INVALID_API_KEY - Please set the API Key with your Bing Developer's Key",re.getMessage());
        }
        assertEquals(true, exception);
    }

    /**
     * Test of execute method, of class Speak.
     */
    public void testGetSpeakUrl() throws Exception {
        String text = "Hello World!";
        SpokenDialect language = SpokenDialect.ENGLISH_INDIA;
        String expResult = "http://api.microsofttranslator.com/V2/http.svc/Speak";
        String result = Speak.execute(text, language);
        assertEquals(true, result.contains(expResult));
    }
}
