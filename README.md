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

Download the JAR @ https://github.com/downloads/boatmeme/microsoft-translator-java-api/microsoft-translator-java-api-0.2-SNAPSHOT.jar

    import com.memetix.mst.Language;
    import com.memetix.mst.translate.Translate;

    public class Main {
      public static void main(String[] args) throws Exception {
        // Set the Microsoft Translator API Key - Get yours at http://www.bing.com/developers/createapp.aspx
        Translate.setKey(/* Enter your API Key here */);

        String translatedText = Translate.execute("Bonjour le monde", Language.FRENCH, Language.ENGLISH);

        System.out.println(translatedText);
      }
    }

Maven
=====
Include the following in your POM.xml:

    <dependency>
        <groupId>com.memetix</groupId>
        <artifactId>microsoft-translator-java-api</artifactId>
        <version>0.3</version>
        <type>jar</type>
    </dependency>