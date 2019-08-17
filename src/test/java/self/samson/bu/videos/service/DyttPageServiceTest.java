package self.samson.bu.videos.service;

import org.junit.Test;

public class DyttPageServiceTest {

    @Test
    public void mainPageTest() {
        DyttPageService service = new DyttPageService();
        System.out.println(service.mainPage("https://www.dytt8.net/"));
    }
}
