package parking.hfq.com.smartparking.enums;

/**
 * @Created by hfq on 2020/4/16 19:06
 * @used to:
 * @return:
 */
public enum  CommentTypeEnum {
    FIRST_CLASS_COMMENT(0,"一级回复"),
    SECOND_CLASS_COMMENT(1,"二级回复");

    private int type;
    private String message;

    CommentTypeEnum(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }}
