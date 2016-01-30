package com.timepass;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class WebPageReader {
	private static WebPageReader webPageReader = new WebPageReader();
    // assume Unicode UTF-8 encoding
    private static final String CHARSET_NAME = "UTF-8";

    // assume language = English, country = US for consistency with System.out.
    private static final Locale LOCALE = Locale.US;

    // the default token separator
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");

    // used to read the entire input.
    private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");

    private static Scanner scanner;

    private WebPageReader(){}
    
    public static WebPageReader getInstance(){
    	return webPageReader;
    }
    
   /**
     * Initializes an input stream from a web page name.
     *
     * @param  link the filename or web page name
     */
    public String readPage(String link) {
    	String webPage = null;
    	try {            
            URL url = new URL(link);
                        
            URLConnection site = url.openConnection();
            
            InputStream inputStream = site.getInputStream();
            scanner = new Scanner(new BufferedInputStream(inputStream), CHARSET_NAME);
            scanner.useLocale(LOCALE);                        
            
            if(scanner != null){
            	webPage =  readAll();
            }
            
        } catch (IOException ioe) {
        	ioe.printStackTrace();
        } finally{
            close();
        }
        
       return webPage;
    }

   /**
     * Reads and returns the content of the web page as a string.
     *
     * @return the content of the web page as a string.
     */
    private String readAll() {
        if (!scanner.hasNextLine())
            return "";

        String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
        // resetting delimeter
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }
    
    /**
     * Closes the input stream.
     */
    private void close() {
    	if(scanner != null){
    		scanner.close();  
        }
    }
}

