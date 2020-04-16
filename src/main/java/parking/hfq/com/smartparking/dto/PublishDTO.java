package parking.hfq.com.smartparking.dto;

/**
 * @Created by hfq on 2020/4/16 16:46
 * @used to: 公告DTO
 * @return:
 */
public class PublishDTO {
    private String title;
    private String description;
    private String tags;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "PublishDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }
}
