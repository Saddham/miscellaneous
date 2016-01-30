package com.timepass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {
	private static WebPageReader webPageReader = WebPageReader.getInstance();
	private static String webPage;
     
	public static void main(String[] args) {
		assert args.length == 1 : "usage: supply url to fetch";
		String sourceURL = args[0];
		List<String> links = getMostPopularLink(sourceURL);
		System.out.println("Most Popular Links: ");
		for (String link : links) {
			System.out.println(link);
		}
	}

	public static List<String> getMostPopularLink(String sourceURL) {
        // timeout connection after 500 miliseconds
       // System.setProperty("sun.net.client.defaultConnectTimeout", "500");
        //System.setProperty("sun.net.client.defaultReadTimeout",    "1000");
        
		// list of web pages to be examined
        Queue<String> queue = new LinkedList<String>();
        queue.add(sourceURL);		
        
        // list of web pages examined
        List<String> marked = new ArrayList<String>();
        marked.add(sourceURL);
        
        // list of links with their respective counts
        Map<String, Long> linksCount = new HashMap<String, Long>();
        linksCount.put(sourceURL, 1L);
        
        /*
         *  Find links of the form: http://xxx.yyy.zzz
         *  \\w+ for one or more alpha-numeric characters
         *  \\. for dot
         *  could take first two statements out of loop
         */
        
         String regexp = "(http|ftp|https)://[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";
         Pattern pattern = Pattern.compile(regexp);
         Matcher matcher;
         String nextLink;
         String curLink;
        int i=20;
        // breadth first search crawl of web
        while (!queue.isEmpty() && i>0) {
        	i--;
            curLink = queue.remove();

            webPage = webPageReader.readPage(curLink);

            if (webPage == null) continue;

            matcher = pattern.matcher(webPage);
   
            while (matcher.find()) {            	
                nextLink = matcher.group();
                if (!marked.contains(nextLink)) {
                    queue.add(nextLink);
                    marked.add(nextLink);
                    linksCount.put(nextLink, 1L);
                } else{
                	linksCount.put(nextLink, linksCount.get(nextLink)+1L);
                }
            }

        } 
		
        return getLinskWithMaxCount(linksCount);		 
	}

	private static List<String> getLinskWithMaxCount(Map<String, Long> linksCount) {
        List<Entry<String, Long>> list = new ArrayList<Entry<String, Long>>(linksCount.entrySet());
        
        Collections.sort( list, new Comparator<Map.Entry<String, Long>>() {
            public int compare( Map.Entry<String, Long> linkCount1, Map.Entry<String, Long> linkCount2 ){
                return (linkCount2.getValue()).compareTo( linkCount1.getValue() );
            }
        } );
         
        List<String> linksList = new ArrayList<String>();
        Long prevCount = list.get(0).getValue();
       
        for (Map.Entry<String, Long> linkInfo : list) {
			if(prevCount.equals(linkInfo.getValue())){
				linksList.add(linkInfo.getKey());				
			}
			System.out.println(linkInfo.getKey()+": "+linkInfo.getValue());
		}
        
        return linksList;
	}
}
