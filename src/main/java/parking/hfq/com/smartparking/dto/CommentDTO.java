package parking.hfq.com.smartparking.dto;

/**
 * @Created by hfq on 2020/4/16 21:01
 * @used to:
 *     {
 *              "parentId": parentId,
 *             "content": content,
 *             "type": type
 *     }
 */
public class CommentDTO {
    private Integer type;
    private String content;
    private Integer parentId;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
