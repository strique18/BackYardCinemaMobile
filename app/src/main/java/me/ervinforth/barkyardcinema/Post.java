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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageContent() {
        return imageContent;
    }

    public void setImageContent(String imageContent) {
        this.imageContent = imageContent;
    }

    public String getVideoContent() {
        return videoContent;
    }

    public void setVideoContent(String videoContent) {
        this.videoContent = videoContent;
    }

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
