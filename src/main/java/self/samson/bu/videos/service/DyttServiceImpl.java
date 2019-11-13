package self.samson.bu.videos.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DyttServiceImpl implements IVideoService {

    private static final Logger LOGGER = LogManager.getLogger(DyttServiceImpl.class);

    private static final String URL_CSS_QUERY = "a[href]";

    @Override
    public List<Element> preProcessing(String uri) {
        // TODO: 切片方式增加入口trace

        List<Element> elements = new ArrayList<>();
        try {
            Document page = Jsoup.connect(uri).get();
            elements = page.select(DyttServiceImpl.URL_CSS_QUERY);
        } catch (IOException ioe) {
            LOGGER.error(ioe, ioe);
        }

        // TODO: 切片方式增加出口trace
        return elements;
    }

    @Override
    public int screening(Element element) {
        // TODO: 实现分类机制
        return 0;
    }
}
