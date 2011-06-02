#microsoft-translator-java-api
* * *

Provides a Java wrapper around the Microsoft Translator API aka Bing Translator. 

Created in an attempt to fill the void left by the [deprecation](http://googlecode.blogspot.com/2011/05/spring-cleaning-for-some-of-our-apis.html) of the [Google Translate API](http://code.google.com/apis/language/translate/overview.html) announced on May 26, 2011 and scheduled for permanent shutdown on December 1, 2011.

In an effort to lessen the impact on Java developers that have previously integrated the Google Translate API into their applications, it
is my goal to mimic the code structure, naming conventions, functionality, and usage patterns of the excellent and widely used [google-api-translate-java](https://github.com/richmidwinter/google-api-translate-java) by Rich Midwinter.

## Requires

* A Bing Developer API Key - [Sign Up Here](http://www.bing.com/developers/createapp.aspx)

Quickstart:
===========

Download the latest [JAR with Dependencies](https://github.com/downloads/boatmeme/microsoft-translator-java-api/microsoft-translator-java-api-0.3-jar-with-dependencies.jar)

    import com.memetix.mst.language.Language;
    import com.memetix.mst.translate.Translate;

    public class Main {
      public static void main(String[] args) throws Exception {
        // Set the Microsoft Translator API Key - Get yours at http://www.bing.com/developers/createapp.aspx
        Translate.setKey(/* Enter your API Key here */);

        String translatedText = Translate.execute("Bonjour le monde", Language.FRENCH, Language.ENGLISH);

        System.out.println(translatedText);
      }
    }

More Examples:
==============

I've posted some examples to the [SVN repository](http://code.google.com/p/microsoft-translator-java-api/source/browse/#svn%2Ftrunk%2Fmicrosoft-translator-java-examples%2Fsrc%2Fmain%2Fjava%2Fcom%2Fmemetix%2Fmst%2Fexamples) on Google Code. The examples include:

  * [Translating text between two languages](http://code.google.com/p/microsoft-translator-java-api/source/browse/trunk/microsoft-translator-java-examples/src/main/java/com/memetix/mst/examples/TranslateExample.java)
  * [Detecting the native language of given text](http://code.google.com/p/microsoft-translator-java-api/source/browse/trunk/microsoft-translator-java-examples/src/main/java/com/memetix/mst/examples/DetectLanguageExample.java)
  * [Getting a list of supported languages, with localized language names](http://code.google.com/p/microsoft-translator-java-api/source/browse/trunk/microsoft-translator-java-examples/src/main/java/com/memetix/mst/examples/LanguageLocalizationExamples.java)
  * [Generating and playing a WAV of given text spoken in a chosen dialect](http://code.google.com/p/microsoft-translator-java-api/source/browse/trunk/microsoft-translator-java-examples/src/main/java/com/memetix/mst/examples/SpeakTextExample.java)

Maven
=====

For those using Maven 2 to manage their project dependencies, the microsoft-translator-java-api is distributed via the [Maven Central](http://search.maven.org/#browse%7C458759702) repository. Simply include the following in your POM.xml to use the Microsoft Translator Java API:

    <dependency>
        <groupId>com.memetix</groupId>
        <artifactId>microsoft-translator-java-api</artifactId>
        <version>0.3</version>
        <type>jar</type>
    </dependency>

Or, if you're feeling adventurous, help us test the next version by adding the latest SNAPSHOT to your POM.xml:

    <dependency>
        <groupId>com.memetix</groupId>
        <artifactId>microsoft-translator-java-api</artifactId>
        <version>0.4-SNAPSHOT</version>
        <type>jar</type>
    </dependency>

The snapshot is hosted at the [OSS Sonatype Snapshot](https://oss.sonatype.org/content/repositories/snapshots/) repository