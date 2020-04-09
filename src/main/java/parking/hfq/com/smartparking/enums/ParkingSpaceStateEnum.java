package parking.hfq.com.smartparking.enums;

/**
 * @Created by hfq on 2020/4/9 19:35
 * @used to: 对于paking_space表的in_use字段
 * @return:
 */
public enum ParkingSpaceStateEnum {
    NOT_IN_USE("空闲状态",0),
    IN_USE("使用状态",1);

    private String message;
    private int state;

    public String getMessage() {
        return message;
    }

    public int getState() {
        return state;
    }

    ParkingSpaceStateEnum(String message, int state) {
        this.message = message;
        this.state = state;
    }}
