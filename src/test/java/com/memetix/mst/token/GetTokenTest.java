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
package com.memetix.mst.token;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.util.Properties;

import com.memetix.mst.MicrosoftTranslatorAPI;
import com.memetix.mst.detect.Detect;
import com.memetix.mst.language.Language;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit Tests for the Auth Token
 * @author Thomas Lehoux <tlehoux at gmail.com>
 */
public class GetTokenTest {
    Properties p;

    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    

    @Test
    public void testGetToken() throws Exception {

        p = new Properties();
        URL url = ClassLoader.getSystemResource("META-INF/config.properties");
        p.load(url.openStream());
        String apiKey = p.getProperty("microsoft.azure.subscription.key");
        if(System.getProperty("test.api.key")!=null) {
            apiKey = System.getProperty("test.api.key").split(",")[0];
        }
        assertNotNull(MicrosoftTranslatorAPI.getToken(apiKey));
    }

}
