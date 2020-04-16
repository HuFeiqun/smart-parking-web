package parking.hfq.com.smartparking.dto;

/**
 * @Created by hfq on 2020/4/16 17:24
 * @used to: 返回结果DTO
 * @return:
 */
public class ResultDTO {
    private int Status;
    private String message;
    private Object object;

    public ResultDTO(int status, String message, Object object) {
        Status = status;
        this.message = message;
        this.object = object;
    }

    public ResultDTO(int status, Object object) {
        Status = status;
        this.object = object;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
