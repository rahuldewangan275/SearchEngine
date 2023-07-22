package org.example;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashSet;
 // this workd as crowler bot

public class Crawler {
    HashSet<String> urlSet;
    int MAX_DEPTH =2;
     Crawler(){
         urlSet = new HashSet<>();
     }
     public void getPageTextAndLinks(String url , int depth){ // if we get the same page  again then simply return
         if(urlSet.contains(url)){
             return;
         }
         if(depth > MAX_DEPTH){
             return;
         }
        if( urlSet.add(url)){
            // storing url's
            System.out.println(url);
        }

         depth++;
         try {
           Document document = Jsoup.connect(url).timeout(7000).get();
             //indexer works starts here
             Indexer indexer = new Indexer(document , url );
             System.out.println(document.title());

             Elements availableLinksOnPage = document.select("a[href]"); //store  hyper links(href) present in web page
             for (Element currentLink : availableLinksOnPage) {
                 getPageTextAndLinks(currentLink.attr("abs:href"), depth);
             }
         }
         catch(IOException ioException){
             ioException.printStackTrace();
         }
     }
    public static void  main(String[] args) {

         Crawler crawler = new Crawler();
         crawler.getPageTextAndLinks("https://www.javatpoint.com/"  , 1);
    }
}