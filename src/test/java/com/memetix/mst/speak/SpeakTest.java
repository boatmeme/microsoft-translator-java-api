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
package com.memetix.mst.speak;

import static org.junit.Assert.*;

import com.memetix.mst.language.SpokenDialect;
import java.net.URL;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Jonathan Griggs <jonathan.griggs AT gmail.com>
 */
public class SpeakTest {
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
        Speak.setSubscriptionKey(subscriptionKey);
    }
    
    @After
    public void tearDown() throws Exception {
     
    }
    
    /**
     * Test of execute method, of class Speak.
     */
    @Test
    public void testGetSpeakUrl_NoKey() throws Exception {
        Speak.setSubscriptionKey(null);
        exception.expect(RuntimeException.class);
        exception.expectMessage("Must provide a Microsoft Translator Text Translation Subscription Key - Please see http://docs.microsofttranslator.com/text-translate.html for further documentation");
        String text = "Hello World!";
        SpokenDialect language = SpokenDialect.ENGLISH_INDIA;
        Speak.execute(text, language);
    }

    /**
     * Test of execute method, of class Speak.
     */
    @Test
    public void testGetSpeakUrl() throws Exception {
        String text = "Hello World!";
        SpokenDialect language = SpokenDialect.ENGLISH_INDIA;
        String expResult = "http://api.microsofttranslator.com/V2/http.svc/Speak";
        String result = Speak.execute(text, language);
        assertEquals(true, result.contains(expResult));
    }
    
    /**
     * Test of execute method, of class Speak.
     */
    @Test
    public void testGetSpeakUrlUk() throws Exception {
        String text = "Hello World!";
        SpokenDialect language = SpokenDialect.ENGLISH_UNITED_KINGDOM;
        String expResult = "http://api.microsofttranslator.com/V2/http.svc/Speak";
        String result = Speak.execute(text, language);
        assertEquals(true, result.contains(expResult));
    }
    
    @Test
    public void testLargeTooLarge() throws Exception {
                String largeText = "Figures from the Office for National Statistics (ONS) show that between December and April, "
                            + "the five-month period typically regarded as peak bonus season, those working in the financial "
                            + "intermediation sector received bonuses worth ¬¨¬£7.6bn. The figure is more than 40pc lower than last"
                            + "year's total of ¬¨¬£13.2bn, but the fact that it came during a period where the banking system owed its"
                            + "survival to the rescue support of taxpayers\' money will spark further outrage. Related Articles USS"
                            + "pays bonuses despite fund fall Ex-HBOS chief Hornby gives up ¬¨¬£1m redundancyBankers blind to bonus "
                            + "'furore' Barclays and Lloyds to dish out millions in bonuses. City bonuses defy credit crunch and "
                            + "hit new record of ¬¨¬£13bn. We are still mad with the banks but we are no closer to getting even. News"
                            + "of the huge sums being offered by Barclays to five traders at JP Morgan will also stoke the row. "
                            + "Barclays is close to poaching Todd Edgar, 37, a star commodity trader at JP Morgan, and his four "
                            + "team members to head up the foreign exchange trading desk. Mr Edgar is responsible for a $2bn book "
                            + "at JP Morgan and single-handedly made the US investment bank a $100m profit last year. At Barclays,"
                            + "the team will have an emerging markets focus, with two members based in Asia and Mr Edgar and the "
                            + "others operating out of London. They will also continue to trade commodities, but to a lesser degree"
                            + "than before. Barclays has offered the team a combined $25m in salaries and bonuses paid in cash "
                            + "guarantees and deferred stock. In addition, they will take a share of future profits that could lift"
                            + "the package to $50m. Market-leading rates on profit shares are currently 12pc, according to bankers,"
                            + "but Mr Edgar and his team are said to have been offered even more generous terms. Sources suggest Mr"
                            + "Edgar himself could receive up to half the total. It is understood the pay package does not "
                            + "contravene any of the Financial Service Authority's guidelines. At JP Morgan, Mr Edgar was largely a"
                            + "proprietary trader, gambling with the bank's own money. At Barclays, although he will take "
                            + "proprietary positions, his main role will be client business. Mr Edgar's appointment would follow "
                            + "public outrage last week over a ¬¨¬£7m \"market leading\" deal agreed by Royal Bank of Scotland, 70pc "
                            + "owned by the taxpayer, for a Merrill Lynch banker, Antonio Polverino. Although Barclays has not "
                            + "taken any cash directly from the state, critics say it is the beneficiary of ¬¨¬£1.2 trillion of "
                            + "taxpayer support for the financial system as a whole. Senior Treasury officials believe that the "
                            + "bank would have collapsed were it not for their assistance. In an interview this weekend, the Shadow"
                            + "Chancellor, George Osborne said it was \"totally unacceptable\" that the major banks are paying "
                            + "large bonuses on the back of taxpayer guarantees. Mr Osborne said: \"There are hundreds of billions "
                            + "of pounds of guarantees in existence: guarantees provided by the taxpayer to all banks. The reason "
                            + "those guarantees are in place is not so the bankers can pay themselves large bonuses. \"The scale of"
                            + "this year's bonus payments, as revealed by the ONS statistics, would be enough to finance an almost "
                            + "2p reduction in the basic rate of income tax. The payments came after the unprecedented bail-out of "
                            + "British banks, which cost the taxpayer some ¬¨¬£35bn in capital infusions. Lord Oakeshott, Liberal "
                            + "Democrat Treasury spokesman, said: \"These figures suggest that the bankers are taking most of the "
                            + "profits and the taxpayer is taking most of the risk. \"The official confirmation of the scale of "
                            + "City bonuses in the past year underlines the fact that even against the backdrop of the worst "
                            + "financial crisis in British history, bankers awarded themselves bonuses which were still "
                            + "significantly larger, even in nominal terms, than those handed out five years ago in 2004, when the "
                            + "City was entering the credit boom. Barclays and JP Morgan declined to comment.";
                            largeText += " " + largeText;
                            largeText += " " + largeText;
                            exception.expect(RuntimeException.class);
                            exception.expectMessage("TEXT_TOO_LARGE - Microsoft Translator (Speak) can handle up to 2000 bytes per request");
                            Speak.execute(largeText.substring(0,10242),SpokenDialect.ENGLISH_INDIA);
                            
    }
}
