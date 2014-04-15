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
package com.memetix.mst.translate;

import static org.junit.Assert.*;

import com.memetix.mst.language.Language;
import java.net.URL;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Jonathan Griggs <jonathan.griggs at gmail.com>
 */
public class TranslateTest{
    
    public Properties p;
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Before
    public void setUp() throws Exception {
        p = new Properties();
        URL url = ClassLoader.getSystemResource("META-INF/config.properties");
        p.load(url.openStream());
        String apiKey = p.getProperty("microsoft.translator.api.key");
        if(System.getProperty("test.api.key")!=null) {
            apiKey = System.getProperty("test.api.key").split(",")[0];
        }
        String clientId = p.getProperty("microsoft.translator.api.clientId");
        if(System.getProperty("test.api.key")!=null) {
            clientId = System.getProperty("test.api.key").split(",")[1];
        }
        String clientSecret = p.getProperty("microsoft.translator.api.clientSecret");
        if(System.getProperty("test.api.key")!=null) {
            clientSecret = System.getProperty("test.api.key").split(",")[2];
        }
        Translate.setKey(apiKey);
        Translate.setClientSecret(clientSecret);
        Translate.setClientId(clientId);
    }
    
    @After
    public void tearDown() throws Exception {
        Translate.setKey(null);
        Translate.setContentType("text/plain");
        Translate.setClientId(null);
        Translate.setClientSecret(null);
        Translate.setHttpReferrer(null);
    }

    public void testSetApiKey() {
        assert(true);
    }
    
    @Test
    public void testTranslate_SetReferrer() throws Exception {
        Translate.setHttpReferrer("http://localhost:8080");
        assertEquals("Salut",Translate.execute("Hello", Language.ENGLISH, Language.FRENCH));
    }
    
    @Test
    public void testTranslate_NoApiKey() throws Exception {
        Translate.setHttpReferrer("http://localhost:8080");
        Translate.setKey(null);
        assertEquals("Salut",Translate.execute("Hello", Language.ENGLISH, Language.FRENCH));
    }
    @Test
    public void testTranslate_NoSpace() throws Exception {
        assertEquals("Salut",Translate.execute("Hello", Language.ENGLISH, Language.FRENCH));
    }
    @Test
    public void testTranslate_EncodeSpace() throws Exception {
        assertEquals("Bonjour, mon nom est",Translate.execute("Hello, my name is", Language.ENGLISH, Language.FRENCH));
    }
    @Test
    public void testTranslate_AutoDetectOrigin() throws Exception {
        assertEquals("Bonjour, mon nom est",Translate.execute("Hello, my name is", Language.AUTO_DETECT, Language.FRENCH));
    }
    
