package helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class GetUrl {
    public static String getUrlFromHTML(String html){
        String result = "/views/client/assets/img/newspaper.jpg";
        try {
            Document doc = Jsoup.parse(html);

            Element imgElement = doc.selectFirst("img.lazy");
            if (imgElement != null) {
                String src = imgElement.attr("src");
                result = src;
            }

            Element divElement = doc.selectFirst("div.fig-picture");
            if (divElement != null) {
                String dataSrc = divElement.attr("data-src");
                result = dataSrc;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public static String getDescriptionFromHTML(String html){
        String result = "";
        try {
            Document doc = Jsoup.parse(html);

            Element divElement = doc.selectFirst("p.description");
            if (divElement != null) {
                String dataSrc = divElement.outerHtml();
                result = dataSrc;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
