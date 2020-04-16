package parking.hfq.com.smartparking.dto;

/**
 * @Created by hfq on 2020/4/16 14:55
 * @used to: 车辆进入停车场的请求 DTO
 * @return:
 */
public class ParkSpaceDTO {
    private Integer id;           //车位号
    private Integer parkinlot_id; //停车场号
    private String car_id;        //进入停车场的车牌号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParkinlot_id() {
        return parkinlot_id;
    }

    public void setParkinlot_id(Integer parkinlot_id) {
        this.parkinlot_id = parkinlot_id;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

}