    @Test
    public void testTranslate_HTMLContentType() throws Exception {
        Translate.setContentType("text/html");
        assertEquals("<hello>Bonjour, mon nom est</hello>",Translate.execute("<hello>Hello, my name is</hello>", Language.AUTO_DETECT, Language.FRENCH));
    }
    @Test
    public void testTranslate_AutoDetectOrigin_French() throws Exception {
        assertEquals("Salut tout le monde", Translate.execute("Hallo welt", Language.AUTO_DETECT, Language.FRENCH));
    }
    @Test
    public void testTranslate_ItalianToGerman_AndBack() throws Exception {
        assertEquals("Salve, mondo", Translate.execute("Hallo welt", Language.GERMAN, Language.ITALIAN));
        assertEquals("Hallo welt".toLowerCase(), Translate.execute("Salve, mondo", Language.ITALIAN, Language.GERMAN).toLowerCase());
    }
    @Test
    public void testTranslate_EnglishToArabic_Unicode() throws Exception {
        //assertEquals("هذا هو بلدي الترجمة للعربية",Translate.execute("This is my translation intended for Arabic", Language.ENGLISH, Language.ARABIC));
    }
    @Test
    public void testTranslate_EnglishToTurkish_Unicode() throws Exception {
        assertEquals("Merhaba Dünya", Translate.execute("Hello world", Language.ENGLISH, Language.TURKISH));
        assertEquals("Hello World", Translate.execute("Merhaba Dünya", Language.TURKISH, Language.ENGLISH));
    }
    @Test
    public void testTranslate_EnglishToHindi_Unicode() throws Exception {
        assertEquals("हैलो वर्ल्ड", Translate.execute("Hello World", Language.ENGLISH, Language.HINDI));
        assertEquals("Hello World", Translate.execute("हैलो वर्ल्ड", Language.HINDI, Language.ENGLISH));
    }
    @Test
    public void testTranslate_EnglishToCatalan_Unicode() throws Exception {
        assertEquals("Hola món", Translate.execute("Hello World", Language.ENGLISH, Language.CATALAN));
        assertEquals("Hello World", Translate.execute("Hola món", Language.CATALAN, Language.ENGLISH));
    }
    @Test
    public void testTranslate_RussianToSpanish_Unicode() throws Exception {
        assertEquals("Hola mundo", Translate.execute("Привет мир", Language.RUSSIAN, Language.SPANISH));
    }
    @Test
    public void testTranslate_EnglishToJapanese_Unicode() throws Exception {
        assertEquals("ハローワールド", Translate.execute("Hello world", Language.ENGLISH, Language.JAPANESE));
        assertEquals("Hello world", Translate.execute("ハローワールド", Language.AUTO_DETECT, Language.ENGLISH));
    }
    @Test
    public void testTranslate_EnglishToKorean_Unicode() throws Exception {
        assertEquals("전 세계 여러분 안녕하세요", Translate.execute("Hello world", Language.ENGLISH, Language.KOREAN));
        assertEquals("Hello world", Translate.execute("전 세계 여러분 안녕하세요", Language.AUTO_DETECT, Language.ENGLISH));
    }
    @Test
    public void testTranslate_EnglishToKorean_DefaultToAutoDetect() throws Exception {
        assertEquals("전 세계 여러분 안녕하세요", Translate.execute("Hello world", Language.ENGLISH, Language.KOREAN));
        assertEquals("Hello world", Translate.execute("전 세계 여러분 안녕하세요", Language.AUTO_DETECT, Language.ENGLISH));
        assertEquals("Hello world", Translate.execute("전 세계 여러분 안녕하세요", Language.ENGLISH));
    }
    @Test
    public void testTranslate_EnglisthToHebrew_Unicode() throws Exception {
        assertEquals("מזהה", Translate.execute("ID", Language.ENGLISH, Language.HEBREW));
    }
    
    @Test
     public void testTranslate_NoKey() throws Exception {
        Translate.setKey(null);
        Translate.setClientId(null);
        Translate.setClientSecret(null);
        exception.expect(RuntimeException.class);
        exception.expectMessage("Must provide a Windows Azure Marketplace Client Id and Client Secret - Please see http://msdn.microsoft.com/en-us/library/hh454950.aspx for further documentation");
        Translate.execute("ハローワールド", Language.AUTO_DETECT, Language.ENGLISH);
    }
    @Test
     public void testTranslate_WrongKey() throws Exception {
        Translate.setKey("lessthan16");
        Translate.setClientId(null);
        Translate.setClientSecret(null);
        exception.expect(RuntimeException.class);
        exception.expectMessage("INVALID_API_KEY - Please set the API Key with your Bing Developer's Key");
        Translate.execute("ハローワールド", Language.AUTO_DETECT, Language.ENGLISH);
    }
    
