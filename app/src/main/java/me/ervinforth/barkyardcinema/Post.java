package me.ervinforth.barkyardcinema;

import java.time.LocalDateTime;

public class Post {

    private String id;
    private String name; // Organisation
    private LocalDateTime dateTime;
    private String summary;
    private String textContent;
    private String imageContent;
    private String videoContent;
    private String type; // text, video, image

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getTextContent() {
        return textContent;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dateTime=" + dateTime +
                ", summary='" + summary + '\'' +
                ", textContent='" + textContent + '\'' +
                ", imageContent='" + imageContent + '\'' +
                ", videoContent='" + videoContent + '\'' +
                '}';
    }
}
