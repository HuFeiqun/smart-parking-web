package parking.hfq.com.smartparking.model;

/**
 * @Created by hfq on 2020/3/26 12:25
 * @used to:
 * @return:
 */
public class Order {
    private String id;
    private String carId;
    private String appointTime;
    private float charge;
    private String startDate;
    private int startHour;
    private String endDate;
    private int endHour;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(String appiontTime) {
        this.appointTime = appiontTime;
    }

    public float getCharge() {
        return charge;
    }

    public void setCharge(float charge) {
        this.charge = charge;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int stratHour) {
        startHour = stratHour;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }
}