    @Test
     public void testTranslate_SetKeyNoClientIdAndSecret() throws Exception {
        Translate.setClientId(null);
        Translate.setClientSecret(null);
        exception.expect(RuntimeException.class);
        exception.expectMessage("Must provide a Windows Azure Marketplace Client Id and Client Secret - Please see http://msdn.microsoft.com/en-us/library/hh454950.aspx for further documentation");
        String translate = Translate.execute("ハローワールド", Language.AUTO_DETECT, Language.ENGLISH);
        assertNotNull(translate);
    }
    /*
    @Test
    public void testTranslate_Exception() throws Exception {
       exception.expect(Exception.class);
       //exception.expectMessage("INVALID_API_KEY - Please set the API Key with your Bing Developer's Key");
       String result = Translate.execute("\"test\" red", Language.AUTO_DETECT, Language.ENGLISH);
       System.out.println(result);
   }
    */
    @Test
     public void testLarge() throws Exception {
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
		Translate.execute(largeText,
				Language.ENGLISH, Language.FRENCH);
	}
        @Ignore("Sometimes Fails, not sure why")
        public void testLargeLimit() throws Exception {
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
                                Translate.execute(largeText.substring(0,10240),
                                                Language.ENGLISH, Language.FRENCH);
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
                                exception.expectMessage("TEXT_TOO_LARGE - Microsoft Translator (Translate) can handle up to 10,240 bytes per request");
                                Translate.execute(largeText.substring(0,10242), Language.ENGLISH, Language.FRENCH);
                                
        }
       @Test
        public void testTranslateArray() throws Exception {
            String[] sourceTexts = {"Hello","I would like to be translated","How are you doing today?"};
            String[] translatedTexts = Translate.execute(sourceTexts, Language.ENGLISH, Language.FRENCH);
            assertEquals(3,translatedTexts.length);
            assertEquals("Salut",translatedTexts[0]);
            assertEquals("Je voudrais être traduit",translatedTexts[1]);
            assertEquals("Comment allez-vous faire aujourd'hui ?",translatedTexts[2]);
        }
       @Test
       public void testTranslateArrayWithQuotes() throws Exception {
           String[] sourceTexts = {"Hello","I would like to be \"translated\"","How are you doing today?"};
           String[] translatedTexts = Translate.execute(sourceTexts, Language.ENGLISH, Language.FRENCH);
           assertEquals(3,translatedTexts.length);
           assertEquals("Salut",translatedTexts[0]);
           assertEquals("Je tiens à être « traduit »",translatedTexts[1]);
           assertEquals("Comment allez-vous faire aujourd'hui ?",translatedTexts[2]);
       }
       @Test
        public void testTranslateArray_Overloaded() throws Exception {
            String[] sourceTexts = {"Hello","I would like to be translated","How are you doing today?"};
            String[] translatedTexts = Translate.execute(sourceTexts, Language.FRENCH);
            assertEquals(3,translatedTexts.length);
            assertEquals("Salut",translatedTexts[0]);
            assertEquals("Je voudrais être traduit",translatedTexts[1]);
            assertEquals("Comment allez-vous faire aujourd'hui ?",translatedTexts[2]);
        }
       
        @Test
        public void testLargeLimitArray() throws Exception {
                String[] sourceTexts = new String[30];
                String largeText = "Figures from the Office for National Statistics (ONS) show that between December and April, "
                                + "the five-month period typically regarded as peak bonus season, those working in the financial "
                                + "intermediation sector received bonuses worth £7.6bn.";
                                
                for(int i = 0;i<sourceTexts.length;i++)
                    sourceTexts[i] = largeText;

                String[] results = Translate.execute(sourceTexts,
                                Language.ENGLISH, Language.FRENCH);

                assertEquals(sourceTexts.length,results.length);
        }
        
        @Test
        public void testLargeLimitArrayException() throws Exception {
                String[] sourceTexts = new String[43];
                String largeText = "Figures from the Office for National Statistics (ONS) show that between December and April, "
                                + "the five-month period typically regarded as peak bonus season, those working in the financial "
                                + "intermediation sector received bonuses worth ¬¨¬£7.6bn.";
                                
                for(int i = 0;i<sourceTexts.length;i++)
                    sourceTexts[i] = largeText;

                exception.expect(RuntimeException.class);
                exception.expectMessage("TEXT_TOO_LARGE - Microsoft Translator (Translate) can handle up to 10,240 bytes per request");
                Translate.execute(sourceTexts, Language.ENGLISH, Language.FRENCH);
        }
        
