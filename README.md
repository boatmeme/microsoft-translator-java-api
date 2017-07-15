# microsoft-translator-java-api 
* * *

Provides a Java wrapper around the Microsoft Translator API aka Bing Translator. 

Created in an attempt to fill the void left by the [deprecation](http://googlecode.blogspot.com/2011/05/spring-cleaning-for-some-of-our-apis.html) of the [Google Translate API](http://code.google.com/apis/language/translate/overview.html) announced on May 26, 2011 and scheduled for permanent shutdown on December 1, 2011.

In an effort to lessen the impact on Java developers that have previously integrated the Google Translate API into their applications, it
is my goal to mimic the code structure, naming conventions, functionality, and usage patterns of the excellent and widely used [google-api-translate-java](https://github.com/richmidwinter/google-api-translate-java) by Rich Midwinter.

## Requires

* Java 1.5 or greater
* A Windows Azure Marketplace Client ID and Client Secret - [Documentation](http://msdn.microsoft.com/en-us/library/hh454950.aspx)
                                                          
_Please note: If you signed up for a Bing Developer Key after March 31, 2012, you will not be able to use your App Id with this API. Please visit the aforementioned documentation link_

Quickstart
===========

Download the latest [JAR with Dependencies](https://microsoft-translator-java-api.googlecode.com/files/microsoft-translator-java-api-0.6.2-jar-with-dependencies.jar)

    import com.memetix.mst.language.Language;
    import com.memetix.mst.translate.Translate;

    public class Main {
      public static void main(String[] args) throws Exception {
        // Set your Windows Azure Marketplace client info - See http://msdn.microsoft.com/en-us/library/hh454950.aspx
        Translate.setClientId(/* Enter your Windows Azure Client Id here */);
        Translate.setClientSecret(/* Enter your Windows Azure Client Secret here */);

        String translatedText = Translate.execute("Bonjour le monde", Language.FRENCH, Language.ENGLISH);

        System.out.println(translatedText);
      }
    }

More Examples
=============

I've posted some examples to the [SVN repository](http://code.google.com/p/microsoft-translator-java-api/source/browse/#svn%2Ftrunk%2Fmicrosoft-translator-java-examples%2Fsrc%2Fmain%2Fjava%2Fcom%2Fmemetix%2Fmst%2Fexamples) on Google Code. The examples include:

  * [Translating text between two languages](http://code.google.com/p/microsoft-translator-java-api/source/browse/trunk/microsoft-translator-java-examples/src/main/java/com/memetix/mst/examples/TranslateExample.java)
  * [Detecting the native language of given text](http://code.google.com/p/microsoft-translator-java-api/source/browse/trunk/microsoft-translator-java-examples/src/main/java/com/memetix/mst/examples/DetectLanguageExample.java)
  * [Getting a list of supported languages, with localized language names](http://code.google.com/p/microsoft-translator-java-api/source/browse/trunk/microsoft-translator-java-examples/src/main/java/com/memetix/mst/examples/LanguageLocalizationExamples.java)
  * [Generating and playing a WAV of given text spoken in a chosen dialect](http://code.google.com/p/microsoft-translator-java-api/source/browse/trunk/microsoft-translator-java-examples/src/main/java/com/memetix/mst/examples/SpeakTextExample.java)
  * [Bulk translation using the TranslateArray service](http://code.google.com/p/microsoft-translator-java-api/source/browse/trunk/microsoft-translator-java-examples/src/main/java/com/memetix/mst/examples/TranslateArrayExample.java)

Maven
=====

For those using Maven 2 to manage their project dependencies, the microsoft-translator-java-api is distributed via the [Maven Central](http://search.maven.org/#browse%7C458759702) repository. Simply include the following in your POM.xml to use the Microsoft Translator Java API:

    <dependency>
        <groupId>com.memetix</groupId>
        <artifactId>microsoft-translator-java-api</artifactId>
        <version>0.6.2</version>
        <type>jar</type>
    </dependency>

Or, if you're feeling adventurous, help us test the next version by adding the latest SNAPSHOT to your POM.xml:

    <dependency>
        <groupId>com.memetix</groupId>
        <artifactId>microsoft-translator-java-api</artifactId>
        <version>0.7-SNAPSHOT</version>
        <type>jar</type>
    </dependency>

The SNAPSHOT is hosted at the [Sonatype OSS Snapshot](https://oss.sonatype.org/content/repositories/snapshots/) repository, so if you do not already have it in your repository list, you will need to add this also

    <repository>
        <id>sonatype-oss-repo</id>
        <name>Sonatype Open Source maven snapshot repo</name>
        <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
    </repository>
    
## Support on Beerpay
Contributions to the :beers: fund are much appreciated!

[![Beerpay](https://beerpay.io/boatmeme/microsoft-translator-java-api/badge.svg?style=beer-square)](https://beerpay.io/boatmeme/microsoft-translator-java-api) [![Beerpay](https://beerpay.io/boatmeme/microsoft-translator-java-api/make-wish.svg?style=flat-square)](https://beerpay.io/boatmeme/microsoft-translator-java-api?focus=wish)

License
=======

The microsoft-translator-java-api is licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

    /*
     * Copyright 2011-2015 Jonathan Griggs.
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

Please note that while this license does not distinguish between personal, internal or commercial use, the Microsoft Translator API itself _**does**_ in fact make this distinction.

>  If you intend to use the Microsoft Translator API for commercial or high volume purposes, you would need to sign a commercial license agreement and provide your appID to the Microsoft Translator team. For more details contact [mtlic@microsoft.com](mailto:mtlic@microsoft.com). This allows the Microsoft Translator team to better tune the service to the needs of our many partners, and avoid abuse.
