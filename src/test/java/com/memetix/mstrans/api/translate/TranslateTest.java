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
package com.memetix.mstrans.api.translate;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Properties;
import junit.framework.TestCase;

/**
 *
 * @author Jonathan Griggs <jonathan.griggs at gmail.com>
 */
public class TranslateTest extends TestCase {
    
    public Properties p;
    
    public TranslateTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        p = new Properties();
        URL url = ClassLoader.getSystemResource("META-INF/config.properties");
        p.load(url.openStream());
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSetApiKey() {
        String apiKey = p.getProperty("microsoft.translator.api.key");
        Translate.setKey(apiKey);
        assert(true);
    }
}