        @Test
        public void testLargeChinese() throws Exception {
                String largeText = "据了解，深圳2011年保障性住房已全部开工。深圳一官员表示，政府部门这次是“动真格的”，今年深圳的1万套保障房竣工目标已经成为必须完成的 “硬任务”。如今，深圳保障房和商品房“双轨并行”发展，属于不同的市场需求。截至5月，深圳全市已有6个项目、约1.5万套保障房开工，完成年度计划的 20%。根据项目进度预安排，全市要确保今年6月底之前开工建设3万套以上保障性住房，9月底以前再开工建设4万套以上保障性住房，为确保今年开工任务完成提供有力保证。  www.6park.com 据业内人士透露，其他一些保障房项目也正在陆续开工。目前年内要竣工的1万套房源虽然还没有明确落实到底是哪些项目，但他分析认为，1万套指的是综合起来的一个数量，各个项目年内竣工套数加起来的一个总量，有些项目是2012年、2013年全部交付，但今年可能首期会竣工一部分。 前不久，深圳今年首次通过了“双限”方式出让保障房用地。此次出让地块应在今年10月底前开工，预计两年左右交房，销售均价相当于周边商品房一半。 据了解，深圳2011年保障性住房已全部开工。深圳一官员表示，政府部门这次是“动真格的”，今年深圳的1万套保障房竣工目标已经成为必须完成的 “硬任务”。如今，深圳保障房和商品房“双轨并行”发展，属于不同的市场需求。截至5月，深圳全市已有6个项目、约1.5万套保障房开工，完成年度计划的 20%。根据项目进度预安排，全市要确保今年6月底之前开工建设3万套以上保障性住房，9月底以前再开工建设4万套以上保障性住房，为确保今年开工任务完成提供有力保证。  www.6park.com 据业内人士透露，其他一些保障房项目也正在陆续开工。目前年内要竣工的1万套房源虽然还没有明确落实到底是哪些项目，但他分析认为，1万套指的是综合起来的一个数量，各个项目年内竣工套数加起来的一个总量，有些项目是2012年、2013年全部交付，但今年可能首期会竣工一部分。 据了解，深圳2011年保障性住房已全部开工。深圳一官员表示，政府部门这次是“动真格的”，今年深圳的1万套保障房竣工目标已经成为必须完成的 “硬任务”。如今，深圳保障房和商品房“双轨并行”发展，属于不同的市场需求。截至5月，深圳全市已有6个项目、约1.5万套保障房开工，完成年度计划的 20%。根据项目进度预安排，全市要确保今年6月底之前开工建设3万套以上保障性住房，9月底以前再开工建设4万套以上保障性住房，为确保今年开工任务完成提供有力保证。  www.6park.com 据业内人士透露，其他一些保障房项目也正在陆续开工。目前年内要竣工的1万套房源虽然还没有明确落实到底是哪些项目，但他分析认为，1万套指的是综合起来的一个数量，各个项目年内竣工套数加起来的一个总量，有些项目是2012年、2013年全部交付，但今年可能首期会竣工一部分。 前不久，深圳今年首次通过了“双限”方式出让保障房用地。此次出让地块应在今年10月底前开工，预计两年左右交房，销售均价相当于周边商品房一半。 据了解，深圳2011年保障性住房已全部开工。深圳一官员表示，政府部门这次是“动真格的”，今年深圳的1万套保障房竣工目标已经成为必须完成的 “硬任务”。如今，深圳保障房和商品房“双轨并行”发展，属于不同的市场需求。截至5月，深圳全市已有6个项目、约1.5万套保障房开工，完成年度计划的 20%。根据项目进度预安排，全市要确保今年6月底之前开工建设3万套以上保障性住房，9月底以前再开工建设4万套以上保障性住房，为确保今年开工任务完成提供有力保证。  www.6park.com 据业内人士透露，其他一些保障房项目也正在陆续开工。目前年内要竣工的1万套房源虽然还没有明确落实到底是哪些项目，但他分析认为，1万套指的是综合起来的一个数量，各个项目年内竣工套数加起来的一个总量，有些项目是2012年、2013年全部交付，但今年可能首期会竣工一部分。据了解，深圳2011年保障性住房已全部开工。深圳一官员表示，政府部门这次是“动真格的”，今年深圳的1万套保障房竣工目标已经成为必须完成的 “硬任务”。如今，深圳保障房和商品房“双轨并行”发展，属于不同的市场需求。截至5月，深圳全市已有6个项目、约1.5万套保障房开工，完成年度计划的 20%。根据项目进度预安排，全市要确保今年6月底之前开工建设3万套以上保障性住房，9月底以前再开工建设4万套以上保障性住房，为确保今年开工任务完成提供有力保证。  www.6park.com 据业内人士透露，其他一些保障房项目也正在陆续开工。目前年内要竣工的1万套房源虽然还没有明确落实到底是哪些项目，但他分析认为，1万套指的是综合起来的一个数量，各个项目年内竣工套数加起来的一个总量，有些项目是2012年、2013年全部交付，但今年可能首期会竣工一部分。 但他分析认为，1万套指的是综合起来的一个数量，各个项目年内竣工套数加起来的一个总量，有些项目是2012年、2013年全部交付，但今年可能首期会竣工一部分。 ";
                largeText += " " + largeText;
                largeText += " " + largeText;
                exception.expect(RuntimeException.class);
                exception.expectMessage("TEXT_TOO_LARGE - Microsoft Translator (Translate) can handle up to 10,240 bytes per request");
                Translate.execute(largeText, Language.AUTO_DETECT, Language.ENGLISH);
                                
        }
       
}
