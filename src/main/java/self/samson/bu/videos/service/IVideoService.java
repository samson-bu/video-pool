package self.samson.bu.videos.service;

import org.jsoup.nodes.Element;

import java.util.List;

public interface IVideoService {

    int URI_TYPE_PAGE = 1;
    int URI_TYPE_MULTI_MEDIA = 0;

    /**
     * 获取指定uri的内容，并从中查找出所有的链接元素，即`<a href="***">`标签表示的元素
     * @param uri 要访问的URI
     * @return 返回页面中包含的所有`<a>`元素
     */
    List<Element> preProcessing(String uri);

    /**
     * 判断所给元素是何种类型
     * @param element 带判断元素
     * @return 1-需要继续下载的链接；0-多媒体元素的下载链接
     */
    int screening(Element element);
}
