package self.samson.bu.videos.entity;

import lombok.Value;

@Value
public class Video {
    private String title;
    private String description;
    private String posterUrl;
    private String[] downloadUrls;
}
