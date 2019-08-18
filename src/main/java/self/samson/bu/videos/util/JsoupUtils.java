package self.samson.bu.videos.util;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtils {

    public static Element selectFirst(Elements elements, String selector) {
        Elements results = elements.select(selector);
        if (results.size() > 0) {
            return results.get(0);
        } else {
            return null;
        }
    }
}
