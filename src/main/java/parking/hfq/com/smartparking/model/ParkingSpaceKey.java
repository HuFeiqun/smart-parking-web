package parking.hfq.com.smartparking.model;

public class ParkingSpaceKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_space.id
     *
     * @mbg.generated Thu Apr 16 21:21:39 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column parking_space.parkinglot_id
     *
     * @mbg.generated Thu Apr 16 21:21:39 CST 2020
     */
    private Integer parkinglotId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_space.id
     *
     * @return the value of parking_space.id
     *
     * @mbg.generated Thu Apr 16 21:21:39 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_space.id
     *
     * @param id the value for parking_space.id
     *
     * @mbg.generated Thu Apr 16 21:21:39 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column parking_space.parkinglot_id
     *
     * @return the value of parking_space.parkinglot_id
     *
     * @mbg.generated Thu Apr 16 21:21:39 CST 2020
     */
    public Integer getParkinglotId() {
        return parkinglotId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column parking_space.parkinglot_id
     *
     * @param parkinglotId the value for parking_space.parkinglot_id
     *
     * @mbg.generated Thu Apr 16 21:21:39 CST 2020
     */
    public void setParkinglotId(Integer parkinglotId) {
        this.parkinglotId = parkinglotId;
    }
}