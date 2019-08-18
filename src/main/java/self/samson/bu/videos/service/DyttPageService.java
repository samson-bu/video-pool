package self.samson.bu.videos.service;

import org.apache.http.HttpException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import self.samson.bu.videos.entity.Video;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DyttPageService implements IPageService{

    private static String baseUrl = "https://www.dytt8.net/index.htm";

    @Override
    public List<Video> getVideos() {

        ArrayList<Video> videos = new ArrayList<>();
        ArrayList<String> links = new ArrayList<>();

        // 主页
        Document mainPage = this.homePage(baseUrl);

        // 要爬取的分类
        List<Document> categories = categories(mainPage);
        for (Document category : categories) {
            links.addAll(this.links(category));
            break;
        }

        System.out.println(links.size());
        System.out.println();
        System.out.println(links);

        // 1. 确定要爬取的链接列表



        // co_content8


        // 2. 爬取列表中每个链接，获得详细信息
        for (String link : links) {

        }

        return videos;
    }

    public Document homePage(String url) {

        HttpGet get = new HttpGet(url);

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(get)) {

            int code = response.getStatusLine().getStatusCode();
            if (code != 200) throw new HttpException(String.format("请求异常，返回值为%d", code));

            return Jsoup.parse(response.getEntity().getContent(), "GBK", url);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Document> categories(Document homePage) {
        ArrayList<Document> categories = new ArrayList<>();

        Elements elements = homePage.select("div.title_all");
        for (Element element : elements) {
            Elements results = element.select("strong");
            if (results.size() <= 0) continue;

            String categoryTitle = results.get(0).ownText();

            // 1.1 所有分类的主页
            if (categoryTitle.equals("2019新片精品") || categoryTitle.equals("华语电视剧") || categoryTitle.equals("欧美电视剧") || categoryTitle.equals("迅雷综艺节目")) {
                results = element.select("em a[href]");
                if (results.size() <= 0) continue;
                String url = results.get(0).attr("abs:href");

                Document category = this.homePage(url);
                if (category != null) categories.add(category);
            }
        }

        return categories;
    }

    public List<String> links(Document page) {

        ArrayList<String> links = new ArrayList<>();

        Elements contents = page.select("div.co_content8 ul a[href]");
        Element next = page.selectFirst("div.co_content8 div.x a[href]:containsOwn(下一页)");

        for (Element content : contents) {
            links.add(content.attr("abs:href"));
        }

        if (next != null) {

            try {
                Thread.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Document nextPage = this.homePage(next.attr("abs:href"));
            links.addAll(this.links(nextPage));


        }

        return links;
    }
}
