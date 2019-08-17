package self.samson.bu.videos.service;

import org.apache.http.HttpException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import self.samson.bu.videos.entity.Video;

import java.util.List;

public class DyttPageService implements IPageService{

    private static String baseUrl = "https://www.dytt8.net/";

    @Override
    public List<Video> getVideos() {

        Document mainPage = this.mainPage(baseUrl);




        return null;
    }

    public Document mainPage(String url) {

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
}
